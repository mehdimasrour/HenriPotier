package fr.masrour.henripotier;

import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by mehdimasrour on 18/05/16.
 */
@SharedPref
public interface HenriPotierPrefs {

    @DefaultString("")
    String cart();


    @DefaultString("")
    String offerType();

    @DefaultString("")
    String offerSlice();

    @DefaultString("")
    String offerValue();

    @DefaultString("")
    String offerCalculated();
}
