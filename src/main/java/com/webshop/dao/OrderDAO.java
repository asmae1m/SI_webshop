package com.webshop.dao;

import com.webshop.models.Customer;
import com.webshop.models.Order;
import com.webshop.models.OrderItem;
import com.webshop.utils.HibernateUtil;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class OrderDAO {

    public void saveOrder(Order order) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(order);
            for (OrderItem item : order.getOrderItems()) {
                item.setOrder(order);  // Set order reference for order items
                session.save(item);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public List<Order> getOrdersByCustomer(Customer customer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Order where customer.id = :customerId", Order.class)
                    .setParameter("customerId", customer.getId())
                    .list();
        }
    }

}
