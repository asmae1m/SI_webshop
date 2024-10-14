package com.webshop.dao;

import com.webshop.models.Product;
import com.webshop.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDAO {

	public Product getProductById(String productID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Product product = session.get(Product.class, productID);
        session.close();
        return product;
    }

    public void saveProduct(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(product);
        tx.commit();
        session.close();
    }

    public void updateProduct(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(product);
        tx.commit();
        session.close();
    }

    public List<Product> getProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> products = session.createQuery("from Product", Product.class).list();
        session.close();
        return products;
    }
    
    public void deleteProduct(Long productID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        // Retrieve the product to be deleted
        Product product = session.get(Product.class, productID);
        if (product != null) {
            session.delete(product);  // Delete the product
        }
        
        tx.commit();
        session.close();
    }
    
    public void saveAllProducts(List<Product> products) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        for (Product product : products) {
            
             session.save(product);
             
        }

        tx.commit();
        session.close();
    }

}