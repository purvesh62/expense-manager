package com.expensify.model;

import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

import java.text.ParseException;

public interface IQuartzNotificationService {
    JobDetail buildJobDetail(int userId, String firstName, String email, String subscriptionName);
    Trigger buildJobTrigger(JobDetail jobDetail, String croneExpression);
    void scheduleExpiryNotification(int userId, String firstName, String email, String expiryDate, String subscriptionName) throws SchedulerException, ParseException;

}
