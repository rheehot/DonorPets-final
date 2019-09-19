package kr.hs.emirim.sookhee.donerpets_final;

import android.app.Application;


public class DataApplication extends Application {
    private int storyPosition = 0;
    private int shelterPosition = 0;

    public void setStoryPosition(int position){
        this.storyPosition = position;
    }

    public int getStoryPosition(){
        return this.storyPosition;
    }

    public void setShelterPosition(int position){
        this.shelterPosition = position;
    }

    public int getShelterPosition(){
        return this.shelterPosition;
    }



}