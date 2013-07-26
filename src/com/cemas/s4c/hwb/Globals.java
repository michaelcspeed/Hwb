package com.cemas.s4c.hwb;

import java.util.ArrayList;

import android.os.CountDownTimer;

public class Globals {

	private static String updatedDate = "";
	private static final String UPDATE_DATE_KEY = "updated_date_key";
	private static final String HWB_PACKAGE_NAME = "com.cemas.hwb";
	private static int questionNo;
	private static int totSeconds = 2000;
	private static int currentSeconds;
	private static Quiz currentQuiz;
	private static ArrayList<Boolean> givenAnswersList;
	private static String timeCompletedIn;
	private static CountDownTimer countdownTimer;

	public static String getUpdatedDate() {
		return updatedDate;
	}

	public static void setUpdatedDate(String updatedDate) {
		Globals.updatedDate = updatedDate;
	}

    public static String getUpdateDateKey() {
		return UPDATE_DATE_KEY;
	}

    public static String getHwbPackageName() {
		return HWB_PACKAGE_NAME;
	}

	public static int getQuestionNo() {
		return questionNo;
	}

	public static void setQuestionNo(int questionNo) {
		Globals.questionNo = questionNo;
	}

	public static int getCurrentSeconds() {
		return currentSeconds;
	}

	public static void setCurrentSeconds(int currentSeconds) {
		Globals.currentSeconds = currentSeconds;
	}

	public static int getTotSeconds() {
		return totSeconds;
	}

	public static Quiz getCurrentQuiz() {
		return currentQuiz;
	}

	public static void setCurrentQuiz(Quiz currentQuiz) {
		Globals.currentQuiz = currentQuiz;
	}

	public static ArrayList<Boolean> getGivenAnswersList() {
		return givenAnswersList;
	}

	public static void setGivenAnswersList(ArrayList<Boolean> givenAnswersList) {
		Globals.givenAnswersList = givenAnswersList;
	}

	public static int getNumberOfCorrectAnswers() {

		int tot = 0;

		for (Boolean item : givenAnswersList) {
			if (item)
				tot++;
		}

		return tot;

	}
	
	public static void resetGivenAnswersList(){
		if(givenAnswersList!=null)
		givenAnswersList.clear();
	}
	
	public static int getNumberOfQuestions(){
	
		return getCurrentQuiz().getQuestions().length;
		
	}

	public static String getTimeCompletedIn() {
		return timeCompletedIn;
	}

	public static void setTimeCompletedIn(String timeCompletedIn) {
		Globals.timeCompletedIn = timeCompletedIn;
	}

	public static CountDownTimer getCountdownTimer() {
		return countdownTimer;
	}

	public static void setCountdownTimer(CountDownTimer countdownTimer) {
		Globals.countdownTimer = countdownTimer;
	}

	public static void setTotSeconds(int totSeconds) {
		Globals.totSeconds = totSeconds;
	}
	
}
