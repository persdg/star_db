package control;

import entity.User;
import exception.UserNotFoundException;
import persistence.UserRepository;

public class LogInController {

    public static boolean LogIn(String username, String password) throws UserNotFoundException {

        UserRepository UR = new UserRepository();
        return UR.LogIn(username,password);
    }

    public static boolean SignUp(User user) {
        UserRepository UR = new UserRepository();
        return UR.signIn(user);
    }
}
