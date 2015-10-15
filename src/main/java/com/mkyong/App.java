package com.mkyong;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
//	public static final String LOG_CONFIG = "/SIAT_NFS/pdc/configuracion/log4j.properties";
//	
	static {
	        PropertyConfigurator
	                .configure("/siat/test/log4j.properties");
	    }
	
	public static void main(String[] args) {

		String[] springConfig  = 
			{	
				//"classpath:spring/batch/config/context.xml",
				"classpath:spring/baseContext.xml" 
			};
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(springConfig);
		
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("reportJob");

		try {

			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done");

	}
}
