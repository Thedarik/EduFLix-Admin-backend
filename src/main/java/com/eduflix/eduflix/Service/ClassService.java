package com.eduflix.eduflix.Service;

import com.eduflix.eduflix.Dto.ClassesDto;
import com.eduflix.eduflix.Entity.Classes;
import com.eduflix.eduflix.Entity.Course;
import com.eduflix.eduflix.Entity.Teacher;
import com.eduflix.eduflix.Repository.ClassesRepository;
import com.eduflix.eduflix.Repository.CourseRepository;
import com.eduflix.eduflix.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassService {

    private final ClassesRepository classesRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public boolean exists(Long id) {
        return classesRepository.existsById(id);
    }

    public void deleteClass(Long classId) {
        classesRepository.deleteById(classId);
    }

    public Classes saveNewClass(ClassesDto classesDto) {
        if (courseRepository.existsById(classesDto.getCourseId()) && teacherRepository.existsById(classesDto.getTeacherId())) {
            Course course = courseRepository.findById(classesDto.getCourseId()).get();
            Teacher teacher = teacherRepository.findById(classesDto.getTeacherId()).get();
            Classes classes = new Classes();
            classes.setClassFee(classesDto.getClassFee());
            classes.setName(classesDto.getName());
            classes.setClassTime(classesDto.getClassTime());
            classes.setStudentNumber(classesDto.getStudentNumber());
            classes.setDays(classesDto.getDays());
            classes.setCourse(course);
            classes.setTeacher(teacher);
            return classesRepository.save(classes);
        }
        return null;
    }
}
