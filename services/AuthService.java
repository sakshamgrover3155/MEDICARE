// services/AuthService.java
package services;

import models.User;
import datastructures.BinarySearchTree;
import java.util.Comparator;
import datastructures.LinkedList;

public class AuthService {
    private LinkedList<User> users;
    private BinarySearchTree<User> usernameBST;

    public AuthService() {
        users = new LinkedList<>();
        usernameBST = new BinarySearchTree<>(Comparator.comparing(User::getUsername));
    }

    public void register(User user) {
        users.add(user);
        usernameBST.insert(user);
    }

    public User authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.verifyPassword(password)) {
                return user;
            }
        }
        return null;
    }
}
