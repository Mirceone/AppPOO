package com.example.javafxapp.dao;

import com.example.javafxapp.entity.UserProfile;

import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

public class UserProfileDao extends GenericDao<UserProfile> {

    private EntityManagerFactory factory;

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
