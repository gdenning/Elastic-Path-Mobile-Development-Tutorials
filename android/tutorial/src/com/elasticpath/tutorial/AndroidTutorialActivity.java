package com.elasticpath.tutorial;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import com.elasticpath.tutorial.helpers.DownloadFileHelper;

public class AndroidTutorialActivity extends Activity {
	private static final int PROGRESS_DIALOG = 0;
	private static final int PICK_CONTACT_REQUEST = 0;

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

		helloTextView = (TextView) findViewById(R.id.helloText);
		nameEditText = (EditText) findViewById(R.id.nameEdit);
		verbSpinner = (Spinner) findViewById(R.id.verbSpinner);
		lovesEPButton = (Button) findViewById(R.id.lovesEPButton);
		listView = (ListView) findViewById(R.id.developersList);

		listAdapter = new MainListAdapter(this, R.layout.main_list_item,
				developers, getLayoutInflater());
		listView.setAdapter(listAdapter);

		helloTextView.setText("Hello mobile developers!");
		lovesEPButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				final DeveloperDTO developerDTO = new DeveloperDTO(
						nameEditText.getText().toString(),
						(String) verbSpinner.getSelectedItem());
				developers.add(developerDTO);
				listAdapter.notifyDataSetChanged();
				new DownloadFileTask().execute("http://dl.dropbox.com/u/132371/Staff%20Meeting%20Slides%20Example.pptx");
			}
		});
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
			developers.add(developerDTO);
			listAdapter.notifyDataSetChanged();
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