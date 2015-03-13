package com.dragus.ellouzeandpartners.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.dragus.ellouzeandpartners.adapter.CustomListPlansAdapter;
import com.dragus.ellouzeandpartners.dragus.R;
import com.dragus.ellouzeandpartners.model.Infos_Plan;

import java.util.ArrayList;
import java.util.List;


public class ListPlansActivity extends ActionBarActivity {


    //constant
    public static final String EMPTY_STRING = "";
    private List<Infos_Plan> infosList = new ArrayList<Infos_Plan>();
    private ListView listViewPlans;
    private CustomListPlansAdapter adapter;
    // Totals
    private int tot_collab = 0;
    private int tot_occupied = 0;
    private int tot_passg = 0;
    private int tot_power = 0;
    private int tot_tech = 0;
    private int tot_freed = 0;
    private int tot_tot_posts = 0;
    //Textview totals
    private TextView text_tot_collab;
    private TextView text_tot_occupied;
    private TextView text_tot_passg;
    private TextView text_tot_power;
    private TextView text_tot_tech;
    private TextView text_tot_freed;
    private TextView text_tot_tot_posts;
    //intent de redirection
    Intent intent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_plans);


        text_tot_collab = (TextView) findViewById(R.id.id_tot_collab);
        text_tot_occupied = (TextView) findViewById(R.id.id_tot_post_occup);
        text_tot_passg = (TextView) findViewById(R.id.id_tot_post_passg);
        text_tot_power = (TextView) findViewById(R.id.id_tot_post_power);
        text_tot_tech = (TextView) findViewById(R.id.id_tot_post_tech);
        text_tot_freed = (TextView) findViewById(R.id.id_tot_post_freed);
        text_tot_tot_posts = (TextView) findViewById(R.id.id_tot_tot_posts);


        // static list of infos Plan
        infosList.add(new Infos_Plan("H0Z0", 1, 1, 0, 1, 4, 0, 6));
        infosList.add(new Infos_Plan("H0Z123", 5, 4, 2, 1, 3, 34, 44));
        infosList.add(new Infos_Plan("H1Z12", 34, 32, 0, 0, 1, 2, 35));
        infosList.add(new Infos_Plan("H1Z3", 1, 1, 0, 1, 4, 0, 6));
        infosList.add(new Infos_Plan("H2Z12", 5, 4, 2, 1, 3, 34, 44));
        infosList.add(new Infos_Plan("H2Z3", 34, 32, 0, 0, 1, 2, 35));

        listViewPlans = (ListView) findViewById(R.id.list_plans);

        listViewPlans.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("Item", "clicked");
                Log.e("Position Item", " position is : " + position);


                intent = new Intent(ListPlansActivity.this, DetailsPlanActivity.class);
                startActivity(intent);
            }
        });


        adapter = new CustomListPlansAdapter(this, infosList);
        listViewPlans.setAdapter(adapter);


        // fill infos List with data from WS, and calculate totals : TODO
        for (int i = 0; i < adapter.getCount(); i++) {
            tot_collab += infosList.get(i).getPlaced_collab();
            tot_occupied += infosList.get(i).getOccupied_posts();
            tot_passg += infosList.get(i).getPassage_posts();
            tot_power += infosList.get(i).getPower_posts();
            tot_tech += infosList.get(i).getTech_posts();
            tot_freed += infosList.get(i).getFreed_posts();
            tot_tot_posts += infosList.get(i).getTotal_posts();
        }


        // change the content of totals
        text_tot_collab.setText(EMPTY_STRING + tot_collab);
        text_tot_occupied.setText(EMPTY_STRING + tot_occupied);
        text_tot_passg.setText(EMPTY_STRING + tot_passg);
        text_tot_power.setText(EMPTY_STRING + tot_power);
        text_tot_tech.setText(EMPTY_STRING + tot_tech);
        text_tot_freed.setText(EMPTY_STRING + tot_freed);
        text_tot_tot_posts.setText(EMPTY_STRING + tot_tot_posts);


    }


}
