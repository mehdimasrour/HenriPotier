package fr.masrour.henripotier.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import fr.masrour.henripotier.R;
import fr.masrour.henripotier.fragments.CartFragment_;

/**
 * Created by mehdimasrour on 17/05/16.
 */
@EActivity(R.layout.activity_cart)
public class CartActivity extends AppCompatActivity {

    @AfterViews
    void initActivity(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frameCartContent, CartFragment_.builder().build());
        fragmentTransaction.commit();
    }

}
