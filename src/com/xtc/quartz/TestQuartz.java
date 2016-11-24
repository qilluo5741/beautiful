package com.xtc.quartz;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
/**
 * 测试定时器
 */
public class TestQuartz extends QuartzJobBean {

    public void work() {
        System.out.println(new Date()+"任 调度！！！");
    }
    @Override
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
        this.work();
    }
}
