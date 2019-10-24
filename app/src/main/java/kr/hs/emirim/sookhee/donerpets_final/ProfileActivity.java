package kr.hs.emirim.sookhee.donerpets_final;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.ProxyFileDescriptorCallback;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.lang.reflect.Parameter;

public class ProfileActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String user_email = SaveSharedPreference.getEmail(ProfileActivity.this);

        TextView text_username = (TextView)findViewById(R.id.profile_name);
        text_username.setText(SaveSharedPreference.getUserName((ProfileActivity.this)));

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("user").child(user_email);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int level = dataSnapshot.child("level").getValue(int.class);

                ProgressBar progress_level = (ProgressBar)findViewById(R.id.progress_level);
                ImageView profile_img = (ImageView) findViewById(R.id.profile_img);
                TextView levelname = (TextView) findViewById(R.id.profile_levelname);

                TextView text_level = (TextView)findViewById(R.id.profile_level);
                text_level.setText(String.valueOf(level) + "pt");

                if(level >= 0 && level < 100){
                    progress_level.setProgress(level);
                    profile_img.setImageResource(R.drawable.donor_level_01);
                    levelname.setText("오리지널 도너츠");
                }
                else if(level >= 100 && level < 200){
                    progress_level.setProgress(level-100);
                    profile_img.setImageResource(R.drawable.donor_level_02);
                    levelname.setText("딸기시럽 도너츠");
                }
                else if(level >= 200 && level < 300){
                    progress_level.setProgress(level-200);
                    profile_img.setImageResource(R.drawable.donor_level_03);
                    levelname.setText("레인보우 도너츠");
                }
                else if(level >= 300 && level < 400){
                    progress_level.setProgress(level-300);
                    profile_img.setImageResource(R.drawable.donor_level_04);
                    levelname.setText("킹 도너츠");
                }
                else{
                    progress_level.setProgress(level-400);
                    profile_img.setImageResource(R.drawable.donor_level_05);
                    levelname.setText("레인보우 킹 도너츠");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });


    }

    public void onBackClick(View v){
        super.onBackPressed();
    }

    public void onSettingClick(View v){

        Intent intent = new Intent(ProfileActivity.this, SettingActivity.class);
        startActivity(intent);
        finish();
    }
}