package com.expensify.model;

public class EmailDetails {
    private String receipentAddress;
    private String emailBody;
    private String subject;

    public String getReceipentAddress() {
        return receipentAddress;
    }

    public void setReceipentAddress(String receipentAddress) {
        this.receipentAddress = receipentAddress;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
