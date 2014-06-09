package com.example.braingames2;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.app.ActionBar;

public class IntroActivity extends Activity implements OnClickListener {

	Button memoryMazeButton;
	Button colorMatchButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		
		memoryMazeButton = (Button) findViewById(R.id.memorymaze);
		memoryMazeButton.setOnClickListener(this);
		
		colorMatchButton = (Button) findViewById(R.id.colormatch);
		colorMatchButton.setOnClickListener(this);
		
	}
	
	@Override
    public void onClick(View v) {
		Intent gameIntent = null;;		
		if (v == memoryMazeButton){
			gameIntent = new Intent(this, MemoryMazeActivity.class);
		}
		else if (v == colorMatchButton){
			gameIntent = new Intent(this, ColorMatchActivity.class);			
		}
		if (gameIntent != null){
			startActivity(gameIntent);
		}
	}	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.intro, menu);
		return true;
	}

}


