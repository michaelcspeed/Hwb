package com.cemas.s4c.hwb;

import com.cemas.s4c.hwb.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class VocabActivity extends Activity {

	private String[] vocab;
	private TextView vocabWords;

	private void findViews() {
		vocabWords = (TextView) findViewById(R.id.vocab_words);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.vocab_screen);
		findViews();

		vocab = getResources().getStringArray(R.array.vocab_string_array);

		String vocabString = "";

		for (String string : vocab) {
			vocabString = vocabString + string + "\n";
		}
		for (String string : vocab) {
			vocabString = vocabString + string + "\n";
		}
		for (String string : vocab) {
			vocabString = vocabString + string + "\n";
		}
		for (String string : vocab) {
			vocabString = vocabString + string + "\n";
		}
		
		vocabWords.setText(vocabString);

	}

}
