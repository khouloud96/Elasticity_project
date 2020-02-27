package com.elasticity.services;

import java.util.ListIterator;
import com.elasticity.csm.CSMModel;
import com.elasticity.csm.State;

public class CurrentState
{
	private String S;
	//Get current State
	public String getS()
	{
		return S;
	}
	
	//Set Current State
	public void setS(String s)
	{
    	this.S = s;
	}
	
	public String initialState(CSMModel csm)
	{		
	    ListIterator<State> it = csm.getCsm().getStates().listIterator();
	    
	    //Foreach State
		while(it.hasNext())
		{
			//Get the transition
			State S = it.next();
			if(S.getResourceRequirements().getType().equals("initial"))
				return S.getName();
		}
		
		return null;
	}
	
	public static String instancesOfInitialState(CSMModel csm)
	{		
	    ListIterator<State> it = csm.getCsm().getStates().listIterator();
	    
	    //Foreach State
		while(it.hasNext())
		{
			//Get the transition
			State S = it.next();
			if(S.getResourceRequirements().getType().equals("initial"))
				return S.getResourceRequirements().getInstances();
		}
		
		return null;
	}
}
