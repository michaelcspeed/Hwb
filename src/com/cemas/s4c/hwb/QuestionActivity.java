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
import android.widget.ImageButton;
import android.widget.TextView;

public class QuestionActivity extends Activity implements OnClickListener {

	private static TextView timeHeader;
	private TextView questionText;
	private ImageButton fwdButton;
	private ImageButton vocabicon;
	private int currentTime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_screen);
		findViews();
		startTimer();
	}

	private void findViews() {
		timeHeader = (TextView) findViewById(R.id.time_header);
		questionText = (TextView) findViewById(R.id.question_text);
		fwdButton = (ImageButton) findViewById(R.id.fwd_button);
		vocabicon = (ImageButton) findViewById(R.id.vocabicon);

		fwdButton.setOnClickListener(this);
		vocabicon.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == fwdButton) {
			Intent i = new Intent(this, ChooseAnswer.class);
			i.putExtra("currentTime", currentTime);
			startActivity(i);
		} else if (v == vocabicon) {
			Intent i = new Intent(this, VocabActivity.class);
			startActivity(i);
		}
	}

	final Handler handler = new Handler();
	final Timer timer = new Timer();

	final Runnable updateTime = new Runnable() {

		@Override
		public void run() {
			currentTime++;
			Globals.setCurrentSeconds(currentTime);
			String timeString = Util.getTimeFormatted(currentTime);
			timeHeader.setText(timeString);
			Log.d("Hwb", "time is: " + timeString);
		}

		
	};

	public void startTimer() {
		for (int i = 1; i <= Globals.getTotSeconds(); i++) {

			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					handler.post(updateTime);
				}
			};
			timer.schedule(task, i * 1000);
		}
	}

}