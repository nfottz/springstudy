package com.gdu.app01.xml06;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml06/app-context.xml");
	GymMember member = ctx.getBean("member", GymMember.class);
	
}
