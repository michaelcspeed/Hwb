package com.cemas.s4c.hwb;

import java.util.Timer;
import java.util.TimerTask;

import android.util.Log;
import com.cemas.s4c.hwb.R;

/**
 * Simple demo that uses java.util.Timer to schedule a task to execute once 5
 * seconds have passed.
 */

public class TimerUpdater {

	Timer timer;
	int numSec = 30;
	
	public TimerUpdater() {

		timer = new Timer();
		timer.schedule(new MyTimerTask(), 0, // initial delay
				1 * 1000); // subsequent rate
	}

	class MyTimerTask extends TimerTask {
		

		public void run() {
			if (numSec > 0) {
				Log.d("Hwb","Beep! " + numSec);
				numSec--;
			} else {
				Log.d("Hwb","Time's up!%n");
				timer.cancel(); // Not necessary because
								// we call System.exit

			}
		}
	}
}
