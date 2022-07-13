package com.expensify.persistenceLayer;
import com.expensify.database.Database;
import com.expensify.database.IDatabase;
import com.expensify.model.Authentication;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Component
public class AuthenticationDAO {
    private final IDatabase database;

    public AuthenticationDAO() {
        this.database = Database.getInstance();
    }

    public int saveUser(Authentication authentication) throws SQLException {
        List<Object> parameterList = new ArrayList<>();
        int userId = 0;
        try {
            parameterList.add(authentication.getFirstName());
            parameterList.add(authentication.getLastName());
            parameterList.add(authentication.getEmail());
            parameterList.add(authentication.getPassword());
            parameterList.add(authentication.getContact());

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

    public int verifyUser(Authentication authentication) throws SQLException {
        List<Object> parameterList = new ArrayList<>();
        int userId = 0;
        try {
            parameterList.add(authentication.getEmail());

            ResultSet resultSet = database.executeProcedure("CALL get_user_credential(?)", parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    if (resultSet.getString("password").equals(authentication.getPassword())) {
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
