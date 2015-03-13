package com.dragus.ellouzeandpartners.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dragus.ellouzeandpartners.activities.DetailsPlanActivity;
import com.dragus.ellouzeandpartners.dragus.R;
import com.dragus.ellouzeandpartners.model.Collab;
import com.dragus.ellouzeandpartners.model.FirstLastName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by T-3500 on 24/02/2015.
 */
public class CustomListCollabsAdapter extends BaseAdapter implements Filterable {



    // Log tag
    private static final String TAG = CustomListCollabsAdapter.class.getSimpleName();

    public static final String SPACE = " ";
    public static final String TITLE_DIALOG_REMOVE = "ConneXia | Supprimer";
    List<FirstLastName> orig;
    private Activity activity;
    private LayoutInflater inflater;
    private List<FirstLastName> collabsItems;
    private NameFilter filter;
    private String type_call;
    //content of dialog remove collab
    private Button btn_ok_remove;
    private Button btn_dismiss_remove;
    private TextView textview_search;
    private AutoCompleteTextView textfield_collab;


    public CustomListCollabsAdapter(Activity activity, List<FirstLastName> collabsItems,String type_call) {
        this.activity = activity;
        this.collabsItems = collabsItems;
        this.type_call = type_call;
        if(type_call.equals("From Auto Complete")) {
            orig = this.collabsItems;
            filter = new NameFilter();
        }


    }


    @Override
    public int getCount() {
        if (collabsItems != null)
            return collabsItems.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int location) {
        return collabsItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_single_collab, null);


        TextView collab_name = (TextView) convertView.findViewById(R.id.id_collab_in_list);


        if(!type_call.equals("From Auto Complete")) {

            ImageButton btn_remove = (ImageButton) convertView.findViewById(R.id.btn_remove_collab);
            btn_remove.setVisibility(View.VISIBLE);

            btn_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    final Dialog dialog_remove = new Dialog(activity);
                    dialog_remove.setContentView(R.layout.manag_collab);
                    dialog_remove.getWindow().setLayout(680, 300);
                    dialog_remove.setTitle(TITLE_DIALOG_REMOVE);

                    // set the custom dialog components - text, textfield and buttons

                    btn_ok_remove = (Button) dialog_remove.findViewById(R.id.btn_ok);
                    btn_dismiss_remove = (Button) dialog_remove.findViewById(R.id.btn_dismiss);
                    textview_search = (TextView) dialog_remove.findViewById(R.id.textview_search);
                    textfield_collab = (AutoCompleteTextView) dialog_remove.findViewById(R.id.autoComplete_add_collab);

                    // hide textfield auto complet
                    textfield_collab.setVisibility(View.INVISIBLE);

                    //change width of textview_search
                    textview_search.setWidth(600);

                    // get Name of collab
                    String name =collabsItems.get(position).getFirst_last_name();

                    // edit text "Rechercher :"
                    textview_search.setText(activity.getResources().getString(R.string.text_valid_remove)+SPACE+name);



                    // if button dismiss is clicked, close the custom dialog
                    btn_dismiss_remove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog_remove.dismiss();
                        }
                    });

                    // if button dismiss is clicked, close the custom dialog
                    btn_ok_remove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Log.d(TAG, "Item in position "+position+" is removed");
                            collabsItems.remove(position);

                            DetailsPlanActivity.list_collabs_adapter.notifyDataSetChanged();

                           dialog_remove.dismiss();
                        }
                    });

                    dialog_remove.show();



                }
            });

        }

        if (!collabsItems.isEmpty()) {
            // getting collab data for the row
            FirstLastName c = collabsItems.get(position);

            // name of collab
            collab_name.setText(c.getFirst_last_name());


        }


        return convertView;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private class NameFilter extends Filter {

        public NameFilter() {

        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults oReturn = new FilterResults();
            ArrayList<FirstLastName> results = new ArrayList<FirstLastName>();
            if (orig == null)
                orig = collabsItems;

            if (constraint != null) {
                if (orig != null && orig.size() > 0) {
                    for (FirstLastName c : orig) {
                        if (c.getFirst_last_name().contains(constraint))
                            results.add(c);
                    }
                }
                oReturn.values = results;
            }
            return oReturn;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            collabsItems = (ArrayList<FirstLastName>) results.values;
            notifyDataSetChanged();
        }
    }


}
