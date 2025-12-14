package com.example.bookapp.network.response; 
import com.example.bookapp.models.book; 
import com.google.gson.annotations.SerializedName; 
import java.util.ArrayList; 
import java.util.List; 
public class BookResponse { 
@SerializedName("items") 
public List<Item> items; 
public static class Item { 
@SerializedName("volumeInfo") 
public VolumeInfo volumeInfo; 
} 
public static class VolumeInfo { 
public String title; 
public List<String> authors; 
public String description; 
@SerializedName("imageLinks") 
public ImageLinks imageLinks; 
} 
public static class ImageLinks { 
public String thumbnail; 
} 
// Convert to simpler domain list 
public List<book> toBookList() { 
List<book> out = new ArrayList<>(); 
if (items == null) return out; 
for (Item it : items) { 
if (it == null || it.volumeInfo == null) continue; 
VolumeInfo v = it.volumeInfo; 
String authors = (v.authors == null || v.authors.isEmpty()) ? 
"Unknown" : android.text.TextUtils.join(", ", v.authors); 
String thumb = (v.imageLinks != null) ? v.imageLinks.thumbnail : 
null; 
out.add(new book(v.title, authors, thumb, v.description)); 
} 
return out; 
} 
} 
