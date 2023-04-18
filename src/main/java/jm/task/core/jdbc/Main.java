package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService2 = new UserServiceImpl(new UserDaoJDBCImpl());

        userService2.createUsersTable();

        userService2.saveUser("Vadim", "Vinogradov", (byte) 32);
        userService2.saveUser("Polina", "Tikhova", (byte) 23);
        userService2.saveUser("Vladimir", "Semennikov", (byte) 35);
        userService2.saveUser("Kristina", "Semennikova", (byte) 32);

        List<User> list2 = userService2.getAllUsers();
        for (User i : list2) {
            System.out.println(i);
        }

        userService2.cleanUsersTable();

        userService2.dropUsersTable();
        UserService userService = new UserServiceImpl(new UserDaoHibernateImpl());

        userService.createUsersTable();

        userService.saveUser("Vadim", "Vinogradov", (byte) 32);
        userService.saveUser("Polina", "Tikhova", (byte) 23);
        userService.saveUser("Vladimir", "Semennikov", (byte) 35);
        userService.saveUser("Kristina", "Semennikova", (byte) 32);

        List<User> list = userService.getAllUsers();
        for (User i: list) {
            System.out.println(i);
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();


    }
}
