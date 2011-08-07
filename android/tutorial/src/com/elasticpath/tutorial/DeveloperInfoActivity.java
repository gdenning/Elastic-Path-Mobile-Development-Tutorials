package com.elasticpath.tutorial;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.elasticpath.tutorial.dtos.DeveloperDTO;

public class DeveloperInfoActivity extends Activity {
	private TextView developerText;
	private TextView verbText;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.developer_info);

		developerText = (TextView) findViewById(R.id.developerText);
		verbText = (TextView) findViewById(R.id.verbText);

		final DeveloperDTO developer = (DeveloperDTO) getIntent().getExtras().getSerializable("developer");
		developerText.setText(developer.getName());
		verbText.setText(developer.getVerb());
	}

}
