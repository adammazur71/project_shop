package org.example;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Customers.class)
                .addAnnotatedClass(Items.class)
                .addAnnotatedClass(Invoice.class)
                .addAnnotatedClass(InvoiceItems.class)
                .addAnnotatedClass(Employees.class)
                .addAnnotatedClass(Stock.class)
                .buildSessionFactory();
    }
}