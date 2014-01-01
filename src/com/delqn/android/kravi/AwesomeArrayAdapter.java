package com.delqn.android.kravi;

import java.util.Stack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.delqn.android.androidkravi.R;

public class AwesomeArrayAdapter extends ArrayAdapter<ResultOfGuess>{
	private Context context;

	public AwesomeArrayAdapter(Context context) {
		super(context, R.layout.rowlayout);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Stack<String> animals = new Stack<String>();
		
		ResultOfGuess v = this.getItem(position);
		for(int i=0; i < v.getBulls(); i++) animals.push("B");
		for(int i=0; i < v.getCows(); i++) animals.push("C");

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
		ImageView[] imageSlots = {
				(ImageView) rowView.findViewById(R.id.result_cell_icon0),
				(ImageView) rowView.findViewById(R.id.result_cell_icon1),
				(ImageView) rowView.findViewById(R.id.result_cell_icon2),
				(ImageView) rowView.findViewById(R.id.result_cell_icon3)
		};
		int imagePosition = 0;
		while (!animals.isEmpty()) {
			String a = animals.pop();
			if (a.equals("B"))
				imageSlots[imagePosition].setImageResource(R.drawable.tinybull); 
			else if (a.equals("C"))
				imageSlots[imagePosition].setImageResource(R.drawable.tinycow);
			imagePosition++;
		}
		TextView textView = (TextView) rowView.findViewById(R.id.result_cell_guessed_number);
		textView.setText(this.getItem(position).getGuessedNumber());
		return rowView;
	}
}
