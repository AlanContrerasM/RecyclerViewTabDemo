package com.example.recyclerviewtabdemo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//3. add the <TuneAdapter.TuneViewHolder>, next to Adapter, after creating the other stuff.
//4. now import methods suggested here
public class TuneAdapter extends RecyclerView.Adapter<TuneAdapter.TuneViewHolder> {
    //5. create List and variables that will help us., create getter and setter for TuneList
    List<Tune> TuneList;
    Context context;

    public List<Tune> getTuneList() {
        return TuneList;
    }

    public void setTuneList(List<Tune> tuneList) {
        TuneList = tuneList;
        //5.5 don't forget to add the notify
        notifyDataSetChanged();
    }

    //6. after creating your need vars, generate a constructor
    public TuneAdapter(List<Tune> tuneList, Context context) {
        TuneList = tuneList;
        this.context = context;
    }

    //created automatically by step 4
    @NonNull
    @Override
    public TuneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //responsible for inflating  the external layout
        //7. start creating the inflator
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tune, parent,
                                                                                    false);

        //8. create the tune view holder object
        TuneViewHolder myTuneViewHolder = new TuneViewHolder(itemView);
        myTuneViewHolder.tuneImageView = itemView.findViewById(R.id.imageViewTune);
        myTuneViewHolder.tuneTextView = itemView.findViewById(R.id.txtviewTune);
        myTuneViewHolder.tuneItemView = itemView; //not needed

        //resets bg color when it is created.
        myTuneViewHolder.tuneItemView.setBackgroundColor(Color.parseColor("#FAFAFA"));

        //12.. eventlisteners, only set up after step 11 on mainactivity and making sure it works. and everything shows
        myTuneViewHolder.tuneItemView.setOnClickListener((View v)->{
            if(myTuneViewHolder.tuneItemView.getBackground() instanceof ColorDrawable
                && ((ColorDrawable) myTuneViewHolder.tuneItemView.getBackground()).getColor() != Color.LTGRAY){
                //first checking if its a solid color, and then comparing
                myTuneViewHolder.tuneItemView.setBackgroundColor(Color.LTGRAY);
            }else{
                myTuneViewHolder.tuneItemView.setBackgroundColor(Color.parseColor("#FAFAFA"));
            }
        });
        return myTuneViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TuneViewHolder holder, int position) {
        //used to populate the data of the view holder object
        //position corresponds to the adapter data index
        //10. create this
        holder.tuneImageView.setImageResource((TuneList.get(position).getTunePic()));
        holder.tuneTextView.setText(TuneList.get(position).getTuneName());

        //if you want to reset the colors can be done on the onbind,
        holder.tuneItemView.setBackgroundColor(Color.parseColor("#FAFAFA"));

    }

    @Override
    public int getItemCount() {
        //represents the size of your adapter data
        //9. update the return value
        return TuneList.size();
    }

    //1.create this class from scratch and the create the suggested constructor
    public class TuneViewHolder extends RecyclerView.ViewHolder{
        //2.create the variables you need.
        TextView tuneTextView;
        ImageView  tuneImageView;
        View tuneItemView; //not really needed
        public TuneViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
