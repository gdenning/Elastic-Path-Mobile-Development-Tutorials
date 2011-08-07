package com.elasticpath.tutorial;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.elasticpath.tutorial.adapters.MainListAdapter;

public class AndroidTutorialActivity extends Activity {
	private TextView helloTextView;
	private EditText nameEditText;
	private Button lovesEPButton;
	private ListView listView;

	private MainListAdapter listAdapter;

	private final List<String> names = new ArrayList<String>();

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		helloTextView = (TextView) findViewById(R.id.helloText);
		nameEditText = (EditText) findViewById(R.id.nameEdit);
		lovesEPButton = (Button) findViewById(R.id.lovesEPButton);
		listView = (ListView) findViewById(R.id.developersList);

		listAdapter = new MainListAdapter(this, R.layout.main_list_item, names, getLayoutInflater());
		listView.setAdapter(listAdapter);

		helloTextView.setText("Hello mobile developers!");
		lovesEPButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				names.add(nameEditText.getText() + " loves EP");
				listAdapter.notifyDataSetChanged();
			}
		});
	}
}