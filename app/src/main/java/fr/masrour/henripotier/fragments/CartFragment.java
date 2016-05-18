package fr.masrour.henripotier.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.math.BigDecimal;
import java.util.List;

import fr.masrour.henripotier.HenriPotierApp;
import fr.masrour.henripotier.R;
import fr.masrour.henripotier.activities.CartActivity_;
import fr.masrour.henripotier.models.Book;
import fr.masrour.henripotier.models.Cart;
import fr.masrour.henripotier.models.Offer;
import fr.masrour.henripotier.models.Offers;
import fr.masrour.henripotier.network.HenriPotierService;
import fr.masrour.henripotier.network.HenriPotierServices;
import fr.masrour.henripotier.views.adapters.BookAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mehdimasrour on 17/05/16.
 */
@EFragment(R.layout.fragment_cart)
public class CartFragment extends BaseFragment {

    @App
    HenriPotierApp henriPotierApp;

    @ViewById
    ListView listViewCart;

    @ViewById
    TextView textViewSubTotal, textViewOffer, textViewTotal;

    HenriPotierServices henriPotierServices;

    @AfterViews
    void initFragment(){
        henriPotierServices = HenriPotierService.getInstance(getActivity());
        Cart cart = henriPotierApp.getCart();
        List<Book> books = cart.getBooks();
        Offer offer = henriPotierApp.getOffer();

        listViewCart.setAdapter(new BookAdapter(getActivity(), books));
        textViewSubTotal.setText(getString(R.string.sub_total,cart.getTotal()));

        String promoText = "";
        if (offer.getType() != null && books.size() > 0){
            switch (offer.getType()){
                case Offer.TYPE_PERCENTAGE:
                    promoText = offer.getValue()+"% ("+offer.getCalculatedValue()+"€)";
                    break;
                case Offer.TYPE_SLICE:
                    promoText = "-"+offer.getCalculatedValue()+"€ ("+offer.getValue()+"/"+offer.getSliceValue()+")";
                    break;
                default:
                    promoText = "-"+offer.getCalculatedValue()+"€";
                    break;
            }
            textViewOffer.setText(getString(R.string.offer_applied, promoText));
            BigDecimal total = cart.getTotal().subtract(offer.getCalculatedValue());
            textViewTotal.setText(getString(R.string.total_price, total));
        } else {
            textViewSubTotal.setVisibility(View.GONE);
            textViewOffer.setVisibility(View.GONE);
            textViewTotal.setText(getString(R.string.empty_cart));
        }


    }

    // Click on a book in cart to remove it
    @ItemClick
    void listViewCartItemClicked(Book book){
        henriPotierApp.removeFromCart(book);
        if (henriPotierApp.getCart().getBooks().size()>0){
            getOffers();
        } else {
            textViewSubTotal.setVisibility(View.GONE);
            textViewOffer.setVisibility(View.GONE);
            textViewTotal.setText(getString(R.string.empty_cart));
        }
    }

    @Background
    void getOffers(){
        henriPotierServices.getOffers(henriPotierApp.getCart().getISBNList())
                .enqueue(new Callback<Offers>() {
                    @Override
                    public void onResponse(Call<Offers> call, Response<Offers> response) {
                        Offer bestOffer = Offer.getBestOffer(response.body().getOffers(),henriPotierApp.getCart().getTotal());
                        henriPotierApp.setOffer(bestOffer);
                        initFragment();
                    }

                    @Override
                    public void onFailure(Call<Offers> call, Throwable t) {
                        showToast(getString(R.string.error_add_book));
                    }
                });
    }

}
