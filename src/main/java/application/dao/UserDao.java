package application.dao;

import application.entity.User;

import java.util.List;

import  javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

public class UserDao extends GenericDao<User> {

    private final EntityManagerFactory factory;

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

    // verificare username inainte de register
    public boolean isUsernameTaken(String username) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> q = cb.createQuery(Long.class);

        Root<User> c = q.from(User.class);
        q.select(cb.count(c)).where(cb.equal(c.get("username"), username));
        TypedQuery<Long> query = em.createQuery(q);

        Long count = query.getSingleResult();
        em.close();

        // daca mai mare de 0 return true, adica user taken
        return count > 0;
    }

}
