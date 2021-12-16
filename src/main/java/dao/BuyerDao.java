package dao;

import model.Buyer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class BuyerDao {
    SessionFactory factory;

    public BuyerDao(SessionFactory factory) {
        this.factory = factory;
    }

    public Buyer findById(Long id){
        Buyer buyer;
        try(Session session = factory.getCurrentSession()){
            session.getTransaction().begin();
            buyer = session.get(Buyer.class,id);
            session.getTransaction().commit();
        }
        return buyer;
    }

    public List<Buyer> findAll(){
        List<Buyer> buyers;
        try(Session session = factory.getCurrentSession()){
            session.getTransaction().begin();
            buyers = session.createQuery("select s from Product s",Buyer.class).getResultList();
            session.getTransaction().commit();
        }
        return buyers;
    }

    public void deleteById(Long id){
        try(Session session = factory.getCurrentSession()){
            session.getTransaction().begin();
            Buyer buyer = session.get(Buyer.class,id);
            session.delete(buyer);
            session.getTransaction().commit();
        }

    }

    public void saveOrUpdate(Buyer buyer){
        try(Session session = factory.getCurrentSession()){
            session.getTransaction().begin();
            session.save(buyer);
            session.getTransaction().commit();
        }
    }
}
