package com.example.braingames2.data;


public class ActivityLevel {
	private int activitySuccess;
	private int activityFailures;
	private String name;

	
	public ActivityLevel(String newName)
	{
		name = newName;
		activitySuccess = 0;
		activityFailures = 0;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getActivitySuccess() {
		return activitySuccess;
	}
	public void addActivitySuccess() {
		this.activitySuccess += 1;
	}
	public int getActivityFailures() {
		return activityFailures;
	}
	public void addActivityFailures() {
		this.activityFailures += 1;
	}

}

