package com.cemas.s4c.hwb;

import java.util.Timer;
import java.util.TimerTask;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class QuestionAndAnswerActivity extends FragmentActivity implements
		AnswerFragment.onNextQuestion,
		QuestionFragment.onGetAnswersForQuestion, OnClickListener {

	private AnswerFragment aFrag;
	private QuestionFragment qFrag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questionandanswer);
		findViews();
		Globals.resetGivenAnswersList();
		startTimer();
	}

	private void startTimer() {
		if (Globals.getCountdownTimer() != null)
			Globals.getCountdownTimer().cancel();
		Globals.setCountdownTimer(new CountDownTimer(Globals.getTotSeconds()*1000, 1000) {

			public void onTick(long millisUntilFinished) {
				timeHeader.setText("" + millisUntilFinished / 1000);
			}

			public void onFinish() {
				timeHeader.setText("0");
			}
		});
		Globals.getCountdownTimer().start();
	}

	@Override
	public void onGetAnswersForQuestion(int question) {

		aFrag = new AnswerFragment();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

		ft.setCustomAnimations(R.anim.slide_in_right, 0);
		ft.add(R.id.placeholderFragment, aFrag);
		ft.show(aFrag);
		ft.commit();

	}

	@Override
	public void onNextQuestion(int dummy) {
		incrementQuestionNumber();

		qFrag = new QuestionFragment();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

		ft.setCustomAnimations(R.anim.slide_in_right, 0);
		ft.add(R.id.placeholderFragment, qFrag);
		ft.show(qFrag);
		ft.commit();

	}

	private void incrementQuestionNumber() {

		int questionNo = Globals.getQuestionNo() + 1;
		Globals.setQuestionNo(questionNo);
	}

	final Handler handler = new Handler();
	final Timer timer = new Timer();

	/*
	 * final Runnable updateTime = new Runnable() {
	 * 
	 * private int currentTime;
	 * 
	 * @Override public void run() { currentTime++;
	 * Globals.setCurrentSeconds(currentTime); String timeString =
	 * Util.getTimeFormatted(currentTime); timeHeader.setText(timeString);
	 * Log.d("Hwb", "time is: " + timeString); }
	 * 
	 * };
	 * 
	 * // This sets up a load of scheduled tasks to change the timer (one per //
	 * second) // and they continue to fire even if you start a new quiz or
	 * finish a quiz. public void startTimer() { for (int i = 1; i <=
	 * Globals.getTotSeconds(); i++) {
	 * 
	 * TimerTask task = new TimerTask() {
	 * 
	 * @Override public void run() { handler.post(updateTime); } };
	 * timer.schedule(task, i * 1000); } }
	 */
	private static TextView timeHeader;
	private ImageButton vocabicon;
	private TextView vocabWords;

	private void findViews() {
		timeHeader = (TextView) findViewById(R.id.time_header);
		vocabicon = (ImageButton) findViewById(R.id.vocabicon);

		vocabicon.setOnClickListener(this);
	}

	public static String getTime() {
		return timeHeader.getText().toString();
	}

	@Override
	public void onClick(View v) {
		if (v == vocabicon) {
			// custom dialog
			final Dialog dialog = new Dialog(this,
					android.R.style.Theme_Black_NoTitleBar_Fullscreen);

			dialog.setContentView(R.layout.vocab_screen);
			vocabWords = (TextView) dialog.findViewById(R.id.vocab_words);

			String[] vocab = getResources().getStringArray(
					R.array.vocab_string_array);

			String vocabString = "";

			for (String string : vocab) {
				vocabString += string + System.getProperty("line.separator");
			}

			vocabWords.setText(vocabString);

			dialog.show();
		}
	}

	@Override
	public void onBackPressed() {

		// Are you sure you want to go back?
		// If so, end quiz, reset numbers etc

		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case DialogInterface.BUTTON_POSITIVE:
					Globals.setQuestionNo(0);
					QuestionAndAnswerActivity.super.onBackPressed();
					break;

				case DialogInterface.BUTTON_NEGATIVE:
					// No button clicked
					break;
				}
			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.stop_quiz)
				.setPositiveButton(R.string.stop_quiz_yes, dialogClickListener)
				.setNegativeButton(R.string.stop_quiz_no, dialogClickListener)
				.show();

	}
}
