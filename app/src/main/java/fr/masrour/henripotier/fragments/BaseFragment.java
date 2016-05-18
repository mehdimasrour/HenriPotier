package fr.masrour.henripotier.fragments;

import android.app.Fragment;
import android.support.annotation.UiThread;
import android.widget.Toast;

/**
 * Created by mehdimasrour on 18/05/16.
 */
public class BaseFragment extends Fragment {

    @UiThread
    public void showToast(String message){
        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
    }

}
