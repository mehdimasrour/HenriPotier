package fr.masrour.henripotier;

import android.app.Application;

import com.google.gson.Gson;

import org.androidannotations.annotations.EApplication;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.math.BigDecimal;

import fr.masrour.henripotier.models.Book;
import fr.masrour.henripotier.models.Cart;
import fr.masrour.henripotier.models.Offer;

/**
 * Created by mehdimasrour on 18/05/16.
 */
@EApplication
public class HenriPotierApp extends Application{

    @Pref
    HenriPotierPrefs_ henriPotierPrefs;

    private final Gson gson = new Gson();

    private Cart cart;
    private Offer offer;

    public void onCreate() {
        super.onCreate();
        init();
    }

    // Book list warning
    @SuppressWarnings("unchecked")
    private void init(){
        cart = new Cart();
        offer = new Offer();
        // Retrieve existing cart and offer from sharedPrefs
        if (henriPotierPrefs.cart().getOr("").compareTo("")!=0){
            cart = gson.fromJson(henriPotierPrefs.cart().get(),Cart.class);
        }
        if (henriPotierPrefs.offerType().getOr("").compareTo("")!=0){
            offer = new Offer(henriPotierPrefs.offerType().get(),new BigDecimal(henriPotierPrefs.offerSlice().getOr("0")), new BigDecimal(henriPotierPrefs.offerValue().get()), new BigDecimal(henriPotierPrefs.offerCalculated().get()));
        }
    }

    public Cart getCart(){
        return cart;
    }

    public void addToCart(Book book){
        cart.addBook(book);
        // Save cart in sharedPrefs after each add
        henriPotierPrefs.cart().put(cart.toJSON());
    }

    public void removeFromCart(Book book){
        cart.removeBook(book);
        // Save cart in sharedPrefs after each action
        henriPotierPrefs.cart().put(cart.toJSON());
    }

    public void setOffer(Offer offer){
        // Set "locally" and in sharedPrefs
        this.offer = offer;
        henriPotierPrefs.offerType().put(offer.getType());
        if (offer.getSliceValue() != null) henriPotierPrefs.offerSlice().put(offer.getSliceValue().toString());
        henriPotierPrefs.offerValue().put(offer.getValue().toString());
        henriPotierPrefs.offerCalculated().put(offer.getCalculatedValue().toString());
    }

    public Offer getOffer(){
        return offer;
    }


}
