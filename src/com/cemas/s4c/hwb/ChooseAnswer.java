package com.cemas.s4c.hwb;

import java.util.Timer;
import java.util.TimerTask;

import com.cemas.s4c.hwb.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ChooseAnswer extends Activity implements OnClickListener {
	private TextView timeHeader;
	private ImageButton vocabicon;
	private Button answerAButton;
	private Button answerBButton;
	private Button answerCButton;
	private Button answerDButton;
	private int currentTime;

	private void findViews() {
		timeHeader = (TextView) findViewById(R.id.time_header);
		vocabicon = (ImageButton) findViewById(R.id.vocabicon);
		answerAButton = (Button) findViewById(R.id.answerA_button);
		answerBButton = (Button) findViewById(R.id.answerB_button);
		answerCButton = (Button) findViewById(R.id.answerC_button);
		answerDButton = (Button) findViewById(R.id.answerD_button);

		vocabicon.setOnClickListener(this);
		answerAButton.setOnClickListener(this);
		answerBButton.setOnClickListener(this);
		answerCButton.setOnClickListener(this);
		answerDButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == vocabicon) {
			Intent i = new Intent(this, VocabActivity.class);
			startActivity(i);
		} else if (v == answerAButton) {
			// Handle clicks for answerAButton
		} else if (v == answerBButton) {
			// Handle clicks for answerBButton
		} else if (v == answerCButton) {
			// Handle clicks for answerCButton
		} else if (v == answerDButton) {
			// Handle clicks for answerDButton
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.answer_screen);
		findViews();

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				currentTime++;
				String timeString = Util.getTimeFormatted(currentTime);
				timeHeader.setText(timeString);
				Log.d("Hwb", "time is: " + timeString);
			}
		}, 0, 1000);

	}



}
