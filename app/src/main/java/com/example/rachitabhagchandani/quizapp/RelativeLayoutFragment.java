package com.example.rachitabhagchandani.quizapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RelativeLayoutFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    QuizQuestions quizQuestions;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.relative_layout_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //adding Questions to database and display
        quizQuestions = new QuizQuestions(getActivity());
        ArrayList<Question> questions = quizQuestions.getAllQuestions();

        //Get an arraylist from db and set it to recycler view.
        recyclerView.setAdapter(new RecyclerViewAdapter(questions, getActivity()));
        return view;
    }

    public void putArguments(Bundle args){
        String answer = args.getString("answer");
        int id = args.getInt("id");
        quizQuestions.saveResponseTodb(id, answer);
        Log.d("answer",answer);
    }

}


