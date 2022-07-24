package com.expensify.model;

import com.expensify.QuartzExpiryScheduler;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.InterruptableJob;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

;

public class QuartzNotification implements IQuartzNotificationService {
    private Scheduler scheduler ;
    public QuartzNotification(){
       try {
           scheduler = new StdSchedulerFactory().getScheduler();
       } catch (SchedulerException e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public JobDetail buildJobDetail(int userId, String firstName, String email, String subscriptionName, String expiryDate) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("user_id", userId);
        jobDataMap.put("first_name", firstName);
        jobDataMap.put("email", email);
        jobDataMap.put("subscription_name", subscriptionName);


        return JobBuilder.newJob(QuartzExpiryScheduler.class)
                .withIdentity("Expiry_" + userId + "_" + subscriptionName +"_"+ expiryDate, "email-jobs")
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

    public void deleteJob(int userId, String subscriptionName, String expiryDate) {
        try {

            String jobName = "Expiry_" + userId + "_" + subscriptionName +"_"+ expiryDate;
            JobKey key = new JobKey(jobName,"email-jobs");
//            boolean status = scheduler.interrupt(key);
            scheduler.deleteJob(JobKey.jobKey(jobName,"email-jobs"));
            System.out.println("Delete");
            getJobDetails();

        } catch (Exception ex) {
            System.out.println(ex);
        }
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
        String cronExpression = "0 0 0 " + day + " " + (Integer.parseInt(month)+1) + " " + "?" + " " + year;
        JobDetail jobDetail = buildJobDetail(userId, firstName, email, subscriptionName,expiryDate);
        Trigger buildJobTrigger = buildJobTrigger(jobDetail, cronExpression);
        scheduler.scheduleJob(jobDetail, buildJobTrigger);
        scheduler.start();
        System.out.println("Add");
        getJobDetails();
    }

    public void getJobDetails() throws SchedulerException {
        for (String groupName : scheduler.getJobGroupNames()) {

            for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {

                String jobName = jobKey.getName();
                String jobGroup = jobKey.getGroup();
                List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                Date nextFireTime = triggers.get(0).getNextFireTime();

                System.out.println("[jobName] : " + jobName + " [groupName] : "
                        + jobGroup + " - " + nextFireTime);


            }

        }
    }

}



