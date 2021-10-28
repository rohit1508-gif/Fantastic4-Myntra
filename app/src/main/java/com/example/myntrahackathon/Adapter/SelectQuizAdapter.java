package com.example.myntrahackathon.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myntrahackathon.ModalClasses.QuizInfo;
import com.example.myntrahackathon.QuizClickListener;
import com.example.myntrahackathon.R;

import java.util.List;

public class SelectQuizAdapter extends RecyclerView.Adapter<SelectQuizAdapter.MyHolder> {

    List<QuizInfo> quizzes;
    QuizClickListener clickListener;

    public SelectQuizAdapter(List<QuizInfo> quizzes, QuizClickListener clickListener) {
        this.quizzes = quizzes;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quiz_select, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        QuizInfo quiz = quizzes.get(position);
        holder.tvQuizName.setText(quiz.getName());
        holder.tvQuizDescription.setText(quiz.getDescription());
        holder.cvMain.setOnClickListener(v -> clickListener.onQuizSelect(quiz.getId()));
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView tvQuizName, tvQuizDescription;
        CardView cvMain;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvQuizName = itemView.findViewById(R.id.tvQuizName);
            tvQuizDescription = itemView.findViewById(R.id.tvQuizDescription);
            cvMain = itemView.findViewById(R.id.cvMain);
        }
    }
}
