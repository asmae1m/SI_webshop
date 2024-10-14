package com.webshop.dao;

import com.webshop.models.Customer;
import com.webshop.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerDAO {

    public void saveCustomer(Customer customer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Customer getCustomerByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Customer where email = :email", Customer.class)
                          .setParameter("email", email)
                          .uniqueResult();
        }
    }

    public List<Customer> getCustomers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Customer", Customer.class).list();
        }
    }
    
    public void updateCustomer(Customer customer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(customer); // Update the customer in the database
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
