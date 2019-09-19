package kr.hs.emirim.sookhee.donerpets_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.RadialGradient;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.NavigableMap;
import java.util.Random;

public class DetailActivity extends AppCompatActivity {

    // Firebase
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    // Views
    private ListView mListView;
    private EditText mEdtMessage;
    // Values
    private ChatAdapter mAdapter;

    //Firebase2
    FirebaseDatabase Storydatabase;
    DatabaseReference myRefStory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final DataApplication MyData = (DataApplication)getApplication();

        Storydatabase = FirebaseDatabase.getInstance();
        myRefStory = Storydatabase.getReference("story").child(String.valueOf(MyData.getStoryPosition()));

        myRefStory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String title = dataSnapshot.child("title").getValue(String.class);
                String img1 = dataSnapshot.child("img1").getValue(String.class);
                String img2 = dataSnapshot.child("img2").getValue(String.class);
                String img3 = dataSnapshot.child("img3").getValue(String.class);
                String img4 = dataSnapshot.child("img4").getValue(String.class);
                String line1 = dataSnapshot.child("line1").getValue(String.class);
                String line2 = dataSnapshot.child("line2").getValue(String.class);
                String line3 = dataSnapshot.child("line3").getValue(String.class);
                String line4 = dataSnapshot.child("line4").getValue(String.class);
                String date = dataSnapshot.child("date").getValue(String.class);
                String shelterName = dataSnapshot.child("shelterName").getValue(String.class);
                int shelterId = dataSnapshot.child("shelterId").getValue(int.class);
                String shelterMarks = dataSnapshot.child("shelterMark").getValue(String.class);

                MyData.setShelterPosition(shelterId);

                //데이터를 화면에 출력해 준다.
                TextView Title = findViewById(R.id.text_title);
                Title.setText(title);

                TextView ShelterName = findViewById(R.id.text_sheltername);
                ShelterName.setText(shelterName);

                TextView Date = findViewById(R.id.text_date);
                Date.setText(date);

                ImageView ShelterMark = findViewById(R.id.text_sheltermark);
                Picasso.get().load(shelterMarks).into(ShelterMark);

                RadiusImageView  Img1 = (RadiusImageView ) findViewById(R.id.image1_activity2);
                Picasso.get().load(img1).into(Img1);
                ImageView Img2 = (ImageView) findViewById(R.id.image2_activity2);
                Picasso.get().load(img2).into(Img2);
                ImageView Img3 = (ImageView) findViewById(R.id.image3_activity2);
                Picasso.get().load(img3).into(Img3);
                ImageView Img4 = (ImageView) findViewById(R.id.image4_activity2);
                Picasso.get().load(img4).into(Img4);

                TextView Line1 = findViewById(R.id.text1_activity2);
                Line1.setText(line1);
                TextView Line2 = findViewById(R.id.text2_activity2);
                Line2.setText(line2);
                TextView Line3 = findViewById(R.id.text3_activity2);
                Line3.setText(line3);
                TextView Line4 = findViewById(R.id.text4_activity2);
                Line4.setText(line4);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("DetailActivitys", "Failed to read value.", error.toException());
            }
        });

        initViews();
        initFirebaseDatabase();

    }

    public void onLikeClick(View v){
        Toast.makeText(getApplicationContext(), "LIKE", Toast.LENGTH_SHORT).show();
    }

    public void onBackClick(View v){
        super.onBackPressed();
    }

    public void onGoShelter(View v){
        Intent intent = new Intent(DetailActivity.this, ShelterActivity.class);
        startActivity(intent);
    }

    public void onGoDonationClick(View v){
        Intent intent=new Intent(DetailActivity.this,DonationActivity.class);
        startActivity(intent);
    }
    private void initViews() {
        final DataApplication MyData = (DataApplication)getApplication();
        mListView = (ListView) findViewById(R.id.list_message);
        mAdapter = new ChatAdapter(this, 0);
        mListView.setAdapter(mAdapter);

        mEdtMessage = (EditText) findViewById(R.id.edit_message);
        findViewById(R.id.btn_send).setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String message = mEdtMessage.getText().toString();
                if (!TextUtils.isEmpty(message)) {
                    mEdtMessage.setText("");
                    ChatData chatData = new ChatData();
                    chatData.userName = MyData.getUserName();
                    chatData.message = message;
                    mDatabaseReference.push().setValue(chatData);
                }
            }
        });
    }

    private void initFirebaseDatabase() {
        DataApplication MyData = (DataApplication) getApplication();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("message").child(String.valueOf(MyData.getStoryPosition()));
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatData chatData = dataSnapshot.getValue(ChatData.class);
                chatData.firebaseKey = dataSnapshot.getKey();
                mAdapter.add(chatData);
                mListView.smoothScrollToPosition(mAdapter.getCount());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String firebaseKey = dataSnapshot.getKey();
                int count = mAdapter.getCount();
                for (int i = 0; i < count; i++) {
                    if (mAdapter.getItem(i).firebaseKey.equals(firebaseKey)) {
                        mAdapter.remove(mAdapter.getItem(i));
                        break;
                    }
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mDatabaseReference.addChildEventListener(mChildEventListener);
    }

}