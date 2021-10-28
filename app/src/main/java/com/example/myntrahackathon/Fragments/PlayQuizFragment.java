package com.example.myntrahackathon.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myntrahackathon.ModalClasses.Question;
import com.example.myntrahackathon.R;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlayQuizFragment extends Fragment {
    String quizId;
    List<Question> questions;
    TextView tvOption1,tvOption2,tvOption3,tvOption4;
    int i,correctAns=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_play_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        questions = new ArrayList<>();
        tvOption1 = view.findViewById(R.id.tvOption1);
        tvOption2 = view.findViewById(R.id.tvOption2);
        tvOption3 = view.findViewById(R.id.tvOption3);
        tvOption4 = view.findViewById(R.id.tvOption4);
        if (getArguments() != null) {
            quizId = getArguments().getString("quizId");
            getQuestion();
        }
        i=0;
        while(i<questions.size()){
            Question l = questions.get(i);
            ((TextView)view.findViewById(R.id.tvQuestion)).setText(l.getQuestion());
            Picasso.get().load(l.getImage()).into((ImageView) view.findViewById(R.id.ivQuestion));
            tvOption1.setText(l.getOption1());
            tvOption2.setText(l.getOption2());
            tvOption3.setText(l.getOption3());
            tvOption4.setText(l.getOption4());
            tvOption1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isCorrect("a",l.getCorrectOption());
                    ((TextView)view.findViewById(R.id.tvPoints)).setText(correctAns + " Points");
                }
            });
            tvOption2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isCorrect("b",l.getCorrectOption());
                    ((TextView)view.findViewById(R.id.tvPoints)).setText(correctAns + " Points");
                }
            });
            tvOption3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isCorrect("c",l.getCorrectOption());
                    ((TextView)view.findViewById(R.id.tvPoints)).setText(correctAns + " Points");
                }
            });
            tvOption4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isCorrect("d",l.getCorrectOption());
                    ((TextView)view.findViewById(R.id.tvPoints)).setText(correctAns + " Points");
                }
            });
            ((MaterialButton)view.findViewById(R.id.btnNext)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i++;
                }
            });
        }
    }
    public void getQuestion(){
        String url = "";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        ProgressDialog pd = ProgressDialog.show(getContext(), null, "Please wait");
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (pd != null && pd.isShowing())
                        pd.dismiss();
                    for (i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                       Question b = new Question(
                                obj.getString("question"),
                                obj.getString("image"),
                                obj.getString("option1"),
                                obj.getString("option2"),
                                obj.getString("option3"),
                                obj.getString("option4"),
                                obj.getString("correctOption"),
                               (List<String>) obj.getJSONArray("imgUrl")
                       );
                       questions.add((b));
                    }
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

    public void isCorrect(String optionClicked,String correctOption){
        tvOption1.setClickable(false);
        tvOption2.setClickable(false);
        tvOption3.setClickable(false);
        tvOption4.setClickable(false);
        switch (optionClicked) {
            case "a":
                tvOption1.setBackgroundColor(correctOption.equals("a")?Integer.parseInt("green"):Integer.parseInt("red"));
                break;
            case "b":
                tvOption2.setBackgroundColor(correctOption.equals("b")?Integer.parseInt("green"):Integer.parseInt("red"));
                break;
            case "c":
                tvOption3.setBackgroundColor(correctOption.equals("c")?Integer.parseInt("green"):Integer.parseInt("red"));
                break;
           case "d":
               tvOption4.setBackgroundColor(correctOption.equals("d")?Integer.parseInt("green"):Integer.parseInt("red"));
               break;
        }
        if(correctOption.equals(optionClicked))
        correctAns++;
    }
}
