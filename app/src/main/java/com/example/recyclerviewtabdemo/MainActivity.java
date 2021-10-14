package com.example.recyclerviewtabdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> AllTuneNames = new ArrayList<>((Arrays.asList("Beauty and the Best",
                                                                "Lion King", "Mary Poppins", "Game of thrones",
                                                                "Ozark")));
    List<Integer> AllTunePics = new ArrayList<>(Arrays.asList(R.drawable.beauty, R.drawable.lionking,
                                                                R.drawable.marypoppins, R.drawable.gameofthrones,
                                                                R.drawable.ozark));
    List<Tune> AllTunes = new ArrayList<>();
    List<Tune> MovieTunes = new ArrayList<>();
    List<Tune> TVTunes = new ArrayList<>();
    final String TAG = "RecyclerViewDemo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadModelData();
        Log.d(TAG, AllTunes.size() + " items in the all tunes list.");


        //11. call the recyclerView
        RecyclerView recyclerViewTunes = findViewById(R.id.recyclerViewTunes);
        //we want two columns
        GridLayoutManager gm = new GridLayoutManager(this, 2);
        recyclerViewTunes.setLayoutManager(gm);//set layout manager
        TuneAdapter tuneAdapter = new TuneAdapter(AllTunes, this);
        recyclerViewTunes.setAdapter(tuneAdapter);
        //should work now

        //13. only after setting event listener inside the adapter. set the tab functionality
        TabLayout tuneTabs = findViewById(R.id.tabLayout);
        tuneTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tuneTabs.getSelectedTabPosition()){
                    case 0:
                        tuneAdapter.setTuneList(AllTunes);
                        //tuneAdapter.notifyDataSetChanged(); can be called here or inside the adapter setTuneList
                        break;
                    case 1:
                        tuneAdapter.setTuneList(MovieTunes);
                        break;
                    case 2:
                        tuneAdapter.setTuneList(TVTunes);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void LoadModelData(){
        for(int i=0; i< AllTuneNames.size(); i++){
            Tune eachTune = new Tune(AllTuneNames.get(i),  AllTunePics.get(i));
            AllTunes.add(eachTune);
        }

        MovieTunes = AllTunes.subList(0,3); //first param is starting index
        TVTunes = AllTunes.subList(3,5);//upper bound is exclusive
    }
}