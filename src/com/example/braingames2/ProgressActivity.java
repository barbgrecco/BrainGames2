package com.example.braingames2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.braingames2.data.ActivityDataModel;


public class ProgressActivity extends Activity implements OnClickListener {

	ImageView image11;
	ImageView image12;
	ImageView image13;
	ImageView image21;
	ImageView image22;
	ImageView image23;
	ImageView image31;
	ImageView image32;
	ImageView image33;
	
	
	Button clearAll;
	
	ActivityDataModel data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress);
		
		data = ActivityDataModel.getInstance();
		
	
		image11 = (ImageView) findViewById(R.id.complete11);
		image12 = (ImageView) findViewById(R.id.complete12);
		image13 = (ImageView) findViewById(R.id.complete13);
		image21 = (ImageView) findViewById(R.id.complete21);
		image22 = (ImageView) findViewById(R.id.complete22);
		image23 = (ImageView) findViewById(R.id.complete23);
		image31 = (ImageView) findViewById(R.id.complete31);
		image32 = (ImageView) findViewById(R.id.complete32);
		image33 = (ImageView) findViewById(R.id.complete33);
		
		
		clearAll = (Button) findViewById(R.id.clearall);
		clearAll.setOnClickListener(this);
			
		SetFields();
				
		
		
	}
	
	public void onClick(View v)
	{
		data.ClearAllData();
		Intent introIntent = new Intent(this, IntroActivity.class);
		startActivity(introIntent);

	}
	
	private void SetFields ()
	{
		for (int i = 1; i < 10; i++)
		{
			String field = getActivityLevelName(i);
			if (data.GetActivitySuccess(field) > 0)
			{
				setCheckVisible(i, true);
			}
			else
			{
				setCheckVisible(i, false);
			}
			
		}
		
	}

	private void setCheckVisible(int i, boolean visible)
	{
		ImageView image = null;
		CheckBox checkBox;
		switch (i)
		{
		case 1:
			image = (ImageView) findViewById(R.id.complete11);
			if (visible)
			{
				image.setVisibility(View.VISIBLE);
			}
			else
			{
				image.setVisibility(View.INVISIBLE);
			}
			break;
		case 2:
			image = (ImageView) findViewById(R.id.complete12);
			if (visible)
			{
				image.setVisibility(View.VISIBLE);
			}
			else
			{
				image.setVisibility(View.INVISIBLE);
			}
			break;

		case 3:
			image = (ImageView) findViewById(R.id.complete13);
			if (visible)
			{
				image.setVisibility(View.VISIBLE);
			}
			else
			{
				image.setVisibility(View.INVISIBLE);
			}
			break;

		case 4:
			image = (ImageView) findViewById(R.id.complete21);
			if (visible)
			{
				image.setVisibility(View.VISIBLE);
			}
			else
			{
				image.setVisibility(View.INVISIBLE);
			}
			break;
				
		case 5:
			image = (ImageView) findViewById(R.id.complete22);
			if (visible)
			{
				image.setVisibility(View.VISIBLE);
			}
			else
			{
				image.setVisibility(View.INVISIBLE);
			}
			break;
			
		case 6:
			image = (ImageView) findViewById(R.id.complete23);
			if (visible)
			{
				image.setVisibility(View.VISIBLE);
			}
			else
			{
				image.setVisibility(View.INVISIBLE);
			}
			
			break;
					
		case 7:
			image = (ImageView) findViewById(R.id.complete31);
			if (visible)
			{
				image.setVisibility(View.VISIBLE);
			}
			else
			{
				image.setVisibility(View.INVISIBLE);
			}
			break;
					
		case 8:
			image = (ImageView) findViewById(R.id.complete32);
			if (visible)
			{
				image.setVisibility(View.VISIBLE);
			}
			else
			{
				image.setVisibility(View.INVISIBLE);
			}
			break;
			
		case 9:
			image = (ImageView) findViewById(R.id.complete33);
			if (visible)
			{
				image.setVisibility(View.VISIBLE);
			}
			else
			{
				image.setVisibility(View.INVISIBLE);
			}
					
			break;
					
		default:
			break;
		}
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


}
