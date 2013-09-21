package com.delqn.android.kravi;

import java.util.ArrayList;
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

/* Let's use these colors:
 * Mild:
 * accent -- #826aa9 
 * background -- #e9e0aa
 * light -- #e4bc74
 * dark -- #be9766
 *
 * Black and Yellow and Grey:
 * yellow -- #f9f400
 * light grey -- #999999
 * dark grey -- #666666
 * black -- #000000
 * note - got it from Color Index - Accents p.291
 */

public class KraviActivity extends Activity {

	private String randomNumber = "";
	private Integer tries = 0;
	final ArrayList<String> mHistoryList = new ArrayList<String>();
	final ArrayList<ResultOfGuess> mResultsList = new ArrayList<ResultOfGuess>();

	private void makeAllButtonsVisible() {
		findViewById(R.id.button0).setVisibility(View.VISIBLE);
		findViewById(R.id.button1).setVisibility(View.VISIBLE);
		findViewById(R.id.button2).setVisibility(View.VISIBLE);
		findViewById(R.id.button3).setVisibility(View.VISIBLE);
		findViewById(R.id.button4).setVisibility(View.VISIBLE);
		findViewById(R.id.button5).setVisibility(View.VISIBLE);
		findViewById(R.id.button6).setVisibility(View.VISIBLE);
		findViewById(R.id.button7).setVisibility(View.VISIBLE);
		findViewById(R.id.button8).setVisibility(View.VISIBLE);
		findViewById(R.id.button9).setVisibility(View.VISIBLE);
		
		final ListView mResultsListView = (ListView)findViewById(R.id.results_list_view);

		AwesomeArrayAdapter aaa = new AwesomeArrayAdapter(this, mResultsList);
		mResultsListView.setAdapter(aaa);
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

	private void checkNumberAndLogMessage() {
		final TextView mNumberEnterred = (TextView)findViewById(R.id.numberEnterred);
		tries++;
		ResultOfGuess cowsBulls = getCowsBulls(mNumberEnterred.getText().toString());
		mHistoryList.add(mNumberEnterred.getText().toString());
		mResultsList.add(cowsBulls);
		mNumberEnterred.setText("");
		makeAllButtonsVisible();
		if(cowsBulls.getBulls() == 4) {
			Toast.makeText(KraviActivity.this,
					TextUtils.concat("Bravo! You guessed the number (", randomNumber.toString(), ") in ", tries.toString(), " tries!"),
					Toast.LENGTH_LONG).show();
			initGame();
		}
	}

	// *** NOT USED ***
	/*
	private View.OnTouchListener buttonTouchListener = new View.OnTouchListener() {
		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		@Override
		public boolean onTouch(View v, MotionEvent me) {

			if(me.getAction() == MotionEvent.ACTION_MOVE) {
				Log.d("com.delqn", "------------->  Moved to:  x=" + me.getRawX() + "  y=" + me.getRawY());
				
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
			
			final TextView mNumberEnterred = (TextView)findViewById(R.id.numberEnterred);
			mNumberEnterred.setText(
					TextUtils.concat(
							mNumberEnterred.getText(),
							clickedButton.getText().toString().trim()));
			
			if(mNumberEnterred.getText().length() == 4)
				checkNumberAndLogMessage();
		}
	};
	
	public void buttonClickListener(View v) {
		buttonClickListener.onClick(v);
	}

	/*
	private void announceBeginning() {
		mHistoryList.add("I came up with a number!  You try to guess it!");
	}*/
	
	private void initGame() {
		((TextView)findViewById(R.id.numberEnterred)).setText("");
		mHistoryList.clear();
		mResultsList.clear();
		randomNumber = thinkOfANumber();
		//this.announceBeginning();
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
				// TODO Auto-generated method stub
				final TextView mNumberEnterred = (TextView)findViewById(R.id.numberEnterred);
				mNumberEnterred.setText("");
				makeAllButtonsVisible();
			}
		});

		tries = 0;
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