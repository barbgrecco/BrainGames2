package com.example.braingames2;

import java.util.Random;


//import android.R.color;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ColorMatchActivity extends Activity implements OnClickListener, OnItemClickListener{

	TextView textMeaning;
	TextView textColor;
	TextView timeRemaining;
	TextView currentScore;
	
	Button match;
	Button noMatch;
	Button explain;
	
	String strMeaning;
	String strColor;
	String strBkColor;
	
	ImageView result;
	
	int timeLeft;
	int tries;
	int correctTries;
	
	private Handler handler;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_colormatch);
	
		handler = new Handler();
		textMeaning = (TextView) findViewById(R.id.colorMeaning);
		textColor = (TextView) findViewById(R.id.colorShown);
		timeRemaining = (TextView) findViewById(R.id.time);
		currentScore = (TextView) findViewById(R.id.score);
	
		match = (Button) findViewById(R.id.buttonmatch);
		match.setOnClickListener(this);
		
		noMatch = (Button) findViewById(R.id.buttonnomatch);
		noMatch.setOnClickListener(this);
		
		explain = (Button) findViewById(R.id.explain);
		explain.setOnClickListener(this);
		

		result = (ImageView) findViewById(R.id.result);
		result.setImageResource(R.drawable.check);		
	}
	
	@Override
	public void onResume(){
		super.onResume();		
		resetVals();
		SetupColorBoxes();
		result.setVisibility(View.INVISIBLE);		
	}
	
	void resetVals(){
		timeLeft = 45;
		tries = 0;
		correctTries = 0;
		currentScore.setText("Score: " + String.valueOf(correctTries) + "/" + String.valueOf(tries));
//		currentScore.setText(R.string.score2 + String.valueOf(correctTries) + "/" + String.valueOf(tries));
		timeRemaining.setText("Time: " + "0:" + String.valueOf(timeLeft));				
//		timeRemaining.setText(R.string.time + "0:" + String.valueOf(timeLeft));		
		handler.postDelayed(timeRunnable, 1000);		
	}

  public static int randInt(int min, int max) {

      // Usually this can be a field rather than a method variable
      Random rand = new Random();

      // nextInt is normally exclusive of the top value,
      // so add 1 to make it inclusive
      int randomNum = rand.nextInt((max - min) + 1) + min;

      return randomNum;
  }	
  
  String getColorString(int num){
  	switch (num){
  	case 0:
  		return "black";
  	case 1:
  		return "red";
  	case 2:
  		return "green";
  	case 3:
  		return "yellow";
  	case 4: 
  		return "blue";
  	case 5:
  		return "purple";
  	default:
  		return "black";
  				
  	}
  }
  
  int getColor( int num, String strColor){
  	switch (num){
    	case 0:
    		strColor = "black";
    		return getResources().getColor(R.color.black);    
  	case 1:
  		strColor = "red";
    		return getResources().getColor(R.color.red);        		
  	case 2:
  		strColor =  "green";
  		return getResources().getColor(R.color.green);        		

  	case 3:
  		strColor = "yellow";  		
    		return getResources().getColor(R.color.yellow);        		

  	case 4: 
  		strColor = "blue";
  		return getResources().getColor(R.color.blue);        		

  	case 5:
  	default:  		
		strColor = "purple";
		return getResources().getColor(R.color.purple);  

  	
  	}
  }
  
	void SetupColorBoxes(){
		int meaning = randInt(0,5);
		strMeaning = getColorString(meaning);

		int backColor;
		int matchMeaning = randInt(0,1);
		
	  	switch (matchMeaning == 1 ? meaning : randInt(0,5)){
    	case 0:
    		backColor = getResources().getColor(R.color.black);
    		strBkColor = "black";
    		break;
    	case 1:
    		backColor = getResources().getColor(R.color.red);   
    		strBkColor = "red";    		
    		break;
    	case 2:
    		backColor = getResources().getColor(R.color.green);  
    		strBkColor = "green";    	    		
    		break;
    	case 3:
    		backColor = getResources().getColor(R.color.yellow);
    		strBkColor = "yellow";    	    		
    		break;
    	case 4: 
    		backColor = getResources().getColor(R.color.blue); 
    		strBkColor = "blue";    	    		
    		break;
    	case 5:
    	default:  		
    		backColor = getResources().getColor(R.color.purple);
    		strBkColor = "purple";    	    		
    		break;
 	
	  	}		
	  	switch (randInt(0,5)){
    	case 0:
    		strColor = "black";
    		break;
    	case 1:
    		strColor = "red";
    		break;
    	case 2:
    		strColor =  "green";
    		break;
    	case 3:
    		strColor = "yellow";  		
    		break;
    	case 4: 
    		strColor = "blue";
    		break;
    	case 5:
    	default:  		
    		strColor = "purple";
    		break;
	  	}
	  	
		textMeaning.setText(strMeaning);
		textColor.setTextColor(backColor);
		textColor.setText(strColor);
	}
	
	@Override
  public void onItemClick(AdapterView<?>  parent, View v,
          int position, long id) {
      	
   }
	
	void ShowSuccess(){
		result.setImageResource(R.drawable.check);
		result.setVisibility(View.VISIBLE);
		tries++;
		correctTries++;
		//currentScore.setText(R.string.score2 + String.valueOf(correctTries) + "/" + String.valueOf(tries));
		currentScore.setText("Score: " + String.valueOf(correctTries) + "/" + String.valueOf(tries));		
		handler.postDelayed(runnable, 1000);

	}
	
	void ShowFailure(){
		result.setImageResource(R.drawable.cancel);
		result.setVisibility(View.VISIBLE);
		tries++;
//		currentScore.setText(R.string.score2 + String.valueOf(correctTries) + "/" + String.valueOf(tries));
		currentScore.setText("Score: " + String.valueOf(correctTries) + "/" + String.valueOf(tries));		
		handler.postDelayed(runnable, 1000);

	}
	
	private Runnable runnable = new Runnable(){
		public void run() {
			result.setVisibility(View.INVISIBLE);
			SetupColorBoxes();			
		};
	};
	
	void showProgress(){
		Intent progressActivity = new Intent(this, ProgressColorActivity.class);
		progressActivity.putExtra("total", tries);
		progressActivity.putExtra("totalCorrect", correctTries);
		startActivity(progressActivity);				
	}
	
	private Runnable timeRunnable = new Runnable(){
		public void run() {
			timeLeft--;
			if (timeLeft == 0){
				showProgress();
			}
			else{
				//timeRemaining.setText(R.string.time + "0:" + String.valueOf(timeLeft));
				timeRemaining.setText("Time: " + "0:" + String.valueOf(timeLeft));				
				handler.postDelayed(timeRunnable, 1000);
			}
		};
	};	
	@Override
	public void onClick(View v) {
		if ( v == match ){
			if (strMeaning == strBkColor){
				ShowSuccess();
			}
			else{
				ShowFailure();
			}
		}
		else if (v == noMatch){
			if ( strMeaning == strBkColor){
				ShowFailure();
			}
			else{
				ShowSuccess();
			}
			
		}
		else if (v == explain){
			buildExplainDialog();
		}

	}

	private void buildExplainDialog()
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ColorMatchActivity.this);
		alertDialogBuilder.setTitle("Color Match Game");
		alertDialogBuilder.setMessage("If the color of the text in the 'Color' box matches the color word shown in the 'Meaning' box, click the 'Match' button, otherwise click the 'Not a match' button");
		alertDialogBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
    	 	   }
          });
         
         AlertDialog alertDialog = alertDialogBuilder.create();
         // show alert
         alertDialog.show();	         
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.color_match, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
	/**
	 * A placeholder fragment containing a simple view.
	 */
/*	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
//			View rootView = inflater.inflate(R.layout.fragment_color_match,
//					container, false);
			return rootView;
		}
	}

}*/

