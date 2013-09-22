package com.delqn.android.kravi;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.delqn.android.androidkravi.R;

public class KraviActivity extends Activity {

	private String randomNumber = "";
	private Integer tries = 0;
	ListView mResultsListView;
	TextView mEntry;
	AwesomeArrayAdapter mResultsAdapter;
	int[] buttonsXml = { R.id.button0, R.id.button1, R.id.button2, R.id.button3,
			R.id.button4, R.id.button5, R.id.button6, R.id.button7,
			R.id.button8, R.id.button9 };
	Button[] bObjs = new Button[buttonsXml.length];
	
	private void makeAllButtonsVisible() {
		for (int i = 0; i < bObjs.length; i++)
			bObjs[i].setVisibility(View.VISIBLE);
	}

	private String thinkOfANumber() {
		Random rnd = new Random();
		String r = "";
		while(r.length() < 4) {
			String x = ((Integer)rnd.nextInt(9)).toString();
			if(!r.contains(x)) r = TextUtils.concat(r, x).toString();
		}
		return r;
	}

	private ResultOfGuess getCowsBulls(String guessedNumber) {
		ResultOfGuess x = new ResultOfGuess(0, 0, guessedNumber);
		x.setCows(0);
		x.setBulls(0);
		
		char[] rn = randomNumber.toCharArray();
		char[] gn = guessedNumber.toCharArray();

		for(int i=0; i<=3; ++i) {
			int guessedDigit = gn[i];
			if(guessedDigit == rn[i]) {
				x.setBulls(x.getBulls() +1);
			} else {
				for(int j=0; j<=3; ++j) {
					if(guessedDigit == rn[j])
						x.setCows(x.getCows() +1);
				}
			}
		}
		return x;
	}

	private void checkNumberAndLogMessage(String entry) {
		tries++;
		ResultOfGuess cowsBulls = getCowsBulls(entry);
		mResultsAdapter.add(cowsBulls);
		scrollResultsToBottom();
		resetEntryBox();
		makeAllButtonsVisible();
		if(cowsBulls.getBulls() == 4) {
			Toast.makeText(KraviActivity.this,
					TextUtils.concat("Bravo! You guessed the number (", randomNumber.toString(), ") in ", tries.toString(), " tries!"),
					Toast.LENGTH_LONG).show();
			initGame();
		}
	}

	private void scrollResultsToBottom() {
		mResultsListView.post(new Runnable(){
			@Override
			public void run() {
				mResultsListView.setSelection(mResultsListView.getCount() - 1);
			}
		});
	}

	/*
	private View.OnTouchListener buttonTouchListener = new View.OnTouchListener() {
		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		@Override
		public boolean onTouch(View v, MotionEvent me) {

			if(me.getAction() == MotionEvent.ACTION_MOVE) {
				LayoutParams params = new LayoutParams(v.getWidth(), v.getHeight());
				//set the margins. Not sure why but multiplying the height by 1.5 seems to keep my finger centered on the button while it's moving
				//params.setMargins((int)me.getRawX() - v.getWidth()/2, (int)(me.getRawY() - v.getHeight()*1.5), (int)me.getRawX() - v.getWidth()/2, (int)(me.getRawY() - v.getHeight()*1.5));
				params.setMargins(0, 0, 0, 0);

				v.setX(me.getRawX());
				v.setY(me.getRawY());
				//findViewById(R.id.button0).setLayoutParams(params);
				//v.setLayoutParams(params);
			}

			return true;
		}
	};*/
	
	private View.OnClickListener buttonClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Button clickedButton = (Button)v;
			clickedButton.setVisibility(View.INVISIBLE);
			String currentEntry = mEntry.getText().toString().replace(">", "").replace("_", "");
			CharSequence tail = "";
			for(int i=0; i<(3-currentEntry.length()); i++)
				tail = TextUtils.concat(tail, "_");

			String newDigit = clickedButton.getText().toString().trim();
			mEntry.setText(
					TextUtils.concat(
							">",
							currentEntry,
							clickedButton.getText().toString().trim(),
							tail));
			currentEntry = currentEntry.concat(newDigit);
			if(currentEntry.length() == 4)
				checkNumberAndLogMessage(currentEntry);
		}
	};
	
	public void buttonClickListener(View v) {
		buttonClickListener.onClick(v);
	}

	private void announceBeginning() {
				Toast.makeText(KraviActivity.this,
				"I came up with a number!  You try to guess it!",
					Toast.LENGTH_LONG).show();
		//mHistoryList.add("I came up with a number!  You try to guess it!");
	}
	
	private void initGame() {
		bObjs = new Button[buttonsXml.length];
		for (int i=0; i<buttonsXml.length; i++)
			bObjs[i] = (Button)findViewById(buttonsXml[i]);
		mEntry = (TextView)findViewById(R.id.numberEnterred);
		mResultsAdapter = new AwesomeArrayAdapter(this);
		mResultsListView = (ListView)findViewById(R.id.results_list_view);
		mResultsListView.setAdapter(mResultsAdapter);
		resetEntryBox();
		mResultsAdapter.clear();
		randomNumber = thinkOfANumber();
		this.announceBeginning();
		//((TextView)findViewById(R.id.textHistory)).setText(TextUtils.concat("random number: ", randomNumber, "\n"));
		makeAllButtonsVisible();
		
		((Button)findViewById(R.id.reveal_number)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(KraviActivity.this, randomNumber, Toast.LENGTH_SHORT).show();
			}
		});

		((Button)findViewById(R.id.backspace)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				resetEntryBox();
				makeAllButtonsVisible();
			}
		});

		tries = 0;
		announceBeginning();
	}

	private void resetEntryBox() {
		mEntry.setText(">____");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kravi);
		initGame();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}
}