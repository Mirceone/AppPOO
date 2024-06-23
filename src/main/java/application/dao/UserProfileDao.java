package application.dao;

import application.entity.UserProfile;

import javax.persistence.*;

public class UserProfileDao extends GenericDao<UserProfile> {

    private final EntityManagerFactory factory;

    public UserProfileDao(EntityManagerFactory factory) {
        super(UserProfile.class);
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

    // Additional methods specific to UserProfile if needed
}
