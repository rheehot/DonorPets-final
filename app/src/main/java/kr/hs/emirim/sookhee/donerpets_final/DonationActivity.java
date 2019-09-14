package kr.hs.emirim.sookhee.donerpets_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DonationActivity extends AppCompatActivity {

    private static final String TAG = "Donation";

    FirebaseDatabase database;
    DatabaseReference myRef;

    FirebaseDatabase donationDatabase;
    DatabaseReference myDonation;

    TextView text_name;
    TextView text_phone;
    TextView text_account;

    EditText edit_type;
    EditText edit_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        DataApplication MyData = (DataApplication)getApplication();

        text_name = (TextView) findViewById(R.id.text_name);
        text_account = (TextView)findViewById(R.id.text_account);
        text_phone = (TextView) findViewById(R.id.text_phone);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("shelter").child(String.valueOf(MyData.getShelterPosition()));

        // Read from the database
        // 그리고 데이터베이스에 변경사항이 있으면 실행된다.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String name = dataSnapshot.child("name").getValue(String.class);
                String phone = dataSnapshot.child("phone").getValue(String.class);
                String account = dataSnapshot.child("account").getValue(String.class);
                //데이터를 화면에 출력해 준다.
                text_name.setText(name);
                text_phone.setText(phone);
                text_account.setText(account);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void onBackClick(View v){
        super.onBackPressed();
    }


    public void onAllowDonation(View v){
        DataApplication MyData = (DataApplication)getApplication();
        edit_type = (EditText) findViewById(R.id.edit_type);
        edit_count = (EditText)findViewById(R.id.edit_count);
        donationDatabase = FirebaseDatabase.getInstance();
        myDonation = donationDatabase.getReference("user").child(MyData.getUserName());

        String type = edit_type.getText().toString();
        int count = Integer.parseInt(edit_count.getText().toString());

        DonationData donationData = new DonationData();
        donationData.type = type;
        donationData.count = count;
        myDonation.child("donation").push().setValue(donationData);

        MyData.addDonationCount();
        myDonation.child("count").setValue(MyData.getDonationCount());
        Toast.makeText(getApplicationContext(), "기부 감사합니다", Toast.LENGTH_SHORT).show();
        finish();
    }
}
