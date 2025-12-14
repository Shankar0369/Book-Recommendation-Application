package com.example.bookapp; 
import android.content.Intent; 
import android.os.Bundle; 
import android.text.TextUtils; 
import android.view.View; 
import android.widget.Button; 
import android.widget.EditText; 
import android.widget.ProgressBar; 
import android.widget.Toast; 
import androidx.appcompat.app.AppCompatActivity; 
import androidx.recyclerview.widget.LinearLayoutManager; 
import androidx.recyclerview.widget.RecyclerView; 
import com.example.bookapp.network.ApiClient; 
import com.example.bookapp.network.BookApi; 
import com.example.bookapp.network.response.BookResponse; 
import com.example.bookapp.models.book; 
import com.example.implicit_intent25.R; 
import java.util.ArrayList; 
import java.util.List; 
import retrofit2.Call; 
import retrofit2.Callback; 
import retrofit2.Response; 
public class MainActivity extends AppCompatActivity implements 
BookAdapter.OnItemClickListener { 
private EditText searchBox; 
private Button searchBtn; 
private RecyclerView recyclerView; 
private BookAdapter adapter; 
private ProgressBar progressBar; 
@Override 
protected void onCreate(Bundle savedInstanceState) { 
super.onCreate(savedInstanceState); 
setContentView(R.layout.activity_main); 
searchBox = findViewById(R.id.searchBox); 
searchBtn = findViewById(R.id.searchButton); 
recyclerView = findViewById(R.id.recyclerViewBooks); 
progressBar = findViewById(R.id.progressBar); 
adapter = new BookAdapter(new ArrayList<>(), this); 
recyclerView.setLayoutManager(new LinearLayoutManager(this)); 
recyclerView.setAdapter(adapter); 
searchBtn.setOnClickListener(v-> { 
String query = searchBox.getText().toString().trim(); 
if (TextUtils.isEmpty(query)) { 
Toast.makeText(this, "Enter a search term", 
Toast.LENGTH_SHORT).show(); 
} 
return; 
fetchBooks(query); 
}); 
// Optional: default search 
fetchBooks("android development"); 
} 
private void fetchBooks(String query) { 
progressBar.setVisibility(View.VISIBLE); 
BookApi api = ApiClient.getClient().create(BookApi.class); 
Call<BookResponse> call = api.searchBooks(query); 
call.enqueue(new Callback<BookResponse>() { 
@Override 
public void onResponse(Call<BookResponse> call, 
Response<BookResponse> response) { 
progressBar.setVisibility(View.GONE); 
if (response.isSuccessful() && response.body() != null) { 
List<book> list = response.body().toBookList(); 
adapter.updateData(list); 
} else { 
Toast.makeText(MainActivity.this, "No results", 
Toast.LENGTH_SHORT).show(); 
} 
} 
@Override 
public void onFailure(Call<BookResponse> call, Throwable t) { 
progressBar.setVisibility(View.GONE); 
Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), 
Toast.LENGTH_SHORT).show(); 
} 
}); 
} 
@Override 
public void onItemClick(book book) { 
Intent i = new Intent(this, BookDetailActivity.class); 
i.putExtra("title", book.getTitle()); 
i.putExtra("authors", book.getAuthors()); 
i.putExtra("desc", book.getDescription()); 
i.putExtra("thumb", book.getThumbnail()); 
startActivity(i); 
} 
}
