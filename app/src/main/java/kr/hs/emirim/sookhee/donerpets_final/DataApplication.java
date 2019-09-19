package kr.hs.emirim.sookhee.donerpets_final;

import android.app.Application;


public class DataApplication extends Application {
    private int storyPosition = 0;
    private int shelterPosition = 0;
    private String userName = "noname";
    private boolean loginCheck = false;

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

    public void setUsername(String username){
        this.userName = username;
    }

    public String getUserName(){
        return this.userName;
    }

    public void onLogin(){loginCheck = true;}

    public void offLogin(){loginCheck = false;}

    public boolean getLogin(){return loginCheck;}

}