package com.example.braingames2;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

public class TextViewPlus extends TextView {

	String errorText;
	State currentState;
	boolean isTestSquare; 
	boolean hasBeenTested;
	
	public enum State {
		CLEAR_STATE,
		START_STATE,
		SELECTED_STATE,
		TEST_STATE,
		ERROR_STATE
		}	
	
	public TextViewPlus(Context context) {
		super(context);
		errorText ="X";
		SetState(State.CLEAR_STATE); 

		setGravity(Gravity.CENTER);
		setTextSize(30);
		setTextColor(getResources().getColor(R.color.red));
		//setPadding(0,0,0,5);
	}
	
	public boolean IsTestSquare()
	{
		return isTestSquare;
	}
	
	public boolean HasBeenTested()
	{
		return hasBeenTested;
	}
	
	public void SetHasBeenTested()
	{
		hasBeenTested = true;
	}
	
	public void SetState(TextViewPlus.State stateVal){
		currentState = stateVal;
		switch (currentState){
		case CLEAR_STATE:
			isTestSquare = false;
			hasBeenTested = false;
			setText("");
			//fall through
		case START_STATE:
			setBackgroundResource(R.color.blue); 
			break;
		case SELECTED_STATE:
			setBackgroundResource(R.color.aqua);			
			break;
		case TEST_STATE:
			setBackgroundResource(R.color.blue);
			break;
		case ERROR_STATE:
			setText(errorText);	
			setBackgroundResource(R.color.white);			
			break;
		default:
			break;
		}
	}
	
	public void ShowTrueState()
	{
		if ( isTestSquare){
			SetState(State.SELECTED_STATE);
		}
	}

}

