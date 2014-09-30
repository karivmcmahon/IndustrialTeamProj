package com.app.potatoidentifer.activities;

import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.app.potatoidentifer.models.*;
import com.example.potatoidentifier.R;

import java.util.ArrayList;
import java.util.List;
import com.app.potatoidentifer.models.DatabaseHelper;


/**
 * This class sets up the fragment for the expert system
 * @author Kari
 *
 */
public class QuestionFragment extends BaseFragment implements View.OnClickListener {

    TextView textQuestion;
    Button btnYes, btnNo;
    QuestionEngine<FurtherInfoBean, String> QE;
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

        QuestionDataSource ds = new QuestionDataSource(this.getActivity());

        List<Pair<FurtherInfoBean, String>> kno = ds.getKnowledge();

        QE = new QuestionEngine<FurtherInfoBean, String>(kno);

        refresh();

        return v;
    }


    protected void refresh()
    {
        if (QE.getPossibleAnswers().size() == 1)
        {
            textQuestion.setText("It has " + QE.getPossibleAnswers().get(0).getSymptom());
            displayingAnswer = true;
            QE.ForgetAll();
        }
        else
        {
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
