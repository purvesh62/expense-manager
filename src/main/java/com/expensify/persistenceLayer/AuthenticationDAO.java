package com.expensify.persistenceLayer;

import com.expensify.database.IDatabase;
import com.expensify.model.Authentication;
import com.expensify.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Component
public class AuthenticationDAO {
    private final IDatabase mySqlDatabaseManager;

    @Autowired
    public AuthenticationDAO(IDatabase mySqlDatabaseManager) {
        this.mySqlDatabaseManager = mySqlDatabaseManager;
    }

    public List<Authentication> getAllUserLogins(int user_id, String firstname, String lastname, String email, String password, String contact) throws SQLException {
        List<Authentication> userAuthenticationList = new ArrayList<>();
        try {
            List<Object> parameterList = new ArrayList<>();
            parameterList.add(user_id);
            parameterList.add(firstname);
            parameterList.add(lastname);
            parameterList.add(email);
            parameterList.add(password);
            parameterList.add(contact);

            ResultSet resultSet = mySqlDatabaseManager.executeProcedure("CALL get_all_user_authentication(?, ?, ?, ?, ?, ?)", parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    Authentication authentication = new Authentication();
                    authentication.setUser_id(resultSet.getInt("user_id"));
                    authentication.setFirstname(resultSet.getString("firstname"));
                    authentication.setLastname(resultSet.getString("lastname"));
                    authentication.setEmail(resultSet.getString("email"));
                    authentication.setPassword(resultSet.getString("password"));
                    authentication.setContact(resultSet.getString("contact"));
                    userAuthenticationList.add(authentication);

                }
            }
            return userAuthenticationList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mySqlDatabaseManager.closeConnection();
        }
        return userAuthenticationList;
    }

}
