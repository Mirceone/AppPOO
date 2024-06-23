package application.session;

import application.entity.User;

public class SessionManager {
    private static SessionManager instance;
    private User currentUser;

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
//        currentUser.setUsername(capitalizeFirstLetter(currentUser.getUsername()));
        //momentan nu mai facem uppercase aici, poate mai folosim si altundeva current user
    }

    public void clearCurrentUser() {
        this.currentUser = null;
    }

//    public static String capitalizeFirstLetter(String str) {
//        if (str == null || str.isEmpty()) {
//            return str;  // handle null or empty string
//        }
//        return str.substring(0, 1).toUpperCase() + str.substring(1);
//    }
}
