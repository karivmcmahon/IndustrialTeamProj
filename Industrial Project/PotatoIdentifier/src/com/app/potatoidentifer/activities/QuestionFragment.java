package com.app.potatoidentifer.activities;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.app.potatoidentifer.models.QuestionEngine;
import com.example.potatoidentifier.R;

import java.util.ArrayList;
import java.util.List;

/**
 * This class sets up the fragment for the expert system
 * 
 * @author Kari
 * 
 */
public class QuestionFragment extends BaseFragment implements View.OnClickListener {

	TextView textQuestion;
	Button btnYes, btnNo;
	QuestionEngine<String, String> QE;
	String currentQuestion;
	boolean displayingAnswer = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.question_fragment_layout, container, false);

		btnYes = (Button) v.findViewById(R.id.buttonYes);
		btnNo = (Button) v.findViewById(R.id.buttonNo);
		textQuestion = (TextView) v.findViewById(R.id.textQuestion);

		btnYes.setOnClickListener(this);
		btnNo.setOnClickListener(this);

		List<Pair<String, String>> relations = new ArrayList<Pair<String, String>>();

		for (int i = 0; i < QuestionEngine.symptoms.length; i += 2) {
			relations.add(Pair.create(QuestionEngine.symptoms[i], QuestionEngine.symptoms[i + 1]));
		}

		QE = new QuestionEngine<String, String>(relations);

		refresh();

		return v;
	}

	protected void refresh() {
		if (QE.getPossibleAnswers().size() == 1) {
			textQuestion.setText("It has " + QE.getPossibleAnswers().get(0));
			displayingAnswer = true;
			QE.ForgetAll();
		} else {
			displayingAnswer = false;
			currentQuestion = QE.determineNextQuestion();
			String q = "Does it have " + currentQuestion + "?";

			textQuestion.setText(q.toCharArray(), 0, q.length());
		}
	}

	@Override
	public void onClick(View v) {

		if (!displayingAnswer) {
			if (btnYes.equals(v)) {
				QE.Inform(currentQuestion, true);
			} else if (btnNo.equals(v)) {
				QE.Inform(currentQuestion, false);
			} else {

			}
		}
		refresh();
	}

}
