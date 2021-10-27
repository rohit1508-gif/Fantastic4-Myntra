package com.example.myntrahackathon.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myntrahackathon.Adapter.BoardAdapter;
import com.example.myntrahackathon.ModalClasses.Board;
import com.example.myntrahackathon.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LeaderboardFragment extends Fragment {
    RecyclerView recyclerView;
    BoardAdapter adapter;
    Context ctx;
    List<Board> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_leaderboard, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_view);
        ctx= getActivity();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getLeaderBoard();
    }
    public void getLeaderBoard(){
        String url="";
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(ctx));
        ProgressDialog pd = ProgressDialog.show(ctx,null,"Please wait");
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    if(pd!=null && pd.isShowing())
                        pd.dismiss();
                    for(int i=0;i<response.length();i++){
                        JSONObject obj = response.getJSONObject(i);
                        Board b = new Board(
                                obj.getString("name"),
                                obj.getInt("score")
                        );
                        list.add((b));
                    }
                    adapter = new BoardAdapter(list,ctx);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(pd!=null && pd.isShowing())
                    pd.dismiss();
            }
        });
        requestQueue.add(request);
    }
}
