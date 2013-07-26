package com.cemas.s4c.hwb;

import com.cemas.s4c.hwb.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class ChooseTimeFormatActivity extends Activity implements
		OnClickListener {
	private Button timeHawddButton;
	private Button timeSoffaButton;
	private ImageButton vocabicon;

	private void findViews() {
		timeHawddButton = (Button) findViewById(R.id.time_hawdd_button);
		timeSoffaButton = (Button) findViewById(R.id.time_soffa_button);
		vocabicon = (ImageButton) findViewById(R.id.vocabicon);
		timeHawddButton.setOnClickListener(this);
		timeSoffaButton.setOnClickListener(this);
		vocabicon.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.timing_format_screen);
		findViews();
	}

	@Override
	public void onClick( View arg0) {
		switch (arg0.getId()) {
		case R.id.vocabicon:
			Intent i = new Intent(this, VocabActivity.class);
			startActivity(i);
			break;

		case R.id.time_hawdd_button:
			Globals.setTotSeconds(60);
			Intent i2 = new Intent(this, QuestionAndAnswerActivity.class);
			startActivity(i2);
			break;

		case R.id.time_soffa_button:
			Globals.setTotSeconds(20);
			Intent i3 = new Intent(this, QuestionAndAnswerActivity.class);
			startActivity(i3);
			break;

		default:
			break;
		}

	}
}
