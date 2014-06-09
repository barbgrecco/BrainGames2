package com.example.braingames2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ProgressColorActivity extends Activity implements OnClickListener{
	
	Button tryAgain;
	Button backToStart;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progresscolor);
		
		tryAgain = (Button) findViewById(R.id.tryagain);
		tryAgain.setOnClickListener(this);
		
		backToStart = (Button) findViewById(R.id.backtostart);
		backToStart.setOnClickListener(this);
		
		Bundle bundle = getIntent().getExtras();		
		int iTotal = bundle.getInt("total");
		int iCorrect = bundle.getInt("totalCorrect");		
		
		TextView accuracy = (TextView) findViewById(R.id.accuracy);
		float val = (float)iCorrect/(float)iTotal;
		int iVal = (int)(val * 100);
		accuracy.setText(Integer.toString(iVal) + "%");
		
		TextView totalCorrect = (TextView)findViewById(R.id.totalcorrect);
		totalCorrect.setText(String.valueOf(iCorrect));
		
	}
	
	public void onClick(View v)
	{
		if (v == tryAgain){
			finish();
		}
		else if (v == backToStart){
			Intent introIntent = new Intent(this, IntroActivity.class);
			startActivity(introIntent);
		}
	}
		
}
