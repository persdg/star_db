package control;

import entity.User;
import persistence.UserRepository;

public class LogInController {

    public static boolean LogIn(String username, String password) {

        UserRepository UR = new UserRepository();
        return UR.isPresent(username,password);
    }

    public static boolean SignUp(User user) {
        UserRepository UR = new UserRepository();
        return UR.signIn(user);
    }
}
