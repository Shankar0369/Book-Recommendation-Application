package com.example.bookapp; 
import android.os.Bundle; 
import android.widget.ImageView; 
import android.widget.TextView; 
import androidx.appcompat.app.AppCompatActivity; 
import com.bumptech.glide.Glide; 
import com.example.implicit_intent25.R; 
public class BookDetailActivity extends AppCompatActivity { 
@Override 
protected void onCreate(Bundle savedInstanceState) { 
super.onCreate(savedInstanceState); 
setContentView(R.layout.activity_book_detail); 
ImageView img = findViewById(R.id.detailImage); 
TextView tvTitle = findViewById(R.id.detailTitle); 
TextView tvAuthors = findViewById(R.id.detailAuthors); 
TextView tvDesc = findViewById(R.id.detailDesc); 
String title = getIntent().getStringExtra("title"); 
String authors = getIntent().getStringExtra("authors"); 
String desc = getIntent().getStringExtra("desc"); 
String thumb = getIntent().getStringExtra("thumb"); 
tvTitle.setText(title); 
tvAuthors.setText(authors); 
tvDesc.setText(desc == null ? "No description" : desc); 
if (thumb != null && !thumb.isEmpty()) 
Glide.with(this).load(thumb).into(img); 
} 
} 
