package fr.masrour.henripotier.models;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by mehdimasrour on 17/05/16.
 */
public class Offer{

    public static final String TYPE_PERCENTAGE = "percentage";
    public static final String TYPE_MINUS = "minus";
    public static final String TYPE_SLICE = "slice";

    private String type;
    private BigDecimal sliceValue, value, calculatedValue;

    public Offer() {
    }

    public Offer(String type, BigDecimal sliceValue, BigDecimal value, BigDecimal calculatedValue) {

        this.type = type;
        this.sliceValue = sliceValue;
        this.value = value;
        this.calculatedValue = calculatedValue;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getSliceValue() {
        return sliceValue;
    }

    public void setSliceValue(BigDecimal sliceValue) {
        this.sliceValue = sliceValue;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public static Offer getBestOffer(List<Offer> offers, BigDecimal total){
        BigDecimal maxDiscount = BigDecimal.ZERO;
        BigDecimal discount = BigDecimal.ZERO;
        Offer bestOffer = null;
        for(Offer o : offers) {
            switch (o.getType()) {
                case Offer.TYPE_PERCENTAGE:
                    discount = total.multiply(o.getValue()).divide(new BigDecimal(100), BigDecimal.ROUND_CEILING).setScale(2,BigDecimal.ROUND_CEILING);
                    break;
                case Offer.TYPE_MINUS:
                    discount = o.getValue();
                    break;
                case Offer.TYPE_SLICE:
                    discount = total.divide(o.getSliceValue(),BigDecimal.ROUND_DOWN).multiply(o.getValue());
                    break;
            }
            if (discount.compareTo(maxDiscount) > 0) {
                bestOffer = o;
                maxDiscount = discount;
                bestOffer.calculatedValue = discount;
            }
        }
        return bestOffer;
    }

    public BigDecimal getCalculatedValue() {
        return calculatedValue;
    }

    public void setCalculatedValue(BigDecimal calculatedValue) {
        this.calculatedValue = calculatedValue;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "type='" + type + '\'' +
                ", sliceValue=" + sliceValue +
                ", value=" + value +
                '}';
    }
}
