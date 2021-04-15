package com.example.newsadder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newsadder.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText node;
    EditText header;
    EditText image;
    EditText link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        node = binding.node;
        header = binding.header;
        image = binding.image;
        link = binding.link;

    }

    public void processed(View view) {
        News news = new News(header.getText().toString().trim(), image.getText().toString().trim(), link.getText().toString().trim());
        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference dr = fb.getReference("news");
        dr.child(node.getText().toString().trim()).setValue(news);
        Toast.makeText(MainActivity.this, "Added to Database", Toast.LENGTH_LONG).show();

        node.setText("");
        header.setText("");
        image.setText("");
        link.setText("");
    }
}