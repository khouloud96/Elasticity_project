package com.elasticity.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Cooldown 
{
	private boolean C;
	private Date cooldownDate;
	
	public boolean isC() {
		return C;
	}

	public void setC(boolean c) {
		C = c;
	}
	
	public void waitCooldown(final long duration) 
	{
		setC(false);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		System.out.print(dtf.format(now));

    	System.out.println("\tSet cooldown : "+isC());
        try
        {
        	setC(false);
        	Thread.sleep(duration*1000);
        }
        catch (InterruptedException e) {}
        
        setC(true);
        cooldownDate = new Date();
		now = LocalDateTime.now();  
		System.out.print(dtf.format(now));
    	System.out.println("\tSet cooldown : "+isC());
	}

	public Date getCooldownDate() {
		return cooldownDate;
	}

	public void setCooldownDate(Date cooldownDate) {
		this.cooldownDate = cooldownDate;
	}
}
