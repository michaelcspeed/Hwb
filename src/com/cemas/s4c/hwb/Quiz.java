package com.cemas.s4c.hwb;

import com.cemas.s4c.hwb.R;

public class Quiz {

	private String ref;
	private String broadcast_dt;
	private Question[] questions;

	public Quiz() {
		// TODO Auto-generated constructor stub
	} 

	public Quiz(String ref, String broadcast_dt, Question[] questions) {
		super();
		this.ref = ref;
		this.broadcast_dt = broadcast_dt;
		this.questions = questions;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getBroadcast_dt() {
		return broadcast_dt;
	}

	public void setBroadcast_dt(String broadcast_dt) {
		this.broadcast_dt = broadcast_dt;
	}

	public Question[] getQuestions() {
		return questions;
	}

	public void setQuestions(Question[] questions) {
		this.questions = questions;
	}
}
