package application.service;

import application.dao.UserProfileDao;
import application.entity.UserProfile;

import javax.persistence.EntityManagerFactory;

public class UserProfileService {

    private final UserProfileDao userProfileDao;

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
