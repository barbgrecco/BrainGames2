package com.example.braingames2.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.braingames2.data.ActivityLevel;

import java.util.ArrayList;


public class ActivityDataModel {

	private List<ActivityLevel> activitiesList = new ArrayList<ActivityLevel>();
	private static ActivityDataModel instance = null;
		
	public static ActivityDataModel getInstance()
	{
		if (instance == null)
		{
			instance = new ActivityDataModel();
		}
		return instance;
	}

	 private ActivityDataModel()
	 {
		 
	 // Constructor hidden because this is a singleton
	 }	
	 
	 public ActivityLevel AddActivity(String name)
	 {
		 Iterator<ActivityLevel> iter = activitiesList.iterator();
		 ActivityLevel level = null;
		 boolean found = false;
		 while (iter.hasNext())
		 {
			 level = iter.next();
			 if ( level.getName() == name)
			 {
				 found = true;
				 break;
			 }
		 }
		 if (found == false)
		 {
			 level = new ActivityLevel(name);
			 activitiesList.add(level);
		 }
		 return level;
	 }

	 public int GetActivitySuccess(String name)
	 {
		 Iterator<ActivityLevel> iter = activitiesList.iterator();
		 int successes = 0;
		 while (iter.hasNext())
		 {
			 ActivityLevel level = iter.next();
			 String levelName = level.getName();
			 if (levelName == name)
			 //if ( iter.next().getName() == name)
			 {
				 //successes = iter.next().getActivitySuccess();
				 successes = level.getActivitySuccess();
				 break;
			 }
		 }
		 return successes; 
	 }
	 public int GetActivityFailures(String name)
	 {
		 Iterator<ActivityLevel> iter = activitiesList.iterator();
		 int failures = 0;
		 while (iter.hasNext())
		 {
			 if ( iter.next().getName() == name)
			 {
				 failures = iter.next().getActivityFailures();
				 break;
			 }
		 }
		 return failures; 
		 
	 }
	 
	 public void ClearAllData()
	 {
		 activitiesList.clear();
	 }	 
}
