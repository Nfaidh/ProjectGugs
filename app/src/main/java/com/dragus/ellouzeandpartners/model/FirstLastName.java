package com.dragus.ellouzeandpartners.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by T-3500 on 24/02/2015.
 */
public class FirstLastName {


    private String first_last_name;



    public FirstLastName() {
    }

    public FirstLastName(String first_last_name) {
        this.first_last_name = first_last_name;
    }

    public String getFirst_last_name() {
        return first_last_name;
    }

    public void setFirst_last_name(String first_last_name) {
        this.first_last_name = first_last_name;
    }

    @Override
    public String toString() {
        return first_last_name;
    }


}
