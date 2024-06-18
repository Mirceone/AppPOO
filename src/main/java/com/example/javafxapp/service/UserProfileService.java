package com.example.javafxapp.service;

import com.example.javafxapp.dao.UserProfileDao;
import com.example.javafxapp.entity.UserProfile;

import javax.persistence.EntityManagerFactory;

public class UserProfileService {

    private UserProfileDao userProfileDao;

    public UserProfileService(EntityManagerFactory factory) {
        userProfileDao = new UserProfileDao(factory);
    }

    public void addUserProfile(UserProfile newUserProfile) {
        userProfileDao.create(newUserProfile);
    }

    public void updateUserProfile(UserProfile updatedUserProfile) {
        userProfileDao.update(updatedUserProfile);
    }
}
