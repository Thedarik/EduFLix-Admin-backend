package com.eduflix.eduflix.Service;

import com.eduflix.eduflix.Dto.CourseDto;
import com.eduflix.eduflix.Dto.StudentDto;
import com.eduflix.eduflix.Entity.Classes;
import com.eduflix.eduflix.Entity.Student;
import com.eduflix.eduflix.Repository.StudentRepository;
import com.eduflix.eduflix.controller.GeneratePassAndUsername;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final UsersService usersService;

    public boolean exists(Long studentId) {
        return studentRepository.existsById(studentId);
    }
//findByStudentId() is used to retrieve set of courses student have taken by student id
    public Set<CourseDto> findByStudentId(Long studentId) {
        Set<Classes> classes = studentRepository.findById(studentId).get().getClasses();
        Set<CourseDto> courses = new HashSet<>();
        for (Classes c : classes) {
            CourseDto course = new CourseDto();
            course.setTitle(c.getCourse().getTitle());
            course.setDescription(c.getCourse().getDescription());
            course.setName(c.getCourse().getName());
            course.setImage(c.getCourse().getImage().getData());
            courses.add(course);
        }
        return courses;
    }
// saveStudent() is used to store student details into database and
// returns random generated username and password containing 8 characters to Admin
    @Transactional
    public GeneratePassAndUsername saveStudent(StudentDto student) {
        GeneratePassAndUsername obj = usersService.saveUser2(student);
        Student student1 = new Student();
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setParentContact(student.getParentContact());
        student1.setPhone(student.getPhone());
        student1.setUsers(obj.getUsers());
        student1.setPayStatus(student.getPayStatus());
        student1.setEnrollmentDate(LocalDate.now());
        studentRepository.save(student1);
        return obj;
    }

    public boolean existsByEmail(String phone) {
        return studentRepository.existsByEmail(phone);
    }
}
