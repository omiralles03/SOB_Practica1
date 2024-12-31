package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.User;
import deim.urv.cat.homework2.controller.UserForm;

public interface UserService {
    
    public User findUserByEmail(String email);
    public boolean addUser(UserForm user);
}
