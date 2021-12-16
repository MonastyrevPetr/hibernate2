package dao;

import model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProductDao {
    SessionFactory factory;

    public ProductDao(SessionFactory factory) {
        this.factory = factory;
    }

    public Product findById(Long id){
        Product product;
        try(Session session = factory.getCurrentSession()){
            session.getTransaction().begin();
            product = session.get(Product.class, id);
            session.getTransaction().commit();
        }
        return product;
    }

    public  List<Product> findAll(){
        List<Product> products;
        try(Session session = factory.getCurrentSession()){
            session.getTransaction().begin();
            products = session.createQuery("select s from Product s ",Product.class).getResultList();
            session.getTransaction().commit();
        }
        return products;
    }

    public void deleteById(Long id){
        try(Session session = factory.getCurrentSession()){
            session.getTransaction().begin();
            Product product = session.get(Product.class,id);
            session.delete(product);
            session.getTransaction().commit();
        }

    }

    public void saveOrUpdate(Product product){
        try(Session session = factory.getCurrentSession()){
            session.getTransaction().begin();
            session.save(product);
            session.getTransaction().commit();
        }
    }
}
