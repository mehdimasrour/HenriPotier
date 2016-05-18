package fr.masrour.henripotier.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import fr.masrour.henripotier.R;
import fr.masrour.henripotier.fragments.BookListFragment_;

@EActivity(R.layout.activity_book_list)
public class BookListActivity extends AppCompatActivity {


    @AfterViews
    void initActivity(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frameListContent, BookListFragment_.builder().build());
        fragmentTransaction.commit();
    }


    @Click
    void cartButton(){
        startActivity(new Intent(this,CartActivity_.class));
    }


}
