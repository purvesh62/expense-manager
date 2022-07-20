package com.expensify.persistenceLayer;
import com.expensify.database.Database;
import com.expensify.database.IDatabase;
import com.expensify.model.User;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Component
public class UserDAOService {
    private final IDatabase database;

    public UserDAOService() {
        this.database = Database.instance();
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
}
