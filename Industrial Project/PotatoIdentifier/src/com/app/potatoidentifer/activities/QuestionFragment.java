package com.app.potatoidentifer.activities;

import android.graphics.drawable.ColorDrawable;
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
 * @author Kari
 *
 */
public class QuestionFragment extends BaseFragment implements View.OnClickListener {

    TextView textQuestion;
    Button btnYes, btnNo;
    QuestionEngine<String, String> QE; 
    String currentQuestion;

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


        String[] people = {
                new String("Bob"),
                new String("Dave"),
                new String("Ian"),
                new String("Alan"),
        };

        String blueEyes = new String("blue eyes");
        String blackHair = new String("black hair");
        String blondeHair = new String("blonde hair");
        String greenEyes = new String("green eyes");


        List<Pair<String, String>> relations = new ArrayList<Pair<String, String>>();
        relations.add(Pair.create(people[0], blueEyes));
        relations.add(Pair.create(people[0], blackHair));
        relations.add(Pair.create(people[1], blueEyes));
        relations.add(Pair.create(people[1], blondeHair));
        relations.add(Pair.create(people[2], blondeHair));
        relations.add(Pair.create(people[2], greenEyes));
        relations.add(Pair.create(people[3], greenEyes));
        relations.add(Pair.create(people[3], blackHair));

        QE = new QuestionEngine<String, String>(relations);

        refresh();

        return v;
    }


    protected void refresh()
    {
        if (QE.getPossibleAnswers().size() == 1)
        {
            textQuestion.setText("It is a " + QE.getPossibleAnswers().get(0));
            QE.ForgetAll();
        }
        else
        {
            currentQuestion = QE.determineNextQuestion();
            String q = currentQuestion + "?";

            textQuestion.setText(q.toCharArray(), 0, q.length());
        }
    }



    @Override
    public void onClick(View v)
    {
        if (btnYes.equals(v))
        {
            QE.Inform(currentQuestion, true);
        }
        else if (btnNo.equals(v))
        {
            QE.Inform(currentQuestion, false);
        }else
        {

        }
        refresh();
    }

}
