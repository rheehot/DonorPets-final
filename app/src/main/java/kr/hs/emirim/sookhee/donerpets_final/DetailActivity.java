package kr.hs.emirim.sookhee.donerpets_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initViews();
        initFirebaseDatabase();
        initValues();

        Intent intent = getIntent();
        ExampleItem exampleItem = intent.getParcelableExtra("Example Item");

        int imageRes = exampleItem.getImageResource();
        String line1 = exampleItem.getText1();
        String line2 = exampleItem.getText2();

        ImageView imageView = findViewById(R.id.image1_activity2);
        imageView.setImageResource(imageRes);

        TextView textView1 = findViewById(R.id.text1_activity2);
        textView1.setText(line1);

        TextView textView2 = findViewById(R.id.text2_activity2);
        textView2.setText(line2);
    }

    public void onBackClick(View v){
        super.onBackPressed();
    }

    private void initViews() {
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
                    chatData.userName = userName;
                    chatData.message = message;
                    chatData.time = System.currentTimeMillis();
                    mDatabaseReference.push().setValue(chatData);
                }
            }
        });
    }

    private void initFirebaseDatabase() {
        DataApplication MyData = (DataApplication) getApplication();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("message").child(String.valueOf(MyData.getPosition()));
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

    private void initValues() {
        userName = "Guest" + new Random().nextInt(5000);
    }

}