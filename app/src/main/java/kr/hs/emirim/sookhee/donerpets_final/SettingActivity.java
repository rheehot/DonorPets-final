package kr.hs.emirim.sookhee.donerpets_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingActivity extends AppCompatActivity {

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

    public void onClickGoTos(View v){
        Intent intent = new Intent(SettingActivity.this, TOSActivity.class);
        startActivity(intent);
    }
}
