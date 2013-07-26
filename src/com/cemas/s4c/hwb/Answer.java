package com.cemas.s4c.hwb;

import com.cemas.s4c.hwb.R;

public class Answer {

	private String answer_text;
	private boolean correct_yn;

	public Answer() {
	}

	public String getAnswer_text() {
		return answer_text;
	}

	public void setAnswer_text(String answer_text) {
		this.answer_text = answer_text;
	}

	public boolean isCorrect_yn() {
		return correct_yn;
	}

	public void setCorrect_yn(boolean correct_yn) {
		this.correct_yn = correct_yn;
	}

	public Answer(String answer_text, boolean corrent_yn) {
		super();
		this.answer_text = answer_text;
		this.correct_yn = corrent_yn;
	}

}
