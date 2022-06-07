package com.ironhack.relationships.repository;

import com.ironhack.relationships.models.Book;
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

    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    void init() {

        Invoice invoicePedro = new Invoice("Pago curso 2022", false);
        Invoice invoiceMotoko = new Invoice("Pago Curso Motoko", false);

        Student student1 = new Student("Pedro", "Ortega-Messi", invoicePedro);
        Student student2 = new Student("Motoko", "Namba", invoiceMotoko);

        Book book1 = new Book("Harry Potter", "JK Rowling");
        Book book2 = new Book("Manolito Gafotas", "Elvira Lindo");

        Set<Book> booksPedro = Set.of(book1, book2);
        Set<Book> booksMotoko = Set.of(book1);

        student1.setBooks(booksPedro);
        student2.setBooks(booksMotoko);

        bookRepository.save(book1);
        bookRepository.save(book2);

        invoiceRepository.save(invoicePedro);
        invoiceRepository.save(invoiceMotoko);


        Course course = new Course(
                "Introduccion a Java", 60, "Jaume");

        Set<Student> students = new HashSet<>();
        students.add(student1);
        students.add(student2);

        courseRepository.save(course);

        course.setStudents(students);

        student1.setCourse(course);
        student2.setCourse(course);

        studentsRepository.save(student1);
        studentsRepository.save(student2);



    }




    @AfterEach
    void tearDown() {
        studentsRepository.deleteAll();
        invoiceRepository.deleteAll();
        courseRepository.deleteAll();
    }



    @Test
    void checkStudent() {
        assertEquals("Pedro", studentsRepository.findAll().get(0).getFirstName());
        assertEquals("Pago curso 2022",
                studentsRepository.findAll().get(0).getInvoice().getInvoiceTitle());

        assertEquals("Pedro", invoiceRepository.findAll().get(0).getStudents().getFirstName());

    }

}

