package kr.hs.emirim.sookhee.donerpets_final;

import android.app.Application;

public class DataApplication extends Application {
    private int gPosition;

    public void setPosition(int position){
        this.gPosition = position;
    }

    public int getPosition(){
        return this.gPosition;
    }
}
