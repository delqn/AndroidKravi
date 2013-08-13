package com.bignerdranch.android.geoquiz;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {

	private String randomNumber = "";
	private Integer tries = 0;
	
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
	}

	private String thinkOfANumber() {
		Random r = new Random();
		String randomNumber = "";
		while(randomNumber.length() < 4) {
			Integer i = r.nextInt(9);
			Log.d("random digit -->", i.toString());
			String x = i.toString();
			if(!randomNumber.contains(x)) {
				randomNumber = TextUtils.concat(randomNumber, x).toString();
			}
		}
		return randomNumber;
	}

	private int[] getCowsBulls(String guess) {
		int[] cowsBulls = {0,0};
		Log.d("random number = ", randomNumber.toString());
		
		char[] rn = randomNumber.toCharArray();
		char[] gn = guess.toCharArray();
		
		for(int i=0; i<=3; ++i) {
			int guessedDigit = gn[i];
			if(guessedDigit == rn[i]) {
				cowsBulls[1]++;
			} else {
				for(int j=0; j<=3; ++j) {
					if(guessedDigit == rn[j]) cowsBulls[0]++;
				}
			}
		}
		return cowsBulls;
	}

	private void checkNumber() {
		final TextView mNumberEnterred = (TextView)findViewById(R.id.numberEnterred);
		tries++;
		int[] cowsBulls = getCowsBulls(mNumberEnterred.getText().toString());
		final TextView mTextHistory = (TextView)findViewById(R.id.textHistory);
		Integer cows = cowsBulls[0];
		Integer bulls = cowsBulls[1];
		mTextHistory.setText(TextUtils.concat(mTextHistory.getText(),
				"опит ", tries.toString(), " беше ",
				mNumberEnterred.getText(),
				" което е ",
				cows.toString(),
				" крави и ",
				 bulls.toString(),
				" бика", "\n"));
		mNumberEnterred.setText("");					
		makeAllButtonsVisible();
		if(bulls==4) {
			Toast.makeText(QuizActivity.this,
					TextUtils.concat("Браво!  Позна числото (", randomNumber.toString(), ") след ", tries.toString(), " опита!"),
					Toast.LENGTH_LONG).show();
			initGame();
		}
	}
	
	private void wireButton(Button aButton) {
		aButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Button theButton = (Button)v;
				// TODO Auto-generated method stub
				
				theButton.setVisibility(View.INVISIBLE);
				
				//Toast.makeText(QuizActivity.this, theButton.getText().toString(), Toast.LENGTH_SHORT).show();
				
				final TextView mNumberEnterred = (TextView)findViewById(R.id.numberEnterred);
				mNumberEnterred.setText(TextUtils.concat(mNumberEnterred.getText(), theButton.getText()));
				
				if(mNumberEnterred.getText().length()==4) {
					checkNumber();
				}
			}
		});
	}

	private void initGame() {
		((TextView)findViewById(R.id.numberEnterred)).setText("");
		((TextView)findViewById(R.id.textHistory)).setText("");
		randomNumber = thinkOfANumber();
		((TextView)findViewById(R.id.textHistory)).setText(TextUtils.concat("Измислих си число!  Сега ти се опитай да го познаеш!\n"));
        //((TextView)findViewById(R.id.textHistory)).setText(TextUtils.concat("random number: ", randomNumber, "\n"));
		makeAllButtonsVisible();
		
		((TextView)findViewById(R.id.hintText)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
        this.wireButton((Button)findViewById(R.id.button0));
        this.wireButton((Button)findViewById(R.id.button1));
        this.wireButton((Button)findViewById(R.id.button2));
        this.wireButton((Button)findViewById(R.id.button3));
        this.wireButton((Button)findViewById(R.id.button4));
        this.wireButton((Button)findViewById(R.id.button5));
        this.wireButton((Button)findViewById(R.id.button6));
        this.wireButton((Button)findViewById(R.id.button7));
        this.wireButton((Button)findViewById(R.id.button8));
        this.wireButton((Button)findViewById(R.id.button9));
        
        tries = 0;
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initGame();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }
}