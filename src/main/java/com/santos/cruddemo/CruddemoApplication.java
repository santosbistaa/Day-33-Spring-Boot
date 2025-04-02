package com.santos.cruddemo;

import com.santos.cruddemo.dao.AppDAO;
import com.santos.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner-> {

            // createCourseAndStudents(appDAO);

            // findCourseAndStudents(appDAO);

            // findStudentAndCourses(appDAO);

            // addMoreCoursesForStudents(appDAO);
            
            // deleteCourse(appDAO);

            deleteStudent(appDAO);


       };
    }

    private void deleteStudent(AppDAO appDAO) {

        int theId = 1;
        System.out.println("Deleting student id: " + theId);

        appDAO.deleteStudentById(theId);

        System.out.println("Done!");
    }

    private void deleteCourse(AppDAO appDAO) {

        int theId = 10;

        System.out.println("Deleting course id: " + theId);

        appDAO.deleteCourseById(theId);

        System.out.println("Done!!");
    }

    private void addMoreCoursesForStudents(AppDAO appDAO) {

        int theId = 2;
        Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

        // create more courses
        Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
        Course tempCourse2 = new Course("Atari 2600 - Game Development");

        // add courses to student
        tempStudent.addCourse(tempCourse1);
        tempStudent.addCourse(tempCourse2);

        System.out.println("Updating student: " + tempStudent);
        System.out.println("associated courses:" + tempStudent.getCourses());

        appDAO.update(tempStudent);
        System.out.println("Done!");
    }

    private void findStudentAndCourses(AppDAO appDAO) {

        int theId = 2;
        Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

        System.out.println("Loaded student: " + tempStudent);
        System.out.println("Courses: " + tempStudent.getCourses());

        System.out.println("Done!!");
    }

    private void findCourseAndStudents(AppDAO appDAO) {

        int theId =10;
        Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);

        System.out.println("Loaded course: " + tempCourse);
        System.out.println("Students: " + tempCourse.getStudents());

        System.out.println("Done!!");
    }

    private void createCourseAndStudents(AppDAO appDAO) {

        // create a course
        Course tempCourse = new Course ("Pacman - How To Score One Million Points");

        // create the students
        Student tempStudent1 = new Student("Santos", "Bista", "santos@gmail.com");
        Student tempStudent2 = new Student("Sagar", "Thapa", "sagar@gmail.com");

        // add students to the course
        tempCourse.addStudent(tempStudent1);
        tempCourse.addStudent(tempStudent2);

        // save the course and associated students
        System.out.println("Saving the course: " + tempCourse);
        System.out.println("associated students: " + tempCourse.getStudents());

        appDAO.save(tempCourse);
        System.out.println("Done!");
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {

        int theId = 10;

        System.out.println("Deleting course id: " + theId);

        appDAO.deleteCourseById(theId);

        System.out.println("Done!!");
    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {

        // get the course and reviews
        int theId = 10;
        Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

        // print the course
        System.out.println(tempCourse);

        // print the reviews
        System.out.println(tempCourse.getReviews());

        System.out.println("Done!");
    }

    private void createCourseAndReviews(AppDAO appDAO) {

        // create a course
        Course tempCourse = new Course("Pacman - How to Score One Million Points");

        // create a reviews
        tempCourse.addReview(new Review("Great course ... loved it!"));
        tempCourse.addReview(new Review("Cool course, job well done,"));
        tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));

        // save the course ... abd leverage the cascade all
        System.out.println("Saving the course");
        System.out.println(tempCourse);
        System.out.println(tempCourse.getReviews());

        appDAO.save(tempCourse);
        System.out.println("Done!!!");

    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

        int theId = 1;

        // find the instructor
        System.out.println("Finding instructor id:" + theId);
        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated courses: " + tempInstructor.getCourses());

        System.out.println("Done!");
    }

    private void findCoursesForInstructor(AppDAO appDAO) {
        int theId = 1;
        // find instructor
        System.out.println("Finding instructor id: " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);

        // find courses for instructor
        System.out.println("Finding courses for instructor id: " + theId);
        List<Course> courses = appDAO.findCoursesByInstructorId(theId);

        // associate the objects
        tempInstructor.setCourses(courses);

        System.out.println("the associated courses:" + tempInstructor.getCourses());

        System.out.println("Done!!!");
    }

    private void findInstructorWithCourses(AppDAO appDAO) {

        int theId = 1;
        System.out.println("Finding instructor id: " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated courses: " + tempInstructor.getCourses());

        System.out.println("Done!");
    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        // create the instructor
        Instructor tempInstructor =
                new Instructor("Susan","Bista","susan@gmail.com");

        // create the instructor detail
        InstructorDetail tempInstructorDetail =
                new InstructorDetail("http://www.susan.com/youtube",
                        "Bike!!!");

        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);


        // create some courses
        Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
        Course tempCourse2 = new Course("The Football Masterclass");

        // add courses to the instructor
        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        // save the instructor
        //
        // NOTE: this will ALSO save the courses
        // because of CascadeType.PERSIST
        //
        System.out.println("Saving instructor:" + tempInstructor);
        System.out.println("The courses: " +tempInstructor.getCourses());
        appDAO.save(tempInstructor);

        System.out.println("Done!");
    }



    private void deleteInstructorDetail(AppDAO appDAO) {

        int theId = 4;
        System.out.println("Deleting the instructor detail id: " +theId);

        appDAO.deleteInstructorDetailById(theId);

        System.out.println("Done!!");
    }

    private void findInstructorDetail(AppDAO appDAO) {
        // get the instructor detail object
        int theId = 2;
        InstructorDetail tempInstructorDetail = appDAO.findInstructionDetailById(theId);

        // print the instructor detail
        System.out.println("tempInstructorDetail: " + tempInstructorDetail);

        // print the associated instructor
        System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

        System.out.println("Done!!");
    }

    private void deleteInstructor(AppDAO appDAO) {

        int theId = 3;
        System.out.println("Deleting instructor id: " + theId);

        appDAO.deleteInstructorById(theId);

        System.out.println("Done!");
    }

    private void findInstructor(AppDAO appDAO) {

        int theId = 2;
        System.out.println("Finding instructor id: " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associate instructorDetail only: " +tempInstructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {
/*
		// create the instructor
		Instructor tempInstructor =
				new Instructor("Santos","Bista","santos@gmail.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("http://www.santos.com/youtube",
						"Coding!!!");
*/

        // create the instructor
        Instructor tempInstructor =
                new Instructor("Sagar","Thapa","sagar@gmail.com");

        // create the instructor detail
        InstructorDetail tempInstructorDetail =
                new InstructorDetail("http://www.sagar.com/youtube",
                        "Guitar!!!");

        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // save the instructor
        //
        // NOTE: this will Also save the details object
        // because of CascadeType.ALL
        //
        System.out.println("Saving instructor: " + tempInstructor);
        appDAO.save(tempInstructor);

        System.out.println("Done!");
    }

}
