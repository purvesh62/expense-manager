package com.expensify.persistenceLayer;
import com.expensify.database.MySqlDatabase;
import com.expensify.database.IDatabase;
import com.expensify.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



@Component
public class UserDAOService {
    private final IDatabase database;
    @Autowired
    private JavaMailSender mailSender;

    public UserDAOService() {
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
    @Bean
    public User.BCryptPasswordEncoder passwordEncoder() {
        return new User.BCryptPasswordEncoder();
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

    public String updatePassword(User user) throws SQLException {
        List<Object> parameterList = new ArrayList<>();
        String password = null;
        try {
            parameterList.add(user.getPassword());
            User.BCryptPasswordEncoder passwordEncoder = new User.BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            ResultSet resultSet = database.executeProcedure("CALL update_password(?)", parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    encodedPassword = resultSet.getString("password");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }

        return password;
    }

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@shopme.com", "Shopme Support");
        helper.setTo(recipientEmail);
        String subject = "Here's the link to reset your password";
        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }

    public String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }


}
