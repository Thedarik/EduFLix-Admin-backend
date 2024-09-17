package com.eduflix.eduflix.Service;

import com.eduflix.eduflix.AdminPanel.Dto.updateTeacherDetailsRequest;
import com.eduflix.eduflix.Dto.TeacherDto;
import com.eduflix.eduflix.Entity.Teacher;
import com.eduflix.eduflix.Exceptions.NotFoundException;
import com.eduflix.eduflix.Repository.TeacherRepository;
import com.eduflix.eduflix.controller.GeneratePassAndUsername;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final UsersService usersService;

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Transactional
    public Teacher createTeacher(TeacherDto teacher) {
        Teacher teacherEntity = new Teacher();
        teacherEntity.setFirstname(teacher.getFirstname());
        teacherEntity.setLastname(teacher.getLastname());
        teacherEntity.setEmail(teacher.getEmail());
        teacherEntity.setContact(teacher.getContact());
        teacherEntity.setHiredAt(teacher.getHiredAt());
        GeneratePassAndUsername userDetails = usersService.saveUser2(teacher);
        teacherEntity.setUsers(userDetails.getUsers());
        return teacherRepository.save(teacherEntity);
    }

    public String deleteTeacher(Long id) throws NotFoundException {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return "Teacher with id " + id + " was deleted";
        }
        throw new NotFoundException("no such teacher!");
    }

    public boolean existsByEmailOrContact(TeacherDto teacher) {
        return teacherRepository.existsByEmailOrContact(teacher.getEmail(), teacher.getContact());
    }

    public Teacher updateTeacherDetails(updateTeacherDetailsRequest request) throws NotFoundException {
        Optional<Teacher> teacher = teacherRepository.findById(request.getId());

        if (teacher.isPresent()) {
            Teacher teacherEntity = teacher.get();

            if (request.getLastname() != null) {
                teacher.get().setLastname(request.getLastname());
            }
            if (request.getFirstname() != null) {
                teacher.get().setFirstname(request.getFirstname());
            }
            if (request.getEmail() != null) {
                teacher.get().setEmail(request.getEmail());
            }
            if (request.getContact() != null) {
                teacher.get().setContact(request.getContact());
            }
            return teacherRepository.save(teacherEntity);
        }
        throw new NotFoundException("not found!");
    }

    public Integer countTeacher() {
        return teacherRepository.countTeachers();
    }

    public Teacher findById(Long id) throws NotFoundException {
        return teacherRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Teacher with id " + id + " not found!"));
    }
}
