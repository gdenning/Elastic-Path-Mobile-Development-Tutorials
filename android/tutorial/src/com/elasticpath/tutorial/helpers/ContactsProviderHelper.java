package com.elasticpath.tutorial.helpers;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.elasticpath.tutorial.dtos.DeveloperDTO;

public class ContactsProviderHelper {
	
	public static DeveloperDTO getDeveloperForContactUri(final Activity activity, final Uri contactUri) {
		final DeveloperDTO developer = new DeveloperDTO();
		final Cursor contactCursor =  activity.managedQuery(
				contactUri,
				null,
				null,
				null,
				null);
		if (contactCursor.moveToFirst()) {
			developer.setName(contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
			developer.setVerb("loves");
			final String contactId = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts._ID));
			// Find Email Addresses
			final Cursor emailCursor = activity.managedQuery(
					ContactsContract.CommonDataKinds.Email.CONTENT_URI,
					null,
					ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId,
					null,
					null);
			while (emailCursor.moveToNext()) {
				final String emailAddress = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
				developer.getEmails().add(emailAddress);
			}
		}
		
		return developer;
	}

}
