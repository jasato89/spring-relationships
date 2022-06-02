package com.ironhack.relationships.repository;

import com.ironhack.relationships.models.Course;
import com.ironhack.relationships.models.Invoice;
import com.ironhack.relationships.models.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    StudentsRepository studentsRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    CourseRepository courseRepository;

    @BeforeEach
    void init() {

        Invoice invoicePedro = new Invoice("Pago curso 2022", false);
        Invoice invoiceMotoko = new Invoice("Pago Curso Motoko", false);

        invoiceRepository.save(invoicePedro);
        invoiceRepository.save(invoiceMotoko);

        Student student1 = new Student("Pedro", "Ortega-Messi", invoicePedro);
        Student student2 = new Student("Motoko", "Namba", invoiceMotoko);

        studentsRepository.save(student1);
        studentsRepository.save(student2);

        Course course = new Course(
                "Introduccion a Java", 60, "Jaume");

        Set<Student> students = new HashSet<>();
        students.add(student1);
        students.add(student2);

        course.setStudents(students);

        courseRepository.save(course);


    }



/*
    @AfterEach
    void tearDown() {
        courseRepository.deleteAll();
        studentsRepository.deleteAll();
        invoiceRepository.deleteAll();
    }

*/

    @Test
    void checkStudent() {
        assertEquals("Pedro", studentsRepository.findAll().get(0).getFirstName());
        assertEquals("Pago curso 2022",
                studentsRepository.findAll().get(0).getInvoice().getInvoiceTitle());

        assertEquals("Pedro", invoiceRepository.findAll().get(0).getStudents().getFirstName());

    }

}

