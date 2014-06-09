package com.example.braingames2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import com.example.braingames2.data.ActivityDataModel;
import com.example.braingames2.data.ActivityLevel;

public class MemoryMazeActivity extends Activity implements OnClickListener, OnItemClickListener
{
	Button startGame;
	Button progressButton;
	TextViewPlusAdapter textViewPlusAdapter1;
	TextViewPlusAdapter textViewPlusAdapter2;
	TextViewPlusAdapter textViewPlusAdapter3;	
	TextView textSuccess;
	TextView textError;
	private Handler handler;
	private int currentLevel;
	private int maxLevels = 3;
	GridView gridView1;
	GridView gridView2;
	GridView gridView3;
	GridView currentGridView;
	int activityLevel;
	ActivityDataModel data;
	
	public enum State {
		STATE_START,
		STATE_DO_TEST,
		STATE_REPAINT,
		STATE_NOSTATE
	}
	State pageState;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
        setContentView(R.layout.activity_memorymaze);
        handler = new Handler();
        currentLevel = 1;
        activityLevel = 1;
        data = ActivityDataModel.getInstance();
        gridView1 = (GridView) findViewById(R.id.grid_view1);
        gridView1.setVisibility(View.INVISIBLE);
        textViewPlusAdapter1 = new TextViewPlusAdapter((MemoryMazeActivity)this, 9, 3);
        gridView1.setAdapter(textViewPlusAdapter1);
        gridView1.setOnItemClickListener(this);        
        
        gridView2 = (GridView) findViewById(R.id.grid_view2);
        gridView2.setVisibility(View.INVISIBLE);
        textViewPlusAdapter2 = new TextViewPlusAdapter((MemoryMazeActivity)this, 16, 4);
        gridView2.setAdapter(textViewPlusAdapter2);
        gridView2.setOnItemClickListener(this);
        
        gridView3 = (GridView) findViewById(R.id.grid_view3);
        gridView3.setVisibility(View.INVISIBLE);     
        textViewPlusAdapter3 = new TextViewPlusAdapter((MemoryMazeActivity)this, 25, 5);
        gridView3.setAdapter(textViewPlusAdapter3);
        gridView3.setOnItemClickListener(this);        
        
        currentGridView = gridView1;
        currentGridView.setVisibility(View.VISIBLE);
        
        pageState = State.STATE_NOSTATE;
            
        startGame = (Button) findViewById(R.id.StartGame);
        startGame.setOnClickListener(this);
        
		progressButton = (Button) findViewById(R.id.Progress);
		progressButton.setOnClickListener(this);
		
        textSuccess = (TextView) findViewById(R.id.success);
        textSuccess.setVisibility(TextView.INVISIBLE);
        
