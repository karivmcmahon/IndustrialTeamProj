package com.app.potatoidentifer.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.potatoidentifier.R;

/**
 * This class sets up the fragment for the expert system
 * @author Kari
 *
 */
public class QuestionFragment extends BaseFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.question_fragment_layout, container, false);
        return v;
    }
}
