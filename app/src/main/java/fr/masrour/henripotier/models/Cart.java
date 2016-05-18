package fr.masrour.henripotier.models;

import com.google.gson.Gson;

import org.parceler.Parcel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mehdimasrour on 17/05/16.
 */
@Parcel
public class Cart {

    private List<Book> books;
    private static final Gson gson = new Gson();

    public String toJSON(){
        return gson.toJson(this,Cart.class);
    }

    public String getISBNList(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Book b : books){
            stringBuilder.append(b.getIsbn()).append(",");
        }

        // We remove the last ","
        return stringBuilder.deleteCharAt(stringBuilder.length()-1).toString();
    }

    public void addBook(Book book){
        if (!books.contains(book)){
            books.add(book);
        }
    }

    public void removeBook(Book book){
        if(books.contains(book)){
            books.remove(book);
        }
    }

    public BigDecimal getTotal(){
        BigDecimal total = BigDecimal.ZERO;
        for (Book b : books){
            total = total.add(b.getPrice());
        }
        return total;
    }

    public Cart(List<Book> books) {
        this();
        this.books = books;
    }

    public Cart() {
        books = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "books=" + books +
                '}';
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
