package com.expensify.persistenceLayer;
import com.expensify.database.MySqlDatabase;
import com.expensify.database.IDatabase;
import com.expensify.model.User;

import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



@Component
public class UserDAOService implements IUserDAOService {
    private final IDatabase database;

    public UserDAOService() {
      //  this.database = database;
        this.database = MySqlDatabase.instance();
    }

    public int saveUser(User user) throws SQLException {
        List<Object> parameterList = new ArrayList<>();
        int userId = 0;
        try {
            parameterList.add(user.getFirstName());
            parameterList.add(user.getLastName());
            parameterList.add(user.getEmail());
            parameterList.add(user.getPassword());
            parameterList.add(user.getContact());

            ResultSet resultSet = database.executeProcedure("CALL register_user(?, ?, ?, ?, ?)", parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {

                    userId = resultSet.getInt("user_id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }

        return userId;
    }

    public String encode(String password) {

        return password;
    }
    public int verifyUser(User user) throws SQLException {
        List<Object> parameterList = new ArrayList<>();
        int userId = 0;
        try {
            parameterList.add(user.getEmail());

            ResultSet resultSet = database.executeProcedure("CALL get_user_credential(?)", parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    if (resultSet.getString("password").equals(user.getPassword())) {
                        userId = resultSet.getInt("user_id");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return userId;
    }

    public boolean updatePassword(String email, String password)  {
        List<Object> parameterList = new ArrayList<>();
        boolean passwordUpdated = false;
        try {
            parameterList.add(email);
            parameterList.add(password);
            /*User.BCryptPasswordEncoder passwordEncoder = new User.BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);*/
            ResultSet resultSet = database.executeProcedure("CALL update_user_password(?,?)", parameterList);
            if (resultSet != null) {
                passwordUpdated = true;
                }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                database.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return passwordUpdated;
    }



    public boolean findByEmail(String email) {
        List<Object> parameterList = new ArrayList<>();
        parameterList.add(email);
        boolean userExist = false;
        try {
            ResultSet resultSet = database.executeProcedure("CALL check_user_exist(?)", parameterList);
            if (resultSet != null) {
               userExist = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                database.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return userExist;
    }



}
