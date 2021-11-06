package com.example.myntrahackathon.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myntrahackathon.Adapter.SelectQuizAdapter;
import com.example.myntrahackathon.MainActivity;
import com.example.myntrahackathon.ModalClasses.Question;
import com.example.myntrahackathon.ModalClasses.QuizInfo;
import com.example.myntrahackathon.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActiveQuizzesFragment extends Fragment {

    List<QuizInfo> quizzes;
    RecyclerView recyclerView;
    SelectQuizAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_active_quizzes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().setTitle("Active Quizzes");
        MainActivity.goToFragment = "HomeFragment";
        quizzes = new ArrayList<>();

        recyclerView = view.findViewById(R.id.rvSelectQuiz);
        recyclerView.setHasFixedSize(true);

        getData();
    }

    private void getData() {
        String url = "https://fantastic4-myntra.herokuapp.com/quiz";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        ProgressDialog pd = ProgressDialog.show(getContext(), null, "Fetching quizzes...");
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (pd != null && pd.isShowing())
                    pd.dismiss();
                for (int i = 0; i < response.length(); i++) {
                    JSONObject obj = response.optJSONObject(i);
                    QuizInfo quizInfo = new QuizInfo(
                            obj.optString("Quiz_name"),
                            obj.optString("Quiz_id"),
                            obj.optString("quiz_desc")
                    );
                    quizzes.add(quizInfo);
                }
                setAdapter();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (pd != null && pd.isShowing())
                    pd.dismiss();
                Toast.makeText(getContext(), "Couldn't load.. Error occurred!", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);
        setAdapter();
    }

    private void setAdapter() {
        adapter = new SelectQuizAdapter(quizzes, this::startQuiz);
        recyclerView.setAdapter(adapter);
    }

    public void startQuiz(String quizId, String quizName) {
        Fragment playQuizFragment = new PlayQuizFragment();
        Bundle bundle = new Bundle();
        bundle.putString("quizId", quizId);
        bundle.putString("quizName", quizName);
        playQuizFragment.setArguments(bundle);
        if (getFragmentManager() != null) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, playQuizFragment);
            transaction.commit();
        }
    }
}
