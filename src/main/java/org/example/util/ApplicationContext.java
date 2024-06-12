package org.example.util;



import org.example.config.DataSource;
import org.example.entity.User;
import org.example.menu.loggedin_sub_menus.AllProductPage;
import org.example.menu.loggedin_sub_menus.CheckoutPage;
import org.example.menu.loggedin_sub_menus.LoggedInMenu;
import org.example.menu.LoginMenu;
import org.example.menu.MainMenu;
import org.example.menu.SignUpMenu;
import org.example.menu.loggedin_sub_menus.ShoppingBagPage;
import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.repository.BaseProductRepo;
import org.example.repository.BaseProductRepoImpl;
import org.example.repository.user.UserRepository;
import org.example.repository.user.UserRepositoryImpl;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;
import org.example.services.BaseProductService;
import org.example.services.BaseProductServiceImp;


import java.sql.Connection;

public class ApplicationContext {

    private static MainMenu mainMenu;

    private static final ApplicationContext INSTANCE = new ApplicationContext();


    private ApplicationContext() {
        Input input = new Input();
        Message message = new Message();
        Connection connection = DataSource.getConnection();


        UserRepository userRepo = new UserRepositoryImpl(connection, User::new);
        BaseProductRepo baseProductRepo = new BaseProductRepoImpl(connection);
        AuthHolder authHolder = new AuthHolder();


        UserService userService = new UserServiceImpl(userRepo,authHolder,userRepo);
        BaseProductService baseProductService = new BaseProductServiceImp(baseProductRepo);

        SignUpMenu signUpMenu = new SignUpMenu(input, message, userService);
        ShoppingBagPage shoppingBagPage = new ShoppingBagPage(input,message);
        CheckoutPage checkoutPage = new CheckoutPage(input,message);
        AllProductPage allProductPage = new AllProductPage(input,message,baseProductService,shoppingBagPage,checkoutPage);
        LoggedInMenu loggedInMenu = new LoggedInMenu(input, message,shoppingBagPage,allProductPage,checkoutPage);


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
