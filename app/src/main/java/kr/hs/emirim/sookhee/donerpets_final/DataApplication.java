package kr.hs.emirim.sookhee.donerpets_final;

import android.app.Application;

public class DataApplication extends Application {
    private String shelterPosition = "10";

    public String getShelterPosition() {
        return shelterPosition;
    }

    public void setShelterPosition(String shelterPosition) {
        this.shelterPosition = shelterPosition;
    }


}