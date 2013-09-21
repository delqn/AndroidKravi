package com.delqn.android.kravi;

import java.util.ArrayList;
import java.util.Stack;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.delqn.android.androidkravi.R;

public class AwesomeArrayAdapter extends ArrayAdapter{
	private Context context;
	private ArrayList<ResultOfGuess> values;

	public AwesomeArrayAdapter(Context context, ArrayList<ResultOfGuess> values) {
		super(context, R.layout.rowlayout, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Stack<String> animals = new Stack<String>();
		ResultOfGuess v = values.get(position);
		Log.d("delyanraychev","Bulls: " + v.getBulls());
		Log.d("delyanraychev","Cows: " + v.getCows());
		for(int i=0; i < v.getBulls(); i++) {
			animals.push("B");
			Log.d("delyanraychev","Pushing a BULL ");
		}
		for(int i=0; i < v.getCows(); i++) {
			animals.push("C");
			Log.d("delyanraychev","Pushing a COW ");
		}
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
		textView.setText(values.get(position).getGuessedNumber());
		return rowView;
	}
}
