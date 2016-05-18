package fr.masrour.henripotier.fragments;

import android.content.Intent;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.parceler.Parcels;

import java.util.List;

import fr.masrour.henripotier.R;
import fr.masrour.henripotier.activities.BookDetailActivity_;
import fr.masrour.henripotier.models.Book;
import fr.masrour.henripotier.network.HenriPotierService;
import fr.masrour.henripotier.network.HenriPotierServices;
import fr.masrour.henripotier.views.adapters.BookAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mehdimasrour on 17/05/16.
 */
@EFragment(R.layout.fragment_booklist)
public class BookListFragment extends BaseFragment {

    @ViewById
    ListView bookListView;

    HenriPotierServices henriPotierServices;


    @UiThread
    void updateBookList(List<Book> books){
        if (bookListView != null) bookListView.setAdapter(new BookAdapter(getActivity(),books));
    }

    @Background
    void getBookList(){

        henriPotierServices.getBooks().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                updateBookList(response.body());
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                showToast(getString(R.string.error_get_books));
            }
        });
    }

    @AfterViews
    void initFragment(){
        henriPotierServices = HenriPotierService.getInstance(getActivity());
        getBookList();
    }

    @ItemClick
    void bookListViewItemClicked(Book book){
        Intent myIntent = new Intent(getActivity(), BookDetailActivity_.class);
        myIntent.putExtra("book", Parcels.wrap(book));
        startActivity(myIntent);
    }

}
