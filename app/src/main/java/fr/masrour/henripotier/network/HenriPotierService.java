package fr.masrour.henripotier.network;

import android.content.Context;

import fr.masrour.henripotier.BuildConfig;
import fr.masrour.henripotier.R;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mehdimasrour on 18/05/16.
 */
public class HenriPotierService {

    private static HenriPotierServices instance = null;

    public final static HenriPotierServices getInstance(Context context){
        if (instance == null){

            instance = new Retrofit.Builder()
                    .baseUrl(context.getString(R.string.baseWSURL))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(HenriPotierServices.class);
        }

        return instance;
    }

}
