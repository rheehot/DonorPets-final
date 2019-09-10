package kr.hs.emirim.sookhee.donerpets_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DonationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
    }

    public void ongoDonation(View v){
        Toast.makeText(getApplicationContext(), "기부 감사합니다", Toast.LENGTH_SHORT).show();
    }
}
