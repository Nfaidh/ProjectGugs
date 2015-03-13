package com.dragus.ellouzeandpartners.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dragus.ellouzeandpartners.dragus.R;
import com.dragus.ellouzeandpartners.model.Infos_Plan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by T-3500 on 20/02/2015.
 */
public class CustomListPlansAdapter extends BaseAdapter {


    public static final String TITLE_SECT = "Secteur : ";
    private Activity activity;
    private LayoutInflater inflater;
    private List<Infos_Plan> infosItems = new ArrayList<>();

    public CustomListPlansAdapter(Activity activity, List<Infos_Plan> infosItems) {
        this.activity = activity;
        this.infosItems = infosItems;


    }

    @Override
    public int getCount() {
        return infosItems.size();
    }

    @Override
    public Object getItem(int location) {
        return infosItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_single_plan, null);


        TextView plan_name = (TextView) convertView.findViewById(R.id.plan_name);
        TextView placed_collab = (TextView) convertView.findViewById(R.id.id_collab);
        TextView occupied_posts = (TextView) convertView.findViewById(R.id.id_post_occup);
        TextView passage_posts = (TextView) convertView.findViewById(R.id.id_post_passg);
        TextView power_posts = (TextView) convertView.findViewById(R.id.id_post_power);
        TextView tech_posts = (TextView) convertView.findViewById(R.id.id_post_tech);
        TextView freed_posts = (TextView) convertView.findViewById(R.id.id_post_freed);
        TextView total_posts = (TextView) convertView.findViewById(R.id.id_tot_posts);


        // getting movie data for the row
        Infos_Plan p = infosItems.get(position);


        // name of plan
        plan_name.setText(TITLE_SECT + p.getPlan_name());


        // number of collab placed
        placed_collab.setText(p.getPlaced_collab()+"");

        // number of occupied posts
        occupied_posts.setText(p.getOccupied_posts()+"");

        //number of passage posts
        passage_posts.setText(p.getPassage_posts()+"");

        //number of power posts
        power_posts.setText(p.getPower_posts()+"");

        //number of technique posts
        tech_posts.setText(p.getTech_posts()+"");

        //number of freed posts
        freed_posts.setText(p.getFreed_posts()+"");

        // total of posts for each plan
        total_posts.setText(p.getTotal_posts()+"");

        return convertView;
    }


}
