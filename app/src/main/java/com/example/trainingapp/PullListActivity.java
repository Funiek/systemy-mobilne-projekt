package com.example.trainingapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.trainingapp.db.AppDatabase;
import com.example.trainingapp.db.Pull;

import java.util.List;

public class PullListActivity extends AppCompatActivity {
    private PullListAdapter pullListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_list);

        initRecyclerView();

        loadPullList();
    }

    public void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewPull);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        pullListAdapter = new PullListAdapter(this);
        recyclerView.setAdapter(pullListAdapter);
    }

    private void loadPullList() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<Pull> userList = db.pullDao().getAllPull();
        pullListAdapter.setPullList(userList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 100) {
            loadPullList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}