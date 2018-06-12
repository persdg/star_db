package control;

import entity.User;
import persistence.UserRepository;

public class LogInController {

    public boolean LogIn(String username, String password) {

        UserRepository UR = new UserRepository();
        return UR.isPresent(username,password);
    }

    public boolean SignUp(User user) {
        UserRepository UR = new UserRepository();
        return UR.signUp(user);
    }
}
