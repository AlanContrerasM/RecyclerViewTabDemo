package com.example.recyclerviewtabdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//1. make it extend RecyclerView.Adapter, that's it.
//3. now we add<TuneAdapter2.TuneViewHolder2>
//4. now implement the methods suggested,.
public class TuneAdapter2 extends RecyclerView.Adapter<TuneAdapter2.TuneViewHolder2> {
    //5. create the global variables
    List<Tune> TuneList;
    Context context;
    int currentPlayInd = -1; //keeps track of currently playing tune. -1 is nothing is playing

    //6. Generate constructor with our global variables
    public TuneAdapter2(List<Tune> tuneList, Context context) {
        TuneList = tuneList;
        this.context = context;
    }

    //7. generate getter and setter for TuneList and currentPlaInd
    public List<Tune> getTuneList() {
        return TuneList;
    }

    public void setTuneList(List<Tune> tuneList) {
        TuneList = tuneList;
        setCurrentPlayInd((-1));
        notifyDataSetChanged();
    }

    public int getCurrentPlayInd() {
        return currentPlayInd;
    }

    public void setCurrentPlayInd(int currentPlayInd) {
        this.currentPlayInd = currentPlayInd;
    }

    @NonNull
    @Override
    public TuneViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //8. start working here
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tune2,parent,false);

        TuneViewHolder2 myHolder = new TuneViewHolder2(itemView);
        myHolder.tuneTextView2 = itemView.findViewById(R.id.txtViewtune2);
        myHolder.tuneImgView2 = itemView.findViewById(R.id.imgViewTune2);
        myHolder.tunePlayPauseImgView = itemView.findViewById(R.id.imgViewPlayPause);

        //here we can set up click listeners here, or onBindViewHolder, both work, separate functionality
        //for example... choose this or the one on bind...
//        myHolder.tunePlayPauseImgView.setOnClickListener((view)->{
//            if(currentPlayInd == myHolder.getAdapterPosition()){
//                currentPlayInd = -1;
//                notifyDataSetChanged();
//            }else{
//                currentPlayInd = myHolder.getAdapterPosition();
//                notifyDataSetChanged();
//            }
//        });

        //Dont forget to change the return value
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TuneViewHolder2 holder, int position) {
        //9. conitnue setting the bind
        position = holder.getAdapterPosition();
        holder.tuneTextView2.setText(TuneList.get(position).getTuneName());
        holder.tuneImgView2.setImageResource(TuneList.get(position).getTunePic());

        //here we set the image for the play buttons, if its currently playing ort not
        if(currentPlayInd == position){
            holder.tunePlayPauseImgView.setImageResource(R.drawable.pause);
        }else{
            holder.tunePlayPauseImgView.setImageResource(R.drawable.play);
        }

        //can set up click listeners here
        holder.tunePlayPauseImgView.setOnClickListener((view)->{
            if(currentPlayInd == holder.getAdapterPosition()){
                //if current is clicked, change from pause to clcked,
                //first change that we are playing nothing. and notift
                currentPlayInd = -1;
                notifyDataSetChanged();
            }else{
                currentPlayInd = holder.getAdapterPosition();
                notifyDataSetChanged();
            }


        });

    }

    @Override
    public int getItemCount() {
        //10. change this return to the list size.
        return TuneList.size();
    }

    //2 create this class from scratch and then create the suggested constructorr
    public class TuneViewHolder2 extends RecyclerView.ViewHolder{
        TextView tuneTextView2;
        ImageView tuneImgView2;
        ImageView tunePlayPauseImgView;

        public TuneViewHolder2(@NonNull View itemView) {
            super(itemView);
        }
    }
}
