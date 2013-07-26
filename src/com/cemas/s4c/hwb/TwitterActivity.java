package com.cemas.s4c.hwb;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class TwitterActivity extends Activity {

	private TextView successText;
	private TextView resultText;
	private Button shareButton;
	private ImageButton vocabButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.twitter_selection);

		shareButton = (Button) findViewById(R.id.shareButton);
		resultText = (TextView) findViewById(R.id.correct_out_of_20);
		successText = (TextView) findViewById(R.id.successText);
		successText.setVisibility(View.INVISIBLE);
		vocabButton = (ImageButton) findViewById(R.id.vocabicon);
		try {
			resultText.setText(Globals.getNumberOfCorrectAnswers() + "/"
					+ Globals.getNumberOfQuestions());
		} catch (Exception e) {
			resultText.setText("-");
		}

		shareButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				String twitterString = "I've just scored "
						+ Globals.getNumberOfCorrectAnswers() + "/"
						+ Globals.getNumberOfQuestions()
						+ " with "
						+ Globals.getTimeCompletedIn()
						+ " seconds remaining on Hwb's Gwenyn Geirfa app for Welsh learners! %23learnwelsh %23cymraeg www.hwb.s4c.com";

				String url = "https://twitter.com/intent/tweet?source=webclient&text="
						+ twitterString;
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);

			}
		});
		vocabButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(TwitterActivity.this, VocabActivity.class);
				startActivity(i);
			}
		});
	}
}
