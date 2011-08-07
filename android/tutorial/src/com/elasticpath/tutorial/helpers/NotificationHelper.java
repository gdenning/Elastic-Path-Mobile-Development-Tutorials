package com.elasticpath.tutorial.helpers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.elasticpath.tutorial.DeveloperInfoActivity;
import com.elasticpath.tutorial.dtos.DeveloperDTO;

public class NotificationHelper {
	public static void showNotification(final NotificationManager notificationManager,
			final Context context, final DeveloperDTO developer) {
		final int icon = android.R.drawable.alert_dark_frame;
		final long when = System.currentTimeMillis();

		final Notification n = new Notification(icon, developer.getName(), when);
		n.defaults |= Notification.DEFAULT_LIGHTS;
		n.defaults |= Notification.DEFAULT_SOUND;
		n.flags |= Notification.FLAG_AUTO_CANCEL;

		final Intent intent = new Intent(context, DeveloperInfoActivity.class);
		// setData method is required so that Android will differentiate between intents
		intent.setData(Uri.fromParts("developer", String.valueOf(developer.hashCode()), null));
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("developer", developer);
		final PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

		n.setLatestEventInfo(context,
				developer.getName(),
				developer.getName() + " was just added.",
				pendingIntent);

		notificationManager.notify(0, n);
	}
}
