package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {
    }
    public void createUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        try (Statement statement = connection.createStatement()) {
            String mysql = "CREATE table if not exists User (id bigint primary key auto_increment, " +
                    "name varchar(255), " +
                    "lastName varchar(255), " +
                    "age tinyint)";
            statement.execute(mysql);
        } catch (SQLException e) {
            System.out.println("Такая таблица уже была создана");
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    public void dropUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        String mysql = "DROP TABLE USER";
        try (Statement statement = connection.createStatement()) {
            statement.execute(mysql);
        } catch (SQLException e) {
            System.out.println("Такой таблицы не существует");
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection connection = Util.getConnection();
        String mysql = "INSERT INTO USER (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(mysql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        Connection connection = Util.getConnection();
        String mysql = "DELETE FROM USER WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(mysql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    public List<User> getAllUsers() throws SQLException {
        Connection connection = Util.getConnection();
        List<User> userList = new ArrayList<>();
        String mysql = "SELECT * FROM USER";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(mysql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return userList;
    }
    public void cleanUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        String mysql = "TRUNCATE TABLE USER";
        try (Statement statement = connection.createStatement()) {
            statement.execute(mysql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
