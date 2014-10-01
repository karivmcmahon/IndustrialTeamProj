package com.app.potatoidentifer.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.app.potatoidentifer.models.FurtherInfoBean;
import com.app.potatoidentifer.models.QuestionDataSource;
import com.app.potatoidentifer.models.QuestionEngine;
import com.example.potatoidentifier.R;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class QuestionFragment extends BaseFragment implements View.OnClickListener {
    View v;
    ViewPager myViewPager;
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
        v = inflater.inflate(R.layout.question_fragment_layout, container, false);
        btnYes = (Button) v.findViewById(R.id.buttonYes);
        btnNo = (Button) v.findViewById(R.id.buttonNo);
        textQuestion = (TextView) v.findViewById(R.id.textQuestion);
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);
        QuestionDataSource ds = new QuestionDataSource(this.getActivity());
        List<Pair<FurtherInfoBean, String>> kno = ds.getKnowledge();
        QE = new QuestionEngine<FurtherInfoBean, String>(kno);

        ds.close();

        refresh();
        return v;
    }
    protected void refresh()
    {
        if (QE.getPossibleAnswers().size() == 1)
        {
            displayingAnswer = true;
            QE.ForgetAll();
            FurtherInfoBean ans = QE.getPossibleAnswers().get(0);
            Bundle bundle = new Bundle();
            bundle.putString("category", ans.getSymptom());
            FurtherInfo gf = new FurtherInfo();
            gf.setArguments(bundle);
            fragmentTabActivity.addFragments(Const.TAB_SECOND, gf, true);
        }
        else
        {
            displayingAnswer = false;
            currentQuestion = QE.determineNextQuestion();
            String q = "Does it have " + currentQuestion + "?";
            textQuestion.setText(q.toCharArray(), 0, q.length());
            ArrayList<Bitmap> slideshowImageArray = new ArrayList<Bitmap>();
            QE.Inform(currentQuestion, true);
            List<FurtherInfoBean> furtherInfo = QE.getPossibleAnswers();
            QE.Forget(currentQuestion);
            for (int i = 0; i < furtherInfo.size(); i++) {
                byte[] id1 = furtherInfo.get(i).getImageID();
                if (id1 != null && id1.length > 100) {
                    ByteArrayInputStream imageStream = new ByteArrayInputStream(id1);
                    slideshowImageArray.add(BitmapFactory.decodeStream(imageStream));
                }
                byte[] id2 = furtherInfo.get(i).getImageID2();
                if (id2 != null && id2.length > 100) {
                    ByteArrayInputStream imageStream = new ByteArrayInputStream(id2);
                    slideshowImageArray.add(BitmapFactory.decodeStream(imageStream));
                }
                byte[] id3 = furtherInfo.get(i).getImageID3();
                if (id3 != null&& id3.length > 100) {
                    ByteArrayInputStream imageStream = new ByteArrayInputStream(id3);
                    slideshowImageArray.add(BitmapFactory.decodeStream(imageStream));
                }
                byte[] id4 = furtherInfo.get(i).getImageID4();
                if (id4 != null&& id4.length > 100 ) {
                    ByteArrayInputStream imageStream = new ByteArrayInputStream(id4);
                    slideshowImageArray.add(BitmapFactory.decodeStream(imageStream));
                }
                byte[] id5 = furtherInfo.get(i).getImageID5();
                if (id5 != null && id5.length > 100) {
                    ByteArrayInputStream imageStream = new ByteArrayInputStream(id5);
                    slideshowImageArray.add(BitmapFactory.decodeStream(imageStream));
                }
                byte[] id6 = furtherInfo.get(i).getImageID6();
                if (id6 != null&& id6.length > 100 ) {
                    ByteArrayInputStream imageStream = new ByteArrayInputStream(id6);
                    slideshowImageArray.add(BitmapFactory.decodeStream(imageStream));
                }
            }

            SlideshowViewPagerAdapter adapter = new SlideshowViewPagerAdapter(getActivity(), slideshowImageArray, this.getActivity());
            myViewPager = (ViewPager) v.findViewById(R.id.slideshowviewpager);
            myViewPager.setAdapter(adapter);
            myViewPager.setCurrentItem(0);
            myViewPager.setOnPageChangeListener(viewPagerListenerHandler);
            initButton();
        }
    }
    private List<Button> buttonArray;
    private int buttonIds[] = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6};
    private void initButton() {
        buttonArray = new ArrayList<Button>();
        for (int id : buttonIds) {
            Button button = (Button) v.findViewById(id);
            if (id == 0) {
                setButton(button, "round_cell_off");
            } else {
                setButton(button, "round_cell_on");
            }
            buttonArray.add(button);
        }
    }
    
    private void setButton(Button btn, String layoutType) {
        if (layoutType.equals("round_cell_on")) {
            btn.setBackgroundResource(R.drawable.round_cell_on);
        } else {
            btn.setBackgroundResource(R.drawable.round_cell_off);
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

    private ViewPager.OnPageChangeListener viewPagerListenerHandler = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrollStateChanged(int position) {}
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {}
        @Override
        public void onPageSelected(int position) {
            btnAction(position);
        }
    };

    private void btnAction(int position) {
        Button btn;
        for (int id = 0; id < buttonIds.length; id++) {
            if (id == position) {
                btn = buttonArray.get(id);
                setButton(btn, "round_cell_off");
            } else {
                btn = buttonArray.get(id);
                setButton(btn, "round_cell_on");
            }
        }
    }
}
