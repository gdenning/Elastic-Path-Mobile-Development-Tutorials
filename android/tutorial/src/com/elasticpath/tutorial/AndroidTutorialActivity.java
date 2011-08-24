package com.elasticpath.tutorial;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.elasticpath.tutorial.adapters.MainListAdapter;
import com.elasticpath.tutorial.dtos.DeveloperDTO;
import com.elasticpath.tutorial.helpers.ContactsProviderHelper;
import com.elasticpath.tutorial.helpers.DownloadFileHelper;
import com.elasticpath.tutorial.helpers.NotificationHelper;

public class AndroidTutorialActivity extends Activity {
	private static final int PROGRESS_DIALOG = 0;
	private static final int PICK_CONTACT_REQUEST = 0;

	private SharedPreferences preferences;
	private NotificationManager notificationManager;

	private TextView helloTextView;
	private EditText nameEditText;
	private Spinner verbSpinner;
	private Button lovesEPButton;
	private ListView listView;

	private MainListAdapter listAdapter;

	private final List<DeveloperDTO> developers = new ArrayList<DeveloperDTO>();

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		helloTextView = (TextView) findViewById(R.id.helloText);
		nameEditText = (EditText) findViewById(R.id.nameEdit);
		verbSpinner = (Spinner) findViewById(R.id.verbSpinner);
		lovesEPButton = (Button) findViewById(R.id.lovesEPButton);
		listView = (ListView) findViewById(R.id.developersList);

		listAdapter = new MainListAdapter(this, R.layout.main_list_item,
				developers, getLayoutInflater());
		listView.setAdapter(listAdapter);

		lovesEPButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				final DeveloperDTO developerDTO = new DeveloperDTO(
						nameEditText.getText().toString(),
						(String) verbSpinner.getSelectedItem());
				addDeveloperToList(developerDTO);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		helloTextView.setText(preferences.getString("CAPTION", "Hello mobile developers!"));
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		final MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.selectContact:
			final Intent intent = new Intent(Intent.ACTION_PICK,
					ContactsContract.Contacts.CONTENT_URI);
			startActivityForResult(intent, PICK_CONTACT_REQUEST);
			return true;
		case R.id.test:
			final DeveloperDTO developerDTO = new DeveloperDTO("Test", "loves");
			addDeveloperToList(developerDTO);
			return true;
		case R.id.settings:
			launchSettingsActivity();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected Dialog onCreateDialog(final int id) {
		switch (id) {
		case PROGRESS_DIALOG:
			final ProgressDialog progressDialog = new ProgressDialog(this);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progressDialog.setMessage("Downloading file...");
			return progressDialog;
		default:
			return null;
		}
	}

	@Override
	protected void onActivityResult(final int requestCode, final int resultCode, final Intent intent) {
		if (requestCode == PICK_CONTACT_REQUEST) {
			if (resultCode == RESULT_OK) {
				final Uri contactUri = intent.getData();
				final DeveloperDTO developerDTO = ContactsProviderHelper.getDeveloperForContactUri(this, contactUri);
				addDeveloperToList(developerDTO);
			}
		}
	}

	private void addDeveloperToList(final DeveloperDTO developerDTO) {
		developers.add(developerDTO);
		listAdapter.notifyDataSetChanged();
		if (preferences.getBoolean("SHOW_NOTIFICATIONS", true) == true) {
			NotificationHelper.showNotification(notificationManager, AndroidTutorialActivity.this, developerDTO);
		}
		//new DownloadFileTask().execute("http://dl.dropbox.com/u/132371/Staff%20Meeting%20Slides%20Example.pptx");
	}

	private void launchSettingsActivity() {
		final Intent i = new Intent(this, SettingsActivity.class);
		startActivity(i);
	}

	class DownloadFileTask extends AsyncTask<String, Void, Void> {
		@Override
		protected Void doInBackground(final String... params) {
			DownloadFileHelper.downloadFile(params[0]);
			return null;
		}

		@Override
		protected void onPreExecute() {
			// Show progress dialog
			showDialog(PROGRESS_DIALOG);
		}

		@Override
		protected void onPostExecute(final Void result) {
			dismissDialog(PROGRESS_DIALOG);
		}
	}
}