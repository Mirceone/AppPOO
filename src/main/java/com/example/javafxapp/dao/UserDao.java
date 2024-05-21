package com.example.javafxapp.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;

import com.example.javafxapp.model.User;

public class UserDao extends GenericDao<User> {

    private EntityManagerFactory factory;

    public UserDao(EntityManagerFactory factory) {
        super(User.class);
        this.factory = factory;
    }

    @Override
    public EntityManager getEntityManager() {
        try {
            return factory.createEntityManager();
        } catch (Exception ex) {
            System.out.println("The entity manager cannot be created!");
            return null;
        }
    }

    // for login
    public List<User> find(String name) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> q = cb.createQuery(User.class);

        Root<User> c = q.from(User.class);
        ParameterExpression<String> paramName = cb.parameter(String.class);
        q.select(c).where(cb.equal(c.get("username"), paramName));
        TypedQuery<User> query = em.createQuery(q);
        query.setParameter(paramName, name);

        List<User> results = query.getResultList();
        return results;
    }
}
