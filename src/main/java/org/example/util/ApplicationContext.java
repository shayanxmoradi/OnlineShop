package org.example.util;



import org.example.config.DataSource;
import org.example.menu.LoggedInMenu;
import org.example.menu.LoginMenu;
import org.example.menu.MainMenu;
import org.example.menu.SignUpMenu;
import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.repository.user.UserRepository;
import org.example.repository.user.UserRepositoryImpl;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;


import java.sql.Connection;

public class ApplicationContext {

    private static MainMenu mainMenu;

    private static final ApplicationContext INSTANCE = new ApplicationContext();


    private ApplicationContext() {
        Input input = new Input();
        Message message = new Message();
        Connection connection = DataSource.getConnection();

        UserRepository userRepo = new UserRepositoryImpl(connection);
        AuthHolder authHolder = new AuthHolder();


        UserService userService = new UserServiceImpl(userRepo,authHolder);
        SignUpMenu signUpMenu = new SignUpMenu(input, message, userService);

        LoggedInMenu loggedInMenu = new LoggedInMenu(input, message);
        LoginMenu loginMenu = new LoginMenu(input, message, userService, authHolder, loggedInMenu);

         mainMenu = new MainMenu(input, message, signUpMenu, loginMenu);

    }

    public static ApplicationContext getInstance() {
        return INSTANCE;
    }

    public MainMenu getMenu() {
        return mainMenu;
    }


}
