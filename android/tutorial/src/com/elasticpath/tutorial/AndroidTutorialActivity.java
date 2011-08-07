package com.elasticpath.tutorial;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.elasticpath.tutorial.adapters.MainListAdapter;
import com.elasticpath.tutorial.dtos.DeveloperDTO;

public class AndroidTutorialActivity extends Activity {
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
}