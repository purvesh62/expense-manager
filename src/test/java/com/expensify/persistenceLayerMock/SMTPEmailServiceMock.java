package com.expensify.persistenceLayerMock;

import com.expensify.model.IEmailService;
import com.expensify.model.SMTPEmailService;

public class SMTPEmailServiceMock implements IEmailService {

    private static IEmailService instance;

    private SMTPEmailServiceMock() {

    }
    public static IEmailService instance(String receipentAddress, String emailBody, String subject) {
        if(null == instance){
            instance = new SMTPEmailServiceMock();
        }
        return instance;
    }

    @Override
    public void sendEmail() {

    }
}
