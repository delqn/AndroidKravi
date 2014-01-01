package com.delqn.android.kravi;

import com.delqn.android.androidkravi.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.widget.TextView;

public class HelpActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		TextView helpTextView = (TextView) findViewById(R.id.helpText);
		String htext = helpTextView.getText().toString();
		htext = "<p><strong>Крави и Бикове</strong> е логическа игра за отгатване на цифри. " +
				"Играе се срещу компютъра като целта е да се отгатне " +
				"произволно избраното число. Числото е четирицифрено без " +
				"повтарящи се цифри. След всеки ход, " +
				"противникът(компютър) дава броя на съвпаденията.</p><p>Играта протича " +
				"по следния начин. Компютърът генерира число и ви кани да го " +
				"познаете. След това, вие въвеждате предположения за числото " +
				"на противника. Противникът отговаря, като посочва " +
				"броя на съвпаденията -- ако дадена цифра от предположението" +
				" се съдържа в тайното число и се намира на точното място, " +
				"тя е „бик“, ако пък е на различно място, е „крава“.</p>" +
				"Пример:<br/>" +
				"- Тайно число: <strong>4271<strong><br/>" +
				"- Предположение: <strong>1234</strong><br/>" +
				"- Отговор: <strong>„1 бик и 2 крави“.</strong> (Бикът е „2“, а кравите " +
				"са „4“ и „1“.)<br/><p>" +
				"На всеки ход играчите записват предположените числа и " +
				"отговорите, за да могат чрез дедукция да идентифицират " +
				"цифрите в тайното число на противника." +
				"Първият играч, който открие тайното число на противника, " +
				"е победител. Тъй като участникът, който започва, има " +
				"логическо предимство, победителят от предната игра ще " +
				"играе втори на ход. В някои случаи всеки играч има равен " +
				"брой ходове, така че, ако първият пръв открие числото на " +
				"противника, вторият има право на още един ход и ако и той " +
				"успее, играта завършва наравно.</p>";
		helpTextView.setText(Html.fromHtml(htext));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}
}
