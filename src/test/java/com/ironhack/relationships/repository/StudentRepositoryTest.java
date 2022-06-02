package com.ironhack.relationships.repository;

import com.ironhack.relationships.models.Invoice;
import com.ironhack.relationships.models.Students;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class StudentRepositoryTest {

    @Autowired StudentsRepository studentsRepository;
    @Autowired InvoiceRepository invoiceRepository;

    @BeforeEach
    void init() {

        invoiceRepository.save(new Invoice("Pago curso 2022", false));
        studentsRepository.save(
                new Students("Pedro", "Ortega-Messi", invoiceRepository.findAll().get(0)));
    }


    @AfterEach
    void tearDown() {
        studentsRepository.deleteAll();
        invoiceRepository.deleteAll();
    }
    
    @Test
    void checkStudent() {
        assertEquals("Pedro", studentsRepository.findAll().get(0).getFirstName());
        assertEquals("Pago curso 2022",
                studentsRepository.findAll().get(0).getInvoice().getInvoiceTitle());

    }


}
