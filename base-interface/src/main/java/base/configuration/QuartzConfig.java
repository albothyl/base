package base.configuration;


import base.application.post.CachedPostReadCountUpdateJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.Properties;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Slf4j
@Configuration
public class QuartzConfig {


    private SchedulerFactory schedulerFactory;
    private Scheduler scheduler;
    private final String fileName = "/quartz.properties";

    @Value("${scheduler.update.pattern}")
    private String pattern_property;

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource(fileName));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactory getSchedulerFactory() {
        schedulerFactory = new StdSchedulerFactory();
        return schedulerFactory;
    }

    @Bean
    public Scheduler schedulerFactoryBean(JobFactory jobFactory) throws Exception {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        //config overwrite
        factory.setOverwriteExistingJobs(true);
        factory.setJobFactory(jobFactory);

        factory.setQuartzProperties(quartzProperties());
        factory.afterPropertiesSet();

        scheduler = factory.getScheduler();
        scheduler.setJobFactory(jobFactory);

        JobDetail job = newJob(CachedPostReadCountUpdateJob.class)
                .build();

        Trigger trigger = newTrigger()
                .startNow()
//              .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//              .withIntervalInSeconds(5)
//              .repeatForever())
                .withSchedule(CronScheduleBuilder.cronSchedule(pattern_property).withMisfireHandlingInstructionDoNothing())
                .build();

        scheduler.scheduleJob(job, trigger);
        scheduler.start();
        return scheduler;
    }


    //GraceFull Shutdown
    @PreDestroy
    public void stopJobs() {
        try {
            if (scheduler != null) {
                scheduler.shutdown(false);
            }
        } catch (SchedulerException e) {
            log.error("Error while closing scheduler", e);
        }
    }
}
