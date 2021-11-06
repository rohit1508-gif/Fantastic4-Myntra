package com.example.myntrahackathon.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myntrahackathon.MainActivity;
import com.example.myntrahackathon.ModalClasses.Question;
import com.example.myntrahackathon.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlayQuizFragment extends Fragment {
    private String quizId, quizName;
    private List<Question> questions;
    private TextView tvOption1, tvOption2, tvOption3, tvOption4, tvQuestion;
    private int correctAns = 0, questionIndex;
    private Button btnNext;
    private ImageView ivQuestion, recommendation_1, recommendation_2;
    private LottieAnimationView lottie_timer;
    private CountDownTimer countDownTimer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_play_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity.goToFragment = "NoAction";
        questions = new ArrayList<>();
        tvOption1 = view.findViewById(R.id.tvOption1);
        tvOption2 = view.findViewById(R.id.tvOption2);
        tvOption3 = view.findViewById(R.id.tvOption3);
        tvOption4 = view.findViewById(R.id.tvOption4);
        tvQuestion = view.findViewById(R.id.tvQuestion);
        btnNext = view.findViewById(R.id.btnNext);
        ivQuestion = view.findViewById(R.id.ivQuestion);
        lottie_timer = view.findViewById(R.id.lottie_timer);
        recommendation_1 = view.findViewById(R.id.recommendation_1);
        recommendation_2 = view.findViewById(R.id.recommendation_2);

        if (getArguments() != null) {
            quizId = getArguments().getString("quizId");
            quizName = getArguments().getString("quizName");
        }
        getActivity().setTitle(quizName);
        getQuestion();
    }

    private void getQuestion() {
        String url = "https://fantastic4-myntra.herokuapp.com/quiz/:" + quizId + "/game";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        ProgressDialog pd = ProgressDialog.show(getContext(), null, "Loading quiz...");
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (pd != null && pd.isShowing())
                    pd.dismiss();
                for (int i = 0; i < response.length(); i++) {
                    JSONObject obj = response.optJSONObject(i);
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
                questionIndex = 0;
                setQuestion();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (pd != null && pd.isShowing())
                    pd.dismiss();
                Toast.makeText(getContext(), "Couldn't load. Error occurred !", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                if (questionIndex == 5) {
                    submitScores();
                } else {
                    Toast.makeText(getContext(), "Time's up... Moving to next question", Toast.LENGTH_SHORT).show();
                    questionIndex++;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    setQuestion();
                }
            }
        }.start();
    }

    private void submitScores() {
        lottie_timer.pauseAnimation();
        Fragment scoreFragment = new ScoreFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("Score", correctAns);
        bundle.putString("QuizId", quizId);
        bundle.putString("QuizName", quizName);
        scoreFragment.setArguments(bundle);
        if (getFragmentManager() != null) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, new ScoreFragment());
            transaction.commit();
        }
    }

    private void isCorrect(String optionClicked, String correctOption) {
        tvOption1.setClickable(false);
        tvOption2.setClickable(false);
        tvOption3.setClickable(false);
        tvOption4.setClickable(false);
        switch (optionClicked) {
            case "a":
                tvOption1.setBackgroundColor(correctOption.equals("a") ? Color.GREEN : Color.RED);
                break;
            case "b":
                tvOption2.setBackgroundColor(correctOption.equals("b") ? Color.GREEN : Color.RED);
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

    private void setBackground(Context context) {
        ViewCompat.setBackgroundTintList(tvOption1, ContextCompat.getColorStateList(context, R.color.white));
        ViewCompat.setBackgroundTintList(tvOption2, ContextCompat.getColorStateList(context, R.color.white));
        ViewCompat.setBackgroundTintList(tvOption3, ContextCompat.getColorStateList(context, R.color.white));
        ViewCompat.setBackgroundTintList(tvOption4, ContextCompat.getColorStateList(context, R.color.white));
    }

    private void setQuestion() {
        setBackground(getContext());
        Question question = questions.get(questionIndex);
        tvQuestion.setText(question.getQuestion());
        Picasso.get().load(question.getImage()).into(ivQuestion);
        tvOption1.setText(question.getOption1());
        tvOption2.setText(question.getOption2());
        tvOption3.setText(question.getOption3());
        tvOption4.setText(question.getOption4());
        if (questionIndex == 4) {
            btnNext.setText("Submit");
        }
        Picasso.get().load(question.getRecom1()).into(recommendation_1);
        Picasso.get().load(question.getRecom2()).into(recommendation_2);
        setOnClickListeners(question, questionIndex);
        lottie_timer.playAnimation();
        startTimer();
    }

    private void setOnClickListeners(Question question, int ind) {
        tvOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCorrect("a", question.getCorrectOption());
                lottie_timer.pauseAnimation();
                countDownTimer.cancel();
            }
        });

        tvOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCorrect("b", question.getCorrectOption());
                lottie_timer.pauseAnimation();
                countDownTimer.cancel();
            }
        });

        tvOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCorrect("c", question.getCorrectOption());
                lottie_timer.pauseAnimation();
                countDownTimer.cancel();
            }
        });

        tvOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCorrect("d", question.getCorrectOption());
                lottie_timer.pauseAnimation();
                countDownTimer.cancel();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionIndex == 5) {
                    submitScores();
                } else {
                    questionIndex++;
                    setQuestion();
                }
            }
        });
    }
}
