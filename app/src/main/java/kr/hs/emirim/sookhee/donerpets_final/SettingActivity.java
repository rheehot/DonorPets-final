package kr.hs.emirim.sookhee.donerpets_final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    DataApplication MyData = (DataApplication) getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

    }

    public void onLogout(View v){
        SaveSharedPreference.clearUserName(this);
        finish();
    }

    public void onBackClick(View v){
        super.onBackPressed();
    }
}
