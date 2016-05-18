package fr.masrour.henripotier.network;

import java.util.List;

import fr.masrour.henripotier.models.Book;
import fr.masrour.henripotier.models.Offer;
import fr.masrour.henripotier.models.Offers;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mehdimasrour on 17/05/16.
 */
public interface HenriPotierServices {

    @GET("/books")
    Call<List<Book>> getBooks();

    @GET("/books/{isbnList}/commercialOffers")
    Call<Offers> getOffers(@Path("isbnList") String isbnList);
}
