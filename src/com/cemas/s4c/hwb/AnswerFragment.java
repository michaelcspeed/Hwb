package com.cemas.s4c.hwb;

import java.util.ArrayList;

import com.cemas.s4c.hwb.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class AnswerFragment extends android.support.v4.app.Fragment implements
		OnClickListener {
	private onNextQuestion mCallback;
	private View v;

	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		v = inflater.inflate(R.layout.answerfragment, container, false);
		// Do stuff to the fragment view in here if you want
		findViews();
		return v;
	}

	public interface onNextQuestion {
		public void onNextQuestion(int dummy);
	}

	@Override
	public void onAttach( Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception
		try {
			mCallback = (onNextQuestion) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement this.");
		}
	}

	private Button answerAButton;
	private Button answerBButton;
	private Button answerCButton;
	private Button answerDButton;

	private void findViews() {
		answerAButton = (Button) v.findViewById(R.id.answerA_button);
		answerBButton = (Button) v.findViewById(R.id.answerB_button);
		answerCButton = (Button) v.findViewById(R.id.answerC_button);
		answerDButton = (Button) v.findViewById(R.id.answerD_button);

		answerAButton.setEnabled(true);
		answerBButton.setEnabled(true);
		answerCButton.setEnabled(true);
		answerDButton.setEnabled(true);
		
		answerAButton.setOnClickListener(this);
		answerBButton.setOnClickListener(this);
		answerCButton.setOnClickListener(this);
		answerDButton.setOnClickListener(this);

		populateAnswers();
	}

	private void populateAnswers() {

		Quiz cq = Globals.getCurrentQuiz();
		Question[] qa = cq.getQuestions();

		Question q = qa[Globals.getQuestionNo()];
		Answer[] as = q.getAnswers();

		Log.d("hwb", "Length of answer qs: " + as.length);
		try {
			answerAButton.setText(as[0].getAnswer_text());
		} catch (Exception e) {
			answerAButton.setVisibility(View.INVISIBLE);
		}
		try {
			answerBButton.setText(as[1].getAnswer_text());
		} catch (Exception e) {
			answerBButton.setVisibility(View.INVISIBLE);
		}
		try {
			answerCButton.setText(as[2].getAnswer_text());
		} catch (Exception e) {
			answerCButton.setVisibility(View.INVISIBLE);
		}
		try {
			answerDButton.setText(as[3].getAnswer_text());
		} catch (Exception e) {
			answerDButton.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public void onClick(View v) {

		boolean lastQuestion = (Globals.getQuestionNo() == Globals
				.getCurrentQuiz().getQuestions().length - 1) ? true : false;

		if (v == answerAButton) {
			checkAnswer(0);
		} else if (v == answerBButton) {
			checkAnswer(1);
		} else if (v == answerCButton) {
			checkAnswer(2);
		} else if (v == answerDButton) {
			checkAnswer(3);
		}

		answerAButton.setEnabled(false);
		answerBButton.setEnabled(false);
		answerCButton.setEnabled(false);
		answerDButton.setEnabled(false);		
		
		//End of quiz?
		if (lastQuestion) {
			Globals.setTimeCompletedIn(QuestionAndAnswerActivity.getTime());
			Intent i = new Intent(getActivity(), AnswerSheetActivity.class);
			startActivity(i);
		} else {
			mCallback.onNextQuestion(1);
		}
	}

	private void checkAnswer(int answerNumber) {

		if (Globals.getGivenAnswersList() == null) {
			Globals.setGivenAnswersList(new ArrayList<Boolean>());
		}

		if (Globals.getCurrentQuiz().getQuestions()[Globals.getQuestionNo()]
				.getAnswers()[answerNumber].isCorrect_yn()) {
		//	Toast.makeText(getActivity(), "Correct!", Toast.LENGTH_SHORT)
		//			.show();
			Globals.getGivenAnswersList().add(true);
		} else {
		//	Toast.makeText(getActivity(), "WRONG!", Toast.LENGTH_SHORT).show();
			Globals.getGivenAnswersList().add(false);
		}
	}
}
