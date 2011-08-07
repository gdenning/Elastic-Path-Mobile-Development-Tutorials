package com.elasticpath.tutorial.adapters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.elasticpath.tutorial.R;
import com.elasticpath.tutorial.dtos.DeveloperDTO;

public class MainListAdapter extends ArrayAdapter<DeveloperDTO> {

	private static final Map<String, Integer> verbToImageMap = new HashMap<String, Integer>();
	static {
		verbToImageMap.put("loves", android.R.drawable.star_big_on);
		verbToImageMap.put("likes", android.R.drawable.star_big_off);
		verbToImageMap.put("wants to work at", android.R.drawable.star_big_on);
	}

	private final LayoutInflater inflater;

	public MainListAdapter(final Context context, final int textViewResourceId,
			final List<DeveloperDTO> objects, final LayoutInflater inflater) {
		super(context, textViewResourceId, objects);
		this.inflater = inflater;
	}

	@Override
	public View getView(final int position, final View convertView, final ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			row = inflater.inflate(R.layout.main_list_item, parent, false);
		}
		final DeveloperDTO developer = getItem(position);
		if (developer != null) {
			final TextView rowText = (TextView) row.findViewById(R.id.nameText);
			final ImageView rowImage = (ImageView) row.findViewById(R.id.rowImage);
			rowText.setText(developer.getName() + " " + developer.getVerb() + " Elastic Path");
			final Integer imageResource = verbToImageMap.get(developer.getVerb());
			if (imageResource != null) {
				rowImage.setImageResource(imageResource);
			}
		}
		return row;
	}
}