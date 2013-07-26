package com.cemas.s4c.hwb;

import com.cemas.s4c.hwb.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionFragment extends android.support.v4.app.Fragment implements
		OnClickListener {
	private onGetAnswersForQuestion mCallback;
	private View v;

	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		v = inflater.inflate(R.layout.questionfragment, container, false);

		findViews();
		fwdButton.setEnabled(true);
		return v;
	}

	private void populateQuestions() {

		Quiz cq = Globals.getCurrentQuiz();
		Question[] qa = cq.getQuestions();

		Question q = qa[Globals.getQuestionNo()];
		String qtext = q.getQuestion_text();

		questionText.setText(qtext);

	}

	public interface onGetAnswersForQuestion {
		public void onGetAnswersForQuestion(int thisQuestion);
	}

	@Override
	public void onAttach( Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception
		try {
			mCallback = (onGetAnswersForQuestion) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement this!");
		}

	}

	private TextView questionText;
	private ImageButton fwdButton;

	private void findViews() {
		questionText = (TextView) v.findViewById(R.id.question_text);
		fwdButton = (ImageButton) v.findViewById(R.id.fwd_button);

		fwdButton.setOnClickListener(this);

		populateQuestions();

	}

	@Override
	public void onClick(View v) {
		if (v == fwdButton) {
			fwdButton.setEnabled(false);
			Log.d("hwb",
					"Question number in Question Fragment: "
							+ Globals.getQuestionNo());
			mCallback.onGetAnswersForQuestion(Globals.getQuestionNo());
		}
	}

}
