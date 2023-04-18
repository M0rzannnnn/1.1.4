package jm.task.core.jdbc.util;



import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties prop = new Properties();
                Configuration configuration = new Configuration();
                prop.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/Users2");
                prop.put("dialect", "org.hibernate.dialect.MySQLDialect");
                prop.put("hibernate.connection.username", "root");
                prop.put("hibernate.connection.password", "Vazo20907006");
                prop.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                prop.put("show_sql", true);
                prop.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                prop.put(Environment.HBM2DDL_AUTO, "");

                configuration.setProperties(prop);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("OK");

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("NE OK");
            }
        }
        return sessionFactory;
    }


    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/Users";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Vazo20907006";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection НЕ ОК");
        }
        return connection;
    }
}
