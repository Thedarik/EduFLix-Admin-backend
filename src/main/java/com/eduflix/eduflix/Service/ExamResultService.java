package com.eduflix.eduflix.Service;

import com.eduflix.eduflix.Dto.ExamResultResponseDto;
import com.eduflix.eduflix.Entity.ExamResult;
import com.eduflix.eduflix.Entity.Student;
import com.eduflix.eduflix.Repository.ExamResultRepository;
import com.eduflix.eduflix.Repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ExamResultService {

    private final ExamResultRepository examResultRepository;
    private final StudentRepository studentRepository;

    public List<ExamResultResponseDto> getResultsByStudentId(Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        List<ExamResult> result = examResultRepository.findByStudent(student);
        List<ExamResultResponseDto> responseDtos = new ArrayList<>();

        for (ExamResult examResult : result) {
            String className = examResult.getClasses().getClassName();
            String courseName = examResult.getClasses().getCourse().getName();
            ExamResultResponseDto dto = ExamResultResponseDto.builder()
                    .className(className)
                    .courseName(courseName)
                    .studentId(studentId)
                    .result(examResult.getResult())
                    .examTime(examResult.getDate())
                    .build();

            responseDtos.add(dto);
        }
        return responseDtos;
    }
}
