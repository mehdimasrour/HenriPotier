package fr.masrour.henripotier.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import fr.masrour.henripotier.R;
import fr.masrour.henripotier.fragments.BookDetailFragment_;
import fr.masrour.henripotier.models.Book;

/**
 * Created by mehdimasrour on 17/05/16.
 */
@EActivity(R.layout.activity_book_detail)
public class BookDetailActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Extra
    Book book;


    @AfterViews
    void init(){

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameDetailContent, BookDetailFragment_.builder().book(book).build());
        fragmentTransaction.commit();
    }

}
