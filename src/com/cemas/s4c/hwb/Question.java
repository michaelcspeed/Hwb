package com.cemas.s4c.hwb;

import com.cemas.s4c.hwb.R;

public class Question {

	private int question_number;
	private String question_text;
	private Answer[] answers;
	
	public Question(){}

	public Question(int question_number, String question_text, Answer[] answers) {
		super();
		this.question_number = question_number;
		this.question_text = question_text;
		this.answers = answers;
	}

	public int getQuestion_number() {
		return question_number;
	}

	public void setQuestion_number(int question_number) {
		this.question_number = question_number;
	}

	public String getQuestion_text() {
		return question_text;
	}

	public void setQuestion_text(String question_text) {
		this.question_text = question_text;
	}

	public Answer[] getAnswers() {
		return answers;
	}

	public void setAnswers(Answer[] answers) {
		this.answers = answers;
	}


}
