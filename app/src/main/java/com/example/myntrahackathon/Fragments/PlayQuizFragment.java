package com.example.myntrahackathon.Fragments;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myntrahackathon.ModalClasses.Question;
import com.example.myntrahackathon.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlayQuizFragment extends Fragment {
    String quizId;
    List<Question> questions;
    TextView tvOption1, tvOption2, tvOption3, tvOption4, tvTimer;
    int correctAns = 0;
    Button btnNext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_play_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        questions = new ArrayList<>();
        tvTimer = view.findViewById(R.id.tvTimer);
        tvOption1 = view.findViewById(R.id.tvOption1);
        tvOption2 = view.findViewById(R.id.tvOption2);
        tvOption3 = view.findViewById(R.id.tvOption3);
        tvOption4 = view.findViewById(R.id.tvOption4);
        btnNext = view.findViewById(R.id.btnNext);

        if (getArguments() != null) {
            quizId = getArguments().getString("quizId");

        }
        getQuestion(view);

    }

    public void getQuestion(View view) {
        String url = "https://fantastic4-myntra.herokuapp.com/quiz/game";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        ProgressDialog pd = ProgressDialog.show(getContext(), null, "Please wait");
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (pd != null && pd.isShowing())
                        pd.dismiss();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        Question question = new Question(
                                obj.optString("Question"),
                                obj.optString("Ques_image"),
                                obj.optString("Option_1"),
                                obj.optString("Option_2"),
                                obj.optString("Option_3"),
                                obj.optString("Option_4"),
                                obj.optString("Correct_ans"),
                                obj.optString("Recom_1"),
                                obj.optString("Recom_2")
                        );
                        questions.add(question);
                    }
                    setQuestions(view,0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (pd != null && pd.isShowing())
                    pd.dismiss();
            }
        });
        requestQueue.add(request);
    }

    public void isCorrect(String optionClicked, String correctOption) {
        tvOption1.setClickable(false);
        tvOption2.setClickable(false);
        tvOption3.setClickable(false);
        tvOption4.setClickable(false);
        switch (optionClicked) {
            case "a":
                tvOption1.setBackgroundColor(correctOption.equals("a") ? Color.GREEN : Color.RED);
                break;
            case "b":
                tvOption2.setBackgroundColor(correctOption.equals("b") ? Color.GREEN: Color.RED);
                break;
            case "c":
                tvOption3.setBackgroundColor(correctOption.equals("c") ? Color.GREEN : Color.RED);
                break;
            case "d":
                tvOption4.setBackgroundColor(correctOption.equals("d") ? Color.GREEN : Color.RED);
                break;
        }
        if (correctOption.equals(optionClicked))
            correctAns++;
    }
    public void setbackground(){
        tvOption1.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.boardcardview));
        tvOption2.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.boardcardview));
        tvOption3.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.boardcardview));
        tvOption4.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.boardcardview));
    }
    public void setQuestions(View view,int ind){
        setbackground();
            Question l = questions.get(ind);
            ((TextView) view.findViewById(R.id.tvQuestion)).setText(l.getQuestion());
            //Picasso.get().load(l.getImage()).into((ImageView) view.findViewById(R.id.ivQuestion));
            tvOption1.setText(l.getOption1());
            tvOption2.setText(l.getOption2());
            tvOption3.setText(l.getOption3());
            tvOption4.setText(l.getOption4());
            if(ind==4){
                btnNext.setText("Submit");
            }
            tvOption1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isCorrect("a", l.getCorrectOption());
                    ((TextView) view.findViewById(R.id.tvPoints)).setText(correctAns + " Points");
                }
            });
            tvOption2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isCorrect("b", l.getCorrectOption());
                    ((TextView) view.findViewById(R.id.tvPoints)).setText(correctAns + " Points");
                }
            });
            tvOption3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isCorrect("c", l.getCorrectOption());
                    ((TextView) view.findViewById(R.id.tvPoints)).setText(correctAns + " Points");
                }
            });
            tvOption4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isCorrect("d", l.getCorrectOption());
                    ((TextView) view.findViewById(R.id.tvPoints)).setText(correctAns + " Points");
                }
            });
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   setQuestions(view,ind+1);
                }
            });
        if(ind==5){
            Fragment scoreFragment = new ScoreFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("Score", correctAns);
            scoreFragment.setArguments(bundle);
            if (getFragmentManager() != null) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, new ScoreFragment());
                transaction.commit();
            }
        }
    }
}
