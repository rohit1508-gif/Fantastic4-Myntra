package com.example.myntrahackathon.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myntrahackathon.Adapter.LeaderboardAdapter;
import com.example.myntrahackathon.MainActivity;
import com.example.myntrahackathon.ModalClasses.LeaderboardUser;
import com.example.myntrahackathon.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardFragment extends Fragment {
    RecyclerView recyclerView;
    LeaderboardAdapter adapter;
    List<LeaderboardUser> users;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_leaderboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity.goToFragment ="Leaderboard";
        users = new ArrayList<>();

        recyclerView = view.findViewById(R.id.rvLeaderboard);
        recyclerView.setHasFixedSize(true);
        getLeaderBoard();
    }

    public void getLeaderBoard() {
        String url = "https://fantastic4-myntra.herokuapp.com/leaderboard";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        ProgressDialog pd = ProgressDialog.show(getContext(), null, "Fetching the leaders...");
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (pd != null && pd.isShowing())
                        pd.dismiss();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        LeaderboardUser b = new LeaderboardUser(
                                obj.getString("user_id"),
                                obj.getString("username"),
                                obj.getInt("score")
                        );
                        users.add((b));
                    }
                    adapter = new LeaderboardAdapter(users);
                    recyclerView.setAdapter(adapter);
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
}
