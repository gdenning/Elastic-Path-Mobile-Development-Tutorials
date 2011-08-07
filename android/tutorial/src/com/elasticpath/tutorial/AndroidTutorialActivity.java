package com.elasticpath.tutorial;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidTutorialActivity extends Activity {
	private TextView helloTextView;
	private EditText nameEditText;
	private Button lovesEPButton;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		helloTextView = (TextView) findViewById(R.id.helloText);
		nameEditText = (EditText) findViewById(R.id.nameEdit);
		lovesEPButton = (Button) findViewById(R.id.lovesEPButton);

		helloTextView.setText("Hello mobile developers!");
		lovesEPButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				final Toast toast = Toast.makeText(AndroidTutorialActivity.this, nameEditText.getText() + " loves Elastic Path!", Toast.LENGTH_LONG);
				toast.show();
			}
		});
	}
}