package fr.masrour.henripotier.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import fr.masrour.henripotier.R;
import fr.masrour.henripotier.models.Book;

/**
 * Created by mehdimasrour on 17/05/16.
 */
public class BookAdapter extends BaseAdapter {

    List<Book> books;
    Context context;

    public BookAdapter(Context context, List<Book> books){
        this.context = context;
        this.books = books;
    }


    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return books.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final BookHolder holder;
        if (convertView == null){
            holder = new BookHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.book_item, null);
            holder.bookItemCover = (ImageView)convertView.findViewById(R.id.bookItemCover);
            holder.bookItemTitle = (TextView)convertView.findViewById(R.id.bookItemTitle);
            holder.bookItemPrice = (TextView)convertView.findViewById(R.id.bookItemPrice);
            convertView.setTag(holder);
        } else {
            holder = (BookHolder) convertView.getTag();
        }

        final Book book = books.get(position);

        Picasso.with(context)
                .load(book.getCover())
                .into(holder.bookItemCover);

        holder.bookItemTitle.setText(book.getTitle());

        holder.bookItemPrice.setText(context.getString(R.string.price_list,book.getPrice()));


        return convertView;
    }


    private class BookHolder{
        private ImageView bookItemCover;
        private TextView bookItemTitle, bookItemPrice;
    }
}
