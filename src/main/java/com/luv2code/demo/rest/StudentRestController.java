package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    // define @PostConstruct to load the student data.... only once!
    @PostConstruct
    public void loadData(){

        theStudents = new ArrayList<>();

        theStudents.add( new Student("Kajal", "Modi"));
        theStudents.add( new Student("Hriday", "Jain"));
        theStudents.add( new Student("Manshi", "Kumari"));
    }

    // define endpoint for "/students" - return a list of students
    @GetMapping("/students")
    public List<Student> getStudents(){

//        List<Student> theStudents = new ArrayList<>();
//
//        theStudents.add( new Student("Kajal", "Modi"));
//        theStudents.add( new Student("Manshi", "Kumari"));
//        theStudents.add( new Student("Hriday", "Jain"));
        
        return theStudents;
    }

    // define endpoint "/students/{studentId}" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        //just index into the list ... keep it simple for now

        // check the StudentId against list size
        if((studentId >= theStudents.size()) || (studentId < 0)){

            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        return theStudents.get(studentId);
    }

    // add an exception handler using @ExceptionHandler

}
