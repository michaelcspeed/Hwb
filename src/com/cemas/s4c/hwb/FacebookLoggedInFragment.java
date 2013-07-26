package com.cemas.s4c.hwb;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookRequestError;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;

public class FacebookLoggedInFragment extends Fragment {
	private Button shareButton;
	private static final List<String> PERMISSIONS = Arrays
			.asList("publish_actions");
	private static final String PENDING_PUBLISH_KEY = "pendingPublishReauthorization";
	private boolean pendingPublishReauthorization = false;
	private int RESULT_CANCELED;
	private boolean mAuthorizationCanceled = false;
	private TextView resultText;
	private TextView successText;
	private ImageButton vocabButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.selection, container, false);
		shareButton = (Button) view.findViewById(R.id.shareButton);
		resultText = (TextView) view.findViewById(R.id.correct_out_of_20);
		successText = (TextView) view.findViewById(R.id.successText);
		successText.setVisibility(View.INVISIBLE);
		vocabButton = (ImageButton) view.findViewById(R.id.vocabicon);
		try {
			resultText.setText(Globals.getNumberOfCorrectAnswers() + "/"
					+ Globals.getNumberOfQuestions());
		} catch (Exception e) {
			resultText.setText("-");
		}

		shareButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				publishStory();
			}
		});
		vocabButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), VocabActivity.class);
				startActivity(i);
			}
		});

		return view;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean(PENDING_PUBLISH_KEY, pendingPublishReauthorization);
		// this super is 'uihelper' in the facebook docs
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(getActivity(), requestCode,
				resultCode, data);
	}

	private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
		if (state.isOpened()) {
			shareButton.setVisibility(View.VISIBLE);
			if (pendingPublishReauthorization
					&& state.equals(SessionState.OPENED_TOKEN_UPDATED)) {
				pendingPublishReauthorization = false;
			}
		} else if (state.isClosed()) {
			shareButton.setVisibility(View.INVISIBLE);
		}
	}

	private void publishStory() {

		final ProgressDialog pd = new ProgressDialog(getActivity());
		pd.setMessage("Posting to Facebook");
		pd.show();

		Session session = Session.getActiveSession();

		if (session != null) {

			// Check for publish permissions
			List<String> permissions = session.getPermissions();
			if (!isSubsetOf(PERMISSIONS, permissions)) {
				pendingPublishReauthorization = true;
				Session.NewPermissionsRequest newPermissionsRequest = new Session.NewPermissionsRequest(
						this, PERMISSIONS);
				session.requestNewPublishPermissions(newPermissionsRequest);
				return;
			}

			Bundle postParams = new Bundle();
			postParams.putString("name", "Hwb");
			postParams.putString("caption", "The language learning game!");
			postParams
					.putString(
							"description",
							"I've just scored "
									+ resultText.getText().toString()
									+ " with "
									+ Globals.getTimeCompletedIn()
									+ " seconds remaining on Hwb's Gwenyn Geirfa app for Welsh learners! \n Dw i newydd sgorio "
									+ resultText.getText().toString()
									+ " ar Gwenyn Geirfa - app Hwb ar gyfer Dysgwyr Cymraeg #dysgucymraeg #learnwelsh #cymraeg");
			postParams.putString("link", "http://hwb.s4c.co.uk");
			postParams.putString("picture", "http://i.imgur.com/DWyJbOb.jpg");

			Request.Callback callback = new Request.Callback() {
				public void onCompleted(Response response) {
					try {
						JSONObject graphResponse = response.getGraphObject()

						.getInnerJSONObject();
						// String postId = null;

						// postId = graphResponse.getString("id");
					} catch (Exception e) {
						errorMessage();
						Log.i("hwb", "Error " + e.getMessage());
					}
					FacebookRequestError error = response.getError();
					if (error != null) {
						Toast.makeText(getActivity().getApplicationContext(),
								error.getErrorMessage(), Toast.LENGTH_SHORT)
								.show();
						errorMessage();
					} else {
						successMessage();
					}
				}

				private void successMessage() {
					successText.setVisibility(View.VISIBLE);
					successText.setText("Posted Successfully!");
					pd.dismiss();
				}

				private void errorMessage() {

					pd.dismiss();
					successText.setVisibility(View.VISIBLE);
					successText.setText("An error occurred. Please try again.");

				}
			};

			Request request = new Request(session, "me/feed", postParams,
					HttpMethod.POST, callback);

			RequestAsyncTask task = new RequestAsyncTask(request);
			task.execute();
		}

	}

	private boolean isSubsetOf(Collection<String> subset,
			Collection<String> superset) {
		for (String string : subset) {
			if (!superset.contains(string)) {
				return false;
			}
		}
		return true;
	}
}
