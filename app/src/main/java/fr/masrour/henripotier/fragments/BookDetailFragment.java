package fr.masrour.henripotier.fragments;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import fr.masrour.henripotier.HenriPotierApp;
import fr.masrour.henripotier.R;
import fr.masrour.henripotier.activities.CartActivity_;
import fr.masrour.henripotier.models.Book;
import fr.masrour.henripotier.models.Offer;
import fr.masrour.henripotier.models.Offers;
import fr.masrour.henripotier.network.HenriPotierService;
import fr.masrour.henripotier.network.HenriPotierServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mehdimasrour on 17/05/16.
 */
@EFragment(R.layout.fragment_bookdetail)
public class BookDetailFragment extends BaseFragment {

    @App
    HenriPotierApp henriPotierApp;

    @FragmentArg
    Book book;

    @ViewById
    TextView bookDetailISBN, bookDetailTitle, bookDetailPrice;

    @ViewById
    ImageView bookDetailCover;

    @ViewById
    Button bookDetailButton;

    HenriPotierServices henriPotierServices;

    @AfterViews
    void init(){
        henriPotierServices = HenriPotierService.getInstance(getActivity());
        bookDetailTitle.setText(book.getTitle());
        bookDetailISBN.setText(getString(R.string.isbn_details,book.getIsbn()));
        bookDetailPrice.setText(getString(R.string.price_detail,book.getPrice()));

        bookDetailButton.setText(getString(R.string.cart_add));

        Picasso.with(getActivity())
                .load(book.getCover())
                .into(bookDetailCover);

    }

    @Click
    void bookDetailButton(){
        henriPotierApp.addToCart(book);
        showToast(getString(R.string.book_added,book.getTitle()));
        getOffers();
    }

    @Background
    void getOffers(){
        henriPotierServices.getOffers(henriPotierApp.getCart().getISBNList())
                .enqueue(new Callback<Offers>() {
                    @Override
                    public void onResponse(Call<Offers> call, Response<Offers> response) {
                        Offer bestOffer = Offer.getBestOffer(response.body().getOffers(),henriPotierApp.getCart().getTotal());
                        henriPotierApp.setOffer(bestOffer);
                        startActivity(new Intent(getActivity(),CartActivity_.class));
                    }

                    @Override
                    public void onFailure(Call<Offers> call, Throwable t) {
                        showToast(getString(R.string.error_add_book));
                    }
                });
    }
}
