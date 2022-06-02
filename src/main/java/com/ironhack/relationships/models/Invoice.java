package com.ironhack.relationships.models;

import javax.persistence.*;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String invoiceTitle;
    private boolean isPaid;
    @OneToOne(mappedBy = "invoice")
    private Student student;




    public Invoice(String invoiceTitle, boolean isPaid) {
        this.invoiceTitle = invoiceTitle;
        this.isPaid = isPaid;
    }

    public Invoice() {
    }


    public Student getStudents() {
        return student;
    }

    public void setStudents(Student student) {
        this.student = student;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
