package demo;

import entity.Product;
import util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class HQLDemo {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        // -----------------------------
        // Task 3a - Sort by Price ASC
        // -----------------------------
        Query<Product> q1 = session.createQuery(
                "FROM Product ORDER BY price ASC", Product.class);

        List<Product> list1 = q1.list();

        System.out.println("\nProducts Sorted By Price ASC");
        for(Product p : list1) {
            System.out.println(p.getName()+" "+p.getPrice());
        }

        // -----------------------------
        // Task 3b - Sort by Price DESC
        // -----------------------------
        Query<Product> q2 = session.createQuery(
                "FROM Product ORDER BY price DESC", Product.class);

        List<Product> list2 = q2.list();

        System.out.println("\nProducts Sorted By Price DESC");
        for(Product p : list2) {
            System.out.println(p.getName()+" "+p.getPrice());
        }

        // -----------------------------
        // Task 4 - Sort by Quantity
        // -----------------------------
        Query<Product> q3 = session.createQuery(
                "FROM Product ORDER BY quantity DESC", Product.class);

        List<Product> list3 = q3.list();

        System.out.println("\nProducts Sorted By Quantity");
        for(Product p : list3) {
            System.out.println(p.getName()+" Quantity: "+p.getQuantity());
        }

        // -----------------------------
        // Task 5a - Pagination First 3
        // -----------------------------
        Query<Product> q4 = session.createQuery(
                "FROM Product", Product.class);

        q4.setFirstResult(0);
        q4.setMaxResults(3);

        List<Product> list4 = q4.list();

        System.out.println("\nFirst 3 Products");
        for(Product p : list4) {
            System.out.println(p.getName());
        }

        // -----------------------------
        // Task 5b - Pagination Next 3
        // -----------------------------
        Query<Product> q5 = session.createQuery(
                "FROM Product", Product.class);

        q5.setFirstResult(3);
        q5.setMaxResults(3);

        List<Product> list5 = q5.list();

        System.out.println("\nNext 3 Products");
        for(Product p : list5) {
            System.out.println(p.getName());
        }

        // -----------------------------
        // Task 6a - Count Products
        // -----------------------------
        Query<Long> q6 = session.createQuery(
                "SELECT COUNT(p) FROM Product p", Long.class);

        Long count = q6.uniqueResult();

        System.out.println("\nTotal Products: "+count);

        // -----------------------------
        // Task 6d - Min & Max Price
        // -----------------------------
        Query<Object[]> q7 = session.createQuery(
                "SELECT MIN(price), MAX(price) FROM Product", Object[].class);

        Object[] result = q7.uniqueResult();

        System.out.println("\nMin Price: "+result[0]);
        System.out.println("Max Price: "+result[1]);

        // -----------------------------
        // Task 7 - GROUP BY Description
        // -----------------------------
        Query<Object[]> q8 = session.createQuery(
                "SELECT description, COUNT(*) FROM Product GROUP BY description");

        List<Object[]> list8 = q8.list();

        System.out.println("\nProducts Grouped By Category");

        for(Object[] obj : list8){
            System.out.println(obj[0]+" : "+obj[1]);
        }

        // -----------------------------
        // Task 8 - Price Range Filter
        // -----------------------------
        Query<Product> q9 = session.createQuery(
                "FROM Product WHERE price BETWEEN 20 AND 100", Product.class);

        List<Product> list9 = q9.list();

        System.out.println("\nProducts Between 20 and 100");

        for(Product p : list9){
            System.out.println(p.getName()+" "+p.getPrice());
        }

        // -----------------------------
        // Task 9 - LIKE Query
        // -----------------------------
        Query<Product> q10 = session.createQuery(
                "FROM Product WHERE name LIKE 'D%'", Product.class);

        List<Product> list10 = q10.list();

        System.out.println("\nProducts Starting With D");

        for(Product p : list10){
            System.out.println(p.getName());
        }

        session.close();
        factory.close();
    }
}