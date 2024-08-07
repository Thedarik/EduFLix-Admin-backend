package com.eduflix.eduflix.Service;

import com.eduflix.eduflix.Dto.CourseDto;
import com.eduflix.eduflix.Dto.StudentDto;
import com.eduflix.eduflix.Dto.StudentProfileEditRequest;
import com.eduflix.eduflix.Dto.StudentProfileResponseDto;
import com.eduflix.eduflix.Entity.Classes;
import com.eduflix.eduflix.Entity.Student;
import com.eduflix.eduflix.Entity.Users;
import com.eduflix.eduflix.Repository.StudentRepository;
import com.eduflix.eduflix.Repository.UsersRepository;
import com.eduflix.eduflix.controller.GeneratePassAndUsername;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final UsersService usersService;
    private final UsersRepository usersRepository;

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

    public StudentProfileResponseDto getStudentProfileByUsername(String username) {
        Optional<Users> user = usersService.findByUsername(username);
        if (user.isPresent()) {
            Users user1 = user.get();
            Student student = studentRepository.findByUsers(user1);
            String imageUrl = ServletUriComponentsBuilder.fromHttpUrl("http://localhost:8084/api/profile/image/")
                    .path(user1.getId().toString())
                    .toUriString();

            return new StudentProfileResponseDto(
                    student.getId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getEmail(),
                    student.getPhone(),
                    student.getParentContact(),
                    student.getUsers().getGender(),
                    student.getPayStatus(),
                    imageUrl
            );
        } else
            throw new UsernameNotFoundException("User not found");
    }

    @Transactional
    public String editStudentProfile(Long studentId,
                                     StudentProfileEditRequest request) {
        Optional<Student> student = studentRepository.findById(studentId);
        Optional<Users> user = usersRepository.findById(studentId);
        if (student.isPresent() && user.isPresent()) {
            Student student1 = student.get();
            Users user1 = user.get();
            if (request.getEmail() != null) {
                student1.setEmail(request.getEmail());
            }
            if (request.getPhone() != null) {
                student1.setPhone(request.getPhone());
            }
            if (request.getParentContact() != null) {
                student1.setParentContact(request.getParentContact());
            }
            if (request.getUsername() != null) {
                user1.setUsername(request.getUsername());
            }
            if (request.getGender() != null) {
                user1.setGender(request.getGender());
            }

            usersRepository.save(user1);
            studentRepository.save(student1);
            return "updated";
        } else
            throw new UsernameNotFoundException("User not found");
    }
}
