package com.expensify.Validators;

import com.expensify.model.User;

    public class UserValidator implements IValidator {
        @Override
        public String validate(Object object) {
            User user = (User) object;
            if (user.getFirstName() == null) {
                return "Firstname cannot be null";
            }
            if (user.getLastName() == null) {
                return "Lastname cannot be null";
            }
            if (user.getEmail() == null) {
                return "Email cannot be null";
            }
            if (user.getPassword() == null) {
                return "Password cannot be null";
            }
            if (user.getContact() == null) {
                return "Contact cannot be null";
            }
            return null;
        }
    }


