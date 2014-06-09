package com.example.braingames2;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import com.example.braingames2.TextViewPlus;
import com.example.braingames2.MemoryMazeActivity;

public class TextViewPlusAdapter extends BaseAdapter {

	public enum GridState {
		INIT_STATE,
		SETUP_STATE,
		TEST_STATE,
		SUCCESS_STATE,
		ERROR_STATE
		}	
	private MemoryMazeActivity mContext;
    int numTestSquares;
    int numGridSquares;
    int numMaxTestSquares;
    int numMinTestSquares;
 
	TextViewPlus[] listOfSquares;
    GridView mParent;
    GridState currentState;
    int numCorrectGuesses;

    public TextViewPlusAdapter(MemoryMazeActivity c, int numSquares, int numTest){
    	super();
        mContext = (MemoryMazeActivity)c;
        numGridSquares = numSquares;
        numTestSquares = numTest;
        numMinTestSquares = numTestSquares;
        numMaxTestSquares = numTest + 2;
        listOfSquares = new TextViewPlus[numGridSquares];   
        currentState = GridState.INIT_STATE;
    }
    
    public int getNumMaxTestSquares() {
 		return numMaxTestSquares;
 	}

 	public int getNumMinTestSquares() {
 		return numMinTestSquares;
 	}
    
    public void setNumTestSquares(int n)
    {
    	numTestSquares = n;
    }
    
	public int getNumTestSquares()
    {
    	return numTestSquares;
    }
	
    public int getNumGridSquares()
    {
		return numGridSquares;
	}

	public void setNumGridSquares(int n)
	{
		numGridSquares = n;
	    listOfSquares = new TextViewPlus[numGridSquares];  		
	}


    public void setState(GridState state){
    	currentState = state;
    	switch (state)
    	{
    	case INIT_STATE:
    		numCorrectGuesses = 0;
        	for (int i = 0; i < listOfSquares.length; i++)
        	{
        		TextViewPlus tvp = listOfSquares[i];
        		if (tvp != null)
        		{
        			tvp.SetState(TextViewPlus.State.CLEAR_STATE);
        		}
        	}    		
    		break;
    		
    	case SETUP_STATE:
    		setRandomSquares();
    		break;
    		
    	case TEST_STATE:
        	for (int i = 0; i < listOfSquares.length; i++)
        	{
        		TextViewPlus tvp = listOfSquares[i];
        		if (tvp != null)
        		{
        			tvp.SetState(TextViewPlus.State.START_STATE);
        		}
        	}    		
    		break;
    	
    	case SUCCESS_STATE:
           	for (int i = 0; i < listOfSquares.length; i++)
        	{
        		TextViewPlus tvp = listOfSquares[i];
        		if (tvp != null)
        		{
        			tvp.ShowTrueState();
        		}
        	}  
           	mContext.ShowSuccess();
    		break;

    		
    	case ERROR_STATE:
           	for (int i = 0; i < listOfSquares.length; i++)
        	{
        		TextViewPlus tvp = listOfSquares[i];
        		if (tvp != null)
        		{
        			tvp.ShowTrueState();
        		}
        	} 
          	mContext.ShowError();
    		break;

    	}
    	notifyDataSetChanged();
		mParent.invalidateViews();
  		

    }
    
    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    
    public void setRandomSquares()
    {
    	int i = 0;
    	int nextSquare;
    	while (i < numTestSquares)
    	{
    		nextSquare = randInt(0, listOfSquares.length - 1);
    		//boolean alreadySet = listOfSquares[nextSquare].IsTestSquare();
    		//boolean canBeSet = !listOfSquares[nextSquare].IsTestSquare();
    		//boolean exists = listOfSquares[nextSquare] != null;
    		if (listOfSquares[nextSquare] != null && !listOfSquares[nextSquare].IsTestSquare())
    		{
    			listOfSquares[nextSquare].isTestSquare = true;
    			listOfSquares[nextSquare].SetState(TextViewPlus.State.SELECTED_STATE);
    			notifyDataSetChanged();
    			mParent.invalidateViews();
    			try {
					TimeUnit.MILLISECONDS.sleep(15);
				} catch (java.lang.InterruptedException ie) {
				    System.out.println(ie);
				}    			
    			i++;
    		}
    		
    	}
    }
    
    public void OnSquareClicked(int position)
    {
    	if (currentState != GridState.TEST_STATE){
    		return;
    	}
    	TextViewPlus tvp = listOfSquares[position];
    	if (!tvp.IsTestSquare()){
    		tvp.SetState(TextViewPlus.State.ERROR_STATE);
           	notifyDataSetChanged();
        	mParent.invalidateViews();
        	setState(GridState.ERROR_STATE);
 
    		
    		return;
    	}
    	if ( tvp.HasBeenTested()){
    		return;
    	}
    	tvp.SetHasBeenTested();
    	numCorrectGuesses++;
    	tvp.ShowTrueState();
    	notifyDataSetChanged();
		mParent.invalidateViews();
     	
    	if ( numCorrectGuesses == numTestSquares ){
    		setState(GridState.SUCCESS_STATE);
    	}
 		
    }
    

	@Override
	public int getCount() {
	    return listOfSquares.length;
	}

	@Override
	public Object getItem(int index) {
		return listOfSquares[index];
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		mParent = (GridView)parent;
		TextViewPlus textViewPlus;
		if (listOfSquares[position] == null){
			textViewPlus = new TextViewPlus(mContext);
			//textViewPlus.setScaleType(TextViewPlus.ScaleType.CENTER_CROP);
			textViewPlus.setLayoutParams(new GridView.LayoutParams(45, 45));
			listOfSquares[position] = textViewPlus;
			return textViewPlus;
		}
		else
		{
			return listOfSquares[position];
		}
	}
}

