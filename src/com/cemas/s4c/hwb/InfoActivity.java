package com.cemas.s4c.hwb;

import com.cemas.s4c.hwb.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class InfoActivity extends Activity {
	private TextView infoWords;

	private void findViews() {
		infoWords = (TextView) findViewById(R.id.info_words);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_screen);
		findViews();

	}

}
