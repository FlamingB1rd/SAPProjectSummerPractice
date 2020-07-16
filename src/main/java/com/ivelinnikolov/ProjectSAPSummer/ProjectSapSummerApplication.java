package com.ivelinnikolov.ProjectSAPSummer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjectSapSummerApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(ProjectSapSummerApplication.class, args);
	}

}
