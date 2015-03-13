package com.dragus.ellouzeandpartners.model;

/**
 * Created by T-3500 on 23/02/2015.
 */
public class Total {

    private int tot_collab;
    private int tot_occupied;
    private int tot_passg;
    private int tot_power;
    private int tot_tech;
    private int tot_freed;
    private int tot_tot_posts;

    public Total() {
    }


    public Total(int tot_collab, int tot_occupied, int tot_passg, int tot_tot_posts, int tot_power, int tot_tech, int tot_freed) {
        this.tot_collab = tot_collab;
        this.tot_occupied = tot_occupied;
        this.tot_passg = tot_passg;
        this.tot_tot_posts = tot_tot_posts;
        this.tot_power = tot_power;
        this.tot_tech = tot_tech;
        this.tot_freed = tot_freed;
    }

    public int getTot_collab() {
        return tot_collab;
    }

    public void setTot_collab(int tot_collab) {
        this.tot_collab = tot_collab;
    }

    public int getTot_occupied() {
        return tot_occupied;
    }

    public void setTot_occupied(int tot_occupied) {
        this.tot_occupied = tot_occupied;
    }

    public int getTot_passg() {
        return tot_passg;
    }

    public void setTot_passg(int tot_passg) {
        this.tot_passg = tot_passg;
    }

    public int getTot_power() {
        return tot_power;
    }

    public void setTot_power(int tot_power) {
        this.tot_power = tot_power;
    }

    public int getTot_tech() {
        return tot_tech;
    }

    public void setTot_tech(int tot_tech) {
        this.tot_tech = tot_tech;
    }

    public int getTot_freed() {
        return tot_freed;
    }

    public void setTot_freed(int tot_freed) {
        this.tot_freed = tot_freed;
    }

    public int getTot_tot_posts() {
        return tot_tot_posts;
    }

    public void setTot_tot_posts(int tot_tot_posts) {
        this.tot_tot_posts = tot_tot_posts;
    }
}
