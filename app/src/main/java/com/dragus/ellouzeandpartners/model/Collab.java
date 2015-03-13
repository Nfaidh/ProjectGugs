package com.dragus.ellouzeandpartners.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by T-3500 on 24/02/2015.
 */
public class Collab {


    private int id_collab;
    private String last_name;
    private String first_name;
    private String db_date;
    private String db_perid;
    private String db_enterprise;
    private String db_contract_type;
    private String db_contract_site;
    private String db_effective_site;
    private String db_floor;
    private String db_place;
    private String db_org1;
    private String db_org2;
    private String db_org3;



    public Collab() {
    }


    public Collab(int id_collab, String last_name, String first_name, String db_date, String db_perid, String db_enterprise, String db_contract_type, String db_contract_site, String db_effective_site, String db_floor, String db_place, String db_org1, String db_org2, String db_org3) {
        this.id_collab = id_collab;
        this.last_name = last_name;
        this.first_name = first_name;
        this.db_date = db_date;
        this.db_perid = db_perid;
        this.db_enterprise = db_enterprise;
        this.db_contract_type = db_contract_type;
        this.db_contract_site = db_contract_site;
        this.db_effective_site = db_effective_site;
        this.db_floor = db_floor;
        this.db_place = db_place;
        this.db_org1 = db_org1;
        this.db_org2 = db_org2;
        this.db_org3 = db_org3;
    }

    public void setId_collab(int id_collab) {
        this.id_collab = id_collab;
    }

    public int getId_collab() {
        return id_collab;
    }


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDb_date() {
        return db_date;
    }

    public void setDb_date(String db_date) {
        this.db_date = db_date;
    }

    public String getDb_perid() {
        return db_perid;
    }

    public void setDb_perid(String db_perid) {
        this.db_perid = db_perid;
    }

    public String getDb_enterprise() {
        return db_enterprise;
    }

    public void setDb_enterprise(String db_enterprise) {
        this.db_enterprise = db_enterprise;
    }

    public String getDb_contract_type() {
        return db_contract_type;
    }

    public void setDb_contract_type(String db_contract_type) {
        this.db_contract_type = db_contract_type;
    }

    public String getDb_contract_site() {
        return db_contract_site;
    }

    public void setDb_contract_site(String db_contract_site) {
        this.db_contract_site = db_contract_site;
    }

    public String getDb_effective_site() {
        return db_effective_site;
    }

    public void setDb_effective_site(String db_effective_site) {
        this.db_effective_site = db_effective_site;
    }

    public String getDb_place() {
        return db_place;
    }

    public void setDb_place(String db_place) {
        this.db_place = db_place;
    }

    public String getDb_floor() {
        return db_floor;
    }

    public void setDb_floor(String db_floor) {
        this.db_floor = db_floor;
    }

    public String getDb_org1() {
        return db_org1;
    }

    public void setDb_org1(String db_org1) {
        this.db_org1 = db_org1;
    }

    public String getDb_org2() {
        return db_org2;
    }

    public void setDb_org2(String db_org2) {
        this.db_org2 = db_org2;
    }

    public String getDb_org3() {
        return db_org3;
    }

    public void setDb_org3(String db_org3) {
        this.db_org3 = db_org3;
    }


}
