package com.xtc.quartz;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import com.xtc.entity.TaskInfo;
/**
 * 自动处理任务调度
 *
 */
public class TaskTimer {
	private final static String JOB_GROUP_NAME = "QUARTZ_JOBGROUP_NAME";//任务组
	private final static String TRIGGER_GROUP_NAME = "QUARTZ_TRIGGERGROUP_NAME";//触发器组
	//创建一个SchedulerFactory工厂实例
	private static SchedulerFactory sf = new StdSchedulerFactory();
	/***
	 * 添加一个任务
	 * @param jobName 任务名字
	 * @param triggerName 触发器名
	 * @param jobClass 执行任务类
	 * @param seconds 任务多少秒执行一次
	 * @return 添加成功与否
	 */
	public static boolean addJob(TaskInfo task,Class<? extends Job> jobClass,int seconds){
		try {  
			//通过SchedulerFactory构建Scheduler对象
			Scheduler sche = sf.getScheduler();
			//验证是否存在此任务
			JobKey jobKey = JobKey.jobKey(task.getJobName(),JOB_GROUP_NAME);
			 //判断当前是否存在，存在就不执行
			 if(sche.checkExists(jobKey)){
				 return false;
			 }
			//用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
			JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(task.getJobName(), JOB_GROUP_NAME).build();
			//任务对象  执行时可以获取
			 jobDetail.getJobDataMap().put("task",task);
			//构建一个触发器，规定触发的规则
			Trigger trigger = TriggerBuilder.newTrigger()//创建一个新的TriggerBuilder来规范一个触发器
				.withIdentity(task.getJobName(), TRIGGER_GROUP_NAME)//给触发器起一个名字和组名
				.withSchedule(
				SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(seconds)//时间间隔  单位：秒
				.repeatForever()
				).build();//产生触发器
			//向Scheduler中添加job任务和trigger触发器
			sche.scheduleJob(jobDetail, trigger);
			//启动
			sche.start();
		} catch (SchedulerException e) {
			return  false;
		}
		return true;
	}
	/***
	 * 关闭任务
	 * @param jobName 任务名字（标志）
	 */
	public static boolean closeJob(String jobName){
		try {
			//通过SchedulerFactory构建Scheduler对象
			Scheduler sche = sf.getScheduler();
			JobKey jobKey = JobKey.jobKey(jobName,JOB_GROUP_NAME);
			return sche.deleteJob(jobKey);
		} catch (SchedulerException e) {
			return false;
		}
	}
}
