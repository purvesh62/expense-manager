package com.expensify.model;

import com.expensify.QuartzExpiryScheduler;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

;

public class QuartzNotification implements IQuartzNotificationService {
    @Override
    public JobDetail buildJobDetail(int userId, String firstName, String email, String subscriptionName) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("user_id", userId);
        jobDataMap.put("first_name", firstName);
        jobDataMap.put("email", email);
        jobDataMap.put("subscription_name", subscriptionName);

        return JobBuilder.newJob(QuartzExpiryScheduler.class)
                .withIdentity(UUID.randomUUID().toString(), "email-jobs")
                .withDescription("Send Email Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    @Override
    public Trigger buildJobTrigger(JobDetail jobDetail, String croneExpression) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "email-triggers")
                .withDescription("Send Email Trigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(croneExpression))
                .build();
    }

    @Override
    public void scheduleExpiryNotification(int userId, String firstName, String email, String expiryDate, String subscriptionName) throws SchedulerException, ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(expiryDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -5);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        String day = String.valueOf(calendar.get(Calendar.DATE));
        String cronExpression = "0 0 0 " + day + " " + month + " " + "?" + " " + year;
        JobDetail jobDetail = buildJobDetail(userId, firstName, email, subscriptionName);
        Trigger buildJobTrigger = buildJobTrigger(jobDetail, cronExpression);
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(jobDetail, buildJobTrigger);
        scheduler.start();


    }

}