        textError = (TextView) findViewById(R.id.error);
        textError.setVisibility(TextView.INVISIBLE);
	}
	
	public void ShowSuccess(){
    	TextViewPlusAdapter tva = (TextViewPlusAdapter) currentGridView.getAdapter();
    	
	    textSuccess.setVisibility(TextView.VISIBLE);	
	    startGame.setText(R.string.playnext);
		startGame.setVisibility(View.VISIBLE);
		if ( tva != null )
		{ 
			int max = tva.getNumMaxTestSquares();
			int n = tva.getNumTestSquares();
			
			String name = getActivityLevelName(activityLevel);
			ActivityLevel level = data.AddActivity(name);
			level.addActivitySuccess();
			
			if ( n < max )
			{
				tva.setNumTestSquares(n+1);
				activityLevel++;
			}
			else if (currentLevel < maxLevels )
			{
				BuildAlert();
			}
			else
			{
				startGame.setVisibility(View.INVISIBLE);			
				BuildCongratsAlert();
			}
		}
	}
	
	public void ShowError(){
        textError.setVisibility(TextView.VISIBLE);
	    startGame.setText(R.string.tryagain);        
		startGame.setVisibility(View.VISIBLE);
    	TextViewPlusAdapter tva = (TextViewPlusAdapter) currentGridView.getAdapter();
    	if ( tva != null )
    	{
    		int n = tva.getNumTestSquares();
    		int min = tva.getNumMinTestSquares();
    		if ( n > min )
    		{
    			tva.setNumTestSquares(n-1);
				ActivityLevel level = data.AddActivity(getActivityLevelName(activityLevel));
				level.addActivityFailures();
				activityLevel--;
    		}
    	}
		
	}
	@Override
    public void onItemClick(AdapterView<?>  parent, View v,
            int position, long id) {
    	if (pageState == State.STATE_DO_TEST)
    	{
    		return;
    	}
    	TextViewPlusAdapter tva = (TextViewPlusAdapter) currentGridView.getAdapter();
    	if ( tva != null )
    	{
    		tva.OnSquareClicked(position);
    	}
    	pageState = State.STATE_REPAINT;
    	handler.postDelayed(runnable,100);            	
     }
	
	@Override
	public void onClick(View v) {
		if ( v == progressButton )
		{
			Intent progressIntent = new Intent(this, ProgressActivity.class);
			startActivity(progressIntent);
		}
		else
		{
			startLevel();
		}
	}
	
	public void startLevel()
	{
	    textSuccess.setVisibility(TextView.INVISIBLE);		
        textError.setVisibility(TextView.INVISIBLE);		
    	TextViewPlusAdapter tva = (TextViewPlusAdapter) currentGridView.getAdapter();
    	if ( tva != null )
    	{
    		tva.setState(TextViewPlusAdapter.GridState.INIT_STATE);
    	}
		pageState = State.STATE_START;
		handler.postDelayed(runnable, 100);
		startGame.setVisibility(View.GONE);
	}
	

	private Runnable runnable = new Runnable(){
		public void run() {
		    TextViewPlusAdapter tva = (TextViewPlusAdapter) currentGridView.getAdapter();
			if (tva != null && pageState == State.STATE_DO_TEST)
			{
				tva.setState(TextViewPlusAdapter.GridState.TEST_STATE);
				pageState = State.STATE_REPAINT;
			}
   		    else if (pageState == State.STATE_REPAINT)
			{
   		    	pageState = State.STATE_NOSTATE;
			}
			else if ( tva != null && pageState == State.STATE_START)
			{
				tva.setState(TextViewPlusAdapter.GridState.SETUP_STATE);
				pageState = State.STATE_DO_TEST;
				handler.postDelayed(runnable, 1000);
			}
		};
	};


	private void BuildAlert()
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MemoryMazeActivity.this);
		alertDialogBuilder.setTitle("What is next?");
		alertDialogBuilder.setMessage("Congratulations, you finished the level!  Pick what to do next");
		alertDialogBuilder.setPositiveButton("Play Next Level",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
    		   ReconfigureLevelUp();
    	 	   }
          });
		 alertDialogBuilder.setNegativeButton("Replay Level",new DialogInterface.OnClickListener() {	        
			 public void onClick(DialogInterface dialog,int id) {
    		 	ResetCurrentLevel();	        		 
 	   		 }
		  });
         
         AlertDialog alertDialog = alertDialogBuilder.create();
         // show alert
         alertDialog.show();	         
	}
		
	private void BuildCongratsAlert()
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MemoryMazeActivity.this);	
        alertDialogBuilder.setTitle("Congratulations");
        alertDialogBuilder.setMessage("You did it!");
        alertDialogBuilder.setNeutralButton("See the Final Result",new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog,int id) {
        		goToProgress();
 			}        	
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();	         
        
	}
	
		private void ResetCurrentLevel()
		{
			switch (currentLevel)
			{
				case 1:
					textViewPlusAdapter1.setNumTestSquares(3);
					activityLevel = 1;
					break;
				case 2:
					textViewPlusAdapter2.setNumTestSquares(4);
					activityLevel = 4;
					break;
				case 3:
					textViewPlusAdapter3.setNumTestSquares(5);
					activityLevel = 7;
					break;
			}
		    TextViewPlusAdapter tva = (TextViewPlusAdapter) currentGridView.getAdapter();
		    if ( tva != null )
		    {
		    	tva.setState(TextViewPlusAdapter.GridState.INIT_STATE);
		    }
			startGame.setVisibility(View.VISIBLE);
			pageState = State.STATE_NOSTATE;
		}
		
		private void ReconfigureLevelUp()
		{
			switch (currentLevel)
			{
				case 1:
					currentLevel = 2;
					activityLevel = 4;
			        currentGridView.setVisibility(View.INVISIBLE);
					currentGridView = gridView2;
			        currentGridView.setVisibility(View.VISIBLE);
					break;
				case 2:
					currentLevel = 3;
					activityLevel = 7;
			        currentGridView.setVisibility(View.INVISIBLE);
			        currentGridView = gridView3;
			        currentGridView.setVisibility(View.VISIBLE);			        
					break;
			}
		    textSuccess.setVisibility(TextView.INVISIBLE);
			startGame.setVisibility(View.VISIBLE);
			pageState = State.STATE_NOSTATE;
			
		}
			
		private void goToProgress()
		{
   	       Intent progressIntent = new Intent(this, ProgressActivity.class);
   	       startActivity(progressIntent);
		}
		
		private String getActivityLevelName(int activityLevel)
		{
			String name = "";
			switch (activityLevel)
			{
			case 1:
				name = this.getText(R.string.activity11).toString();
				break;
			case 2:
				name = this.getText(R.string.activity12).toString();
				break;
			case 3:
				name = this.getText(R.string.activity13).toString();
				break;
			case 4:
				name = this.getText(R.string.activity21).toString();
				break;
			case 5:
				name = this.getText(R.string.activity22).toString();	
				break;
			case 6:
				name = this.getText(R.string.activity23).toString();	
				break;
			case 7:
				name = this.getText(R.string.activity31).toString();	
				break;
			case 8:
				name = this.getText(R.string.activity32).toString();	
				break;
			case 9:
				name = this.getText(R.string.activity33).toString();
				break;
			default:
				break;
			}
			return name;
		}	 

	
	   @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.level1, menu);
		return true;
	}

}

