package com.dragus.ellouzeandpartners.model;

/**
 * Created by T-3500 on 23/02/2015.
 */
public class Infos_Plan {


    // int_min = –2147483648 and int_max 2147483648
    private String plan_name;
    private int placed_collab;
    private int occupied_posts;
    private int passage_posts;
    private int power_posts;
    private int tech_posts;
    private int freed_posts;
    private int total_posts;


    public Infos_Plan() {
    }


    public Infos_Plan(String plan_name, int placed_collab, int occupied_posts, int passage_posts, int power_posts,int tech_posts, int freed_posts, int total_posts) {
        this.plan_name = plan_name;
        this.placed_collab = placed_collab;
        this.occupied_posts = occupied_posts;
        this.passage_posts = passage_posts;
        this.power_posts = power_posts;
        this.tech_posts = tech_posts;
        this.freed_posts = freed_posts;
        this.total_posts = total_posts;
    }



    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public int getPlaced_collab() {
        return placed_collab;
    }

    public void setPlaced_collab(int placed_collab) {
        this.placed_collab = placed_collab;
    }

    public int getOccupied_posts() {
        return occupied_posts;
    }

    public void setOccupied_posts(int occupied_posts) {
        this.occupied_posts = occupied_posts;
    }

    public int getPassage_posts() {
        return passage_posts;
    }

    public void setPassage_posts(int passage_posts) {
        this.passage_posts = passage_posts;
    }

    public int getPower_posts() {
        return power_posts;
    }

    public void setPower_posts(int power_posts) {
        this.power_posts = power_posts;
    }

    public int getFreed_posts() {
        return freed_posts;
    }

    public void setFreed_posts(int freed_posts) {
        this.freed_posts = freed_posts;
    }

    public int getTotal_posts() {
        return total_posts;
    }

    public void setTotal_posts(int total_posts) {
        this.total_posts = total_posts;
    }

    public int getTech_posts() {
        return tech_posts;
    }

    public void setTech_posts(int tech_posts) {
        this.tech_posts = tech_posts;
    }
}
