package com.eduflix.eduflix.Service;

import com.eduflix.eduflix.Dto.SupportMessageDto;
import com.eduflix.eduflix.Entity.Student;
import com.eduflix.eduflix.Entity.SupportMessages;
import com.eduflix.eduflix.Entity.Users;
import com.eduflix.eduflix.Repository.StudentRepository;
import com.eduflix.eduflix.Repository.SupportMessageRepository;
import com.eduflix.eduflix.Repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springdoc.core.providers.ObjectMapperProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@AllArgsConstructor
public class SupportMessageService {
    private final StudentRepository studentRepository;
    private final SupportMessageRepository studentSupportMessageRepository;
    private final UsersRepository usersRepository;
    private final Map<String, WebSocketSession> sessions = new HashMap<>();
    private final ObjectMapperProvider objectMapperProvider;

    public void registerSession(WebSocketSession session) {
        sessions.put(session.getId(), session);
    }

    public void unregisterSession(WebSocketSession session) {
        sessions.remove(session.getId());
    }

    @Transactional
    public void sendMessageFromStudent(Long studentId, SupportMessageDto message) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            return;
        }
        Users user = usersRepository.findById(student.getUsers().getId()).orElse(null);
        for (WebSocketSession session : sessions.values()) {
            if (session.isOpen()) {
                try {

                    assert user != null;
                    Map<String, Object> messageData = Map.of(
                            "title", message.getTitle(),
                            "message", message.getMessage(),
                            "user", user
                    );
//                    String formattedMessage = String.format("From %s: %s - %s",
//                            student.getFirstName(), message.getTitle(), message.getMessage());
                    String jsonMessage = objectMapperProvider.jsonMapper().writeValueAsString(messageData);
                    session.sendMessage(new TextMessage(jsonMessage));
                    //save message to db with sender
                    saveSupportMessage(user, message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void saveSupportMessage(Users user, SupportMessageDto message) {
        SupportMessages messages = new SupportMessages();
        messages.setTitle(message.getTitle());
        messages.setMessage(message.getMessage());
        messages.setUser(user);
        studentSupportMessageRepository.save(messages);
    }
}
