package com.example.bookapp; 
import android.view.LayoutInflater; 
import android.view.View; 
import android.view.ViewGroup; 
import android.widget.ImageView; 
import android.widget.TextView; 
import androidx.annotation.NonNull; 
import androidx.recyclerview.widget.RecyclerView; 
import com.bumptech.glide.Glide; 
import com.example.bookapp.models.book; 
import com.example.implicit_intent25.R; 
import java.util.List; 
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.VH> { 
public interface OnItemClickListener { void onItemClick(book book); } 
private List<book> books; 
private OnItemClickListener listener; 
public BookAdapter(List<book> books, OnItemClickListener listener) { 
this.books = books; 
this.listener = listener; 
} 
public void updateData(List<book> newBooks) { 
this.books = newBooks; 
notifyDataSetChanged(); 
} 
@NonNull 
@Override 
public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { 
View v = 
LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, 
false); 
return new VH(v); 
} 
@Override 
public void onBindViewHolder(@NonNull VH holder, int position) { 
book b = books.get(position); 
holder.tvTitle.setText(b.getTitle()); 
holder.tvAuthors.setText(b.getAuthors()); 
if (b.getThumbnail() != null && !b.getThumbnail().isEmpty()) { 
Glide.with(holder.itemView.getContext()).load(b.getThumbnail()).into(holder.imgT 
humb); 
} else { 
holder.imgThumb.setImageResource(android.R.drawable.ic_menu_report_image); 
} 
holder.itemView.setOnClickListener(v-> listener.onItemClick(b)); 
} 
@Override 
public int getItemCount() { return books == null ? 0 : books.size(); } 
static class VH extends RecyclerView.ViewHolder { 
ImageView imgThumb; 
TextView tvTitle, tvAuthors; 
VH(@NonNull View itemView) { 
super(itemView); 
imgThumb = itemView.findViewById(R.id.imgThumb); 
tvTitle = itemView.findViewById(R.id.tvTitle); 
tvAuthors = itemView.findViewById(R.id.tvAuthors); 
} 
} 
}
