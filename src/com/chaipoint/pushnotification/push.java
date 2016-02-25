package com.chaipoint.pushnotification;

import java.io.IOException;
import java.util.ArrayList;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;

public class push {

	// Put your Google API Server Key here
	private static final String GOOGLE_SERVER_KEY = "API_KEY";
	static final String MESSAGE_KEY = "message";

	public String sendMessage(ArrayList<String> regIds) {

		MulticastResult result = null;

		// GCM RedgId of Android device to send push notification

		try {

			String userMessage = "message";
			Sender sender = new Sender(GOOGLE_SERVER_KEY);
			Message message = new Message.Builder().timeToLive(30).delayWhileIdle(true)
					.addData(MESSAGE_KEY, userMessage).build();

			result = sender.send(message, regIds, 1);

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}
}
