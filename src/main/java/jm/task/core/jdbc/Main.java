package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl(new UserDaoJDBCImpl());

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
