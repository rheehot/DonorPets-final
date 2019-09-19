package kr.hs.emirim.sookhee.donerpets_final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ShelterActivity extends AppCompatActivity {

    //Firebase2
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter);

        final DataApplication MyData = (DataApplication)getApplication();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("shelter").child(String.valueOf(MyData.getShelterPosition()));

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String shelterName = dataSnapshot.child("name").getValue(String.class);
                String shelterPhone = dataSnapshot.child("phone").getValue(String.class);
                String shelterMark = dataSnapshot.child("mark").getValue(String.class);

                //데이터를 화면에 출력해 준다.
                TextView ShelterName = findViewById(R.id.tv_shelter_name);
                ShelterName.setText(shelterName);

                TextView ShelterPhone = findViewById(R.id.tv_shelter_phone);
                ShelterPhone.setText(shelterPhone);

                ImageView ShelterMark = findViewById(R.id.iv_shelter_mark);
                Picasso.get().load(shelterMark).into(ShelterMark);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("DetailActivitys", "Failed to read value.", error.toException());
            }
        });
    }

    public void onBackClick(View v){
        super.onBackPressed();
    }
}