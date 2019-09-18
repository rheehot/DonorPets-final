package kr.hs.emirim.sookhee.donerpets_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createExampleList();
        buildRecyclerView();

    }

    public void onProfileClick(View v){
        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.img01, "8월 6일 보호소 한 바퀴", "일주일 전"));
        mExampleList.add(new ExampleItem(R.drawable.img02, "이불과 헌 옷 보내주시면 고맙겠습니다", "4일 전"));
        mExampleList.add(new ExampleItem(R.drawable.img03, "보호소 야옹이 친구들이에용~♡", "2일 전"));
        mExampleList.add(new ExampleItem(R.drawable.img04, "남양주 유기견 보호소 친구들을 소개합니다", "30분 전"));
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                DataApplication MyData = (DataApplication)getApplication();
                MyData.setStoryPosition(position);
                MyData.setShelterPosition(position);

                Intent intent=new Intent(MainActivity.this,DetailActivity.class);
                startActivity(intent);
            }
        });
    }
}