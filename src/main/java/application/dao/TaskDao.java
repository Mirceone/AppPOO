package application.dao;

import application.entity.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TaskDao extends GenericDao<Task> {

    private final EntityManagerFactory factory;

    public TaskDao(EntityManagerFactory factory) {
        super(Task.class);
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

    public List<Task> getAllTasksByUser(int userId) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Task> cq = cb.createQuery(Task.class);

            Root<Task> root = cq.from(Task.class);
            cq.select(root).where(cb.equal(root.get("idUser").get("id"), userId));

            return em.createQuery(cq).getResultList();
        } finally {
            em.close();
        }
    }
}
