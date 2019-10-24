package kr.hs.emirim.sookhee.donerpets_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DonationActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    FirebaseDatabase donationDatabase;
    DatabaseReference myDonation;

    TextView text_phone;
    TextView text_account;

    EditText edit_type;
    EditText edit_count;

    private String shelterPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        Intent intent = getIntent();
        //shelterPosition = intent.getExtras().getString("shelterPosition");
        shelterPosition = "1";

        text_account = (TextView)findViewById(R.id.text_account);
        text_phone = (TextView) findViewById(R.id.text_phone);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("shelter").child(shelterPosition);

        // Read from the database
        // 그리고 데이터베이스에 변경사항이 있으면 실행된다.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String phone = dataSnapshot.child("phone").getValue(String.class);
                String account = dataSnapshot.child("account").getValue(String.class);

                //데이터를 화면에 출력해 준다.
                text_phone.setText(phone);
                text_account.setText(account);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("DONATION", "Failed to read value.", error.toException());
            }
        });

    }

    public void onAllowDonation(View v){
        DataApplication MyData = (DataApplication)getApplication();
        edit_type = (EditText) findViewById(R.id.edit_type);
        edit_count = (EditText)findViewById(R.id.edit_count);
        donationDatabase = FirebaseDatabase.getInstance();
        myDonation = donationDatabase.getReference("user").child(SaveSharedPreference.getEmail(this));

        String type = edit_type.getText().toString();
        int count = Integer.parseInt(edit_count.getText().toString());

        DonationData donationData = new DonationData();
        donationData.type = type;
        donationData.count = count;
        donationData.shelter = Integer.parseInt(shelterPosition);
        myDonation.child("donation").push().setValue(donationData);

        finish();
    }
    public void onBackClick(View v){
        super.onBackPressed();
    }
}