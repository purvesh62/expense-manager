package com.expensify;

import com.expensify.model.INotification;
import com.expensify.model.factories.NotificationFactory;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Component
public class QuartzExpiryScheduler extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(QuartzExpiryScheduler.class);
    private static final int notificationType = 3;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        logger.info("Executing Job with key {}", jobExecutionContext.getJobDetail().getKey());
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        INotification notification = NotificationFactory.instance().createExpiryNotification(
                jobDataMap.getInt("user_id"),
                jobDataMap.getString("email"),
                notificationType
        );
        String firstName = jobDataMap.getString("first_name");
        String emailBody = "Hi " +firstName + ",<br>" + "Your " +jobDataMap.getString("subscription_name")+ " Subscription about to expire";
        notification.notifyUsers(emailBody, "Subscription Expiry Notification");
    }

}


