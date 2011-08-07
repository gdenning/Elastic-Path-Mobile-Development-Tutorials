package com.elasticpath.tutorial.adapters;

import java.util.List;

import com.elasticpath.tutorial.R;
import com.elasticpath.tutorial.R.id;
import com.elasticpath.tutorial.R.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainListAdapter extends ArrayAdapter<String> {

	private final LayoutInflater inflater;

	public MainListAdapter(final Context context, final int textViewResourceId,
			final List<String> objects, final LayoutInflater inflater) {
		super(context, textViewResourceId, objects);
		this.inflater = inflater;
	}

	@Override
	public View getView(final int position, final View convertView, final ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			row = inflater.inflate(R.layout.main_list_item, parent, false);
		}
		final String name = getItem(position);
		if (name != null) {
			final TextView rowText = (TextView) row.findViewById(R.id.nameText);
			rowText.setText(name);
		}
		return row;
	}
}