package com.santos.cruddemo.dao;

import com.santos.cruddemo.entity.Course;
import com.santos.cruddemo.entity.Instructor;
import com.santos.cruddemo.entity.InstructorDetail;
import com.santos.cruddemo.entity.Student;

import java.util.List;

public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructionDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    // find courses for an instructor
    List<Course> findCoursesByInstructorId(int theId);

    // JOIN FETCH
    Instructor findInstructorByIdJoinFetch(int theId);

    // update instructor
    void update(Instructor tempInstructor);

    // update course
    void update(Course tempCourse);

    Course findCourseById(int theId);

    void deleteCourseById(int theId);

    void save(Course theCourse);

    Course findCourseAndReviewsByCourseId(int theId);

    Course findCourseAndStudentsByCourseId(int theId);

    Student findStudentAndCoursesByStudentId(int theId);

    void update(Student tempStudent);

    void deleteStudentById(int theId);

}
