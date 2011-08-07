package com.elasticpath.tutorial;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
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

public class AndroidTutorialActivity extends Activity {
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

		listAdapter = new MainListAdapter(this, R.layout.main_list_item, developers, getLayoutInflater());
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
			final Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
			startActivityForResult(intent, PICK_CONTACT_REQUEST);
			return true;
		case R.id.test:
			final DeveloperDTO developerDTO = new DeveloperDTO("Test",	"loves");
			developers.add(developerDTO);
			listAdapter.notifyDataSetChanged();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}