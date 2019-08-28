package com.example.sportivo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeBackgroundFragment extends Fragment {


    public HomeBackgroundFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_home_background, container, false);

        List<String> titles = new ArrayList<>();

        titles.add("1");
        titles.add("2");
        titles.add("3");
        titles.add("4");
        titles.add("5");
        titles.add("6");
        titles.add("7");
        titles.add("8");
        titles.add("9");
        titles.add("10");


        RecyclerView recyclerView = view.findViewById(R.id.opportunitylistcards);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RecyclerViewAdapter(titles));

        return view;

        /*Spinner started

        Spinner spinner = (Spinner)view.findViewById(R.id.sportsnamespinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.numbers,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Object item=adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;

        //Spinner ended

        */

    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private CardView mCardView;
        private TextView Title;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        public RecyclerViewHolder(LayoutInflater inflater , ViewGroup container){

            super(inflater.inflate(R.layout.cardview,container,false));

            mCardView = itemView.findViewById(R.id.cardview);
            Title = itemView.findViewById(R.id.titletext);

        }
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

        private List<String> mList;

        public RecyclerViewAdapter(List<String> titles){

            this.mList = titles;

        }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new RecyclerViewHolder(inflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

            holder.Title.setText(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }
}
