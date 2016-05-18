package fr.masrour.henripotier.models;

import java.util.List;

/**
 * Created by mehdimasrour on 17/05/16.
 */
public class Offers {

    List<Offer> offers;

    public Offers() {
    }

    public Offers(List<Offer> offers) {

        this.offers = offers;
    }

    public List<Offer> getOffers() {

        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public String toString() {
        return "Offers{" +
                "offers=" + offers +
                '}';
    }


}
