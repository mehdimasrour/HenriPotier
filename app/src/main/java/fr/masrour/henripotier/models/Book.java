package fr.masrour.henripotier.models;

import com.google.gson.Gson;

import org.parceler.Parcel;

import java.math.BigDecimal;

/**
 * Created by mehdimasrour on 17/05/16.
 */
@Parcel
public class Book{

    private String isbn, title, cover;
    private BigDecimal price;
    private static final Gson gson = new Gson();

    public Book() {
    }

    public Book(String isbn, String title, String cover, BigDecimal price) {

        this.isbn = isbn;
        this.title = title;
        this.cover = cover;
        this.price = price;
    }

    public String getIsbn() {

        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", price=" + price +
                '}';
    }
}
