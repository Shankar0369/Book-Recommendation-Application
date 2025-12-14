package com.example.bookapp.network; 
import com.example.bookapp.network.response.BookResponse; 
import retrofit2.Call; 
import retrofit2.http.GET; 
import retrofit2.http.Query; 
public interface BookApi { 
@GET("volumes") 
Call<BookResponse> searchBooks(@Query("q") String query); 
} 
