package eg.gov.iti.jets.foodie.model;

import java.io.Serializable;

public class Country implements Serializable {
    private String strArea;

    public Country(String strArea) {
        this.strArea = strArea;
    }

    public Country() {
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }
}
