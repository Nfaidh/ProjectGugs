package com.dragus.ellouzeandpartners.activities;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dragus.ellouzeandpartners.adapter.CustomListCollabsAdapter;
import com.dragus.ellouzeandpartners.dragus.R;
import com.dragus.ellouzeandpartners.helper.DataBaseHelper;
import com.dragus.ellouzeandpartners.model.FirstLastName;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DetailsPlanActivity extends ActionBarActivity implements View.OnClickListener, View.OnDragListener, View.OnLongClickListener {


    //----------------------------------------- CONSTANT --------------------------------------------------------------//
    public static final String TITLE_DIALOG_ADD = "ConneXia | Ajouter"; // title show dialog
    private static final String TAG = DetailsPlanActivity.class.getSimpleName();// Log tag
    private static final String url = "http://10.0.2.2:80/test/v1/collabs";// Collabs json url


    //----------------------------------------- CONTENT --------------------------------------------------------------//
    public static CustomListCollabsAdapter list_collabs_adapter;
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    int i4 = 0;
    private DataBaseHelper db;// database
    private ImageButton btn_add_collab;
    private ImageButton btn_drag_reserv_post;
    private ImageButton btn_drag_collab_post;
    // content of dialog add collab
    private Button btn_ok_add;
    private Button btn_dismiss_add;
    // spinner
    private Spinner spinner_reserv_post;
    private String post_reserv_selected;
    // ----- LAYOUT DRAG
    private LinearLayout layout_drag;// layout where the image of plan is shown and where posts are dragged and dropped
    private FrameLayout box_drop_1; //box1 to drop
    private int id_bg_circle;
    private TextView text_drag;
    private AutoCompleteTextView textfield_collab;
    private CustomListCollabsAdapter auto_complete_adapter;
    private String selected_collab_ac = "";
    //listview collab
    private ArrayList<FirstLastName> collabsNameList = new ArrayList<FirstLastName>();
    private ListView listViewCollabs;
    //listview collab
    private List<FirstLastName> list_collabs_names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_plan);

        // getting views from layout
        listViewCollabs = (ListView) findViewById(R.id.list_added_collab);// get listview of collabs
        btn_add_collab = (ImageButton) findViewById(R.id.btn_add_collab);
        btn_drag_reserv_post = (ImageButton) findViewById(R.id.btn_drag_reserv_post);
        btn_drag_collab_post = (ImageButton) findViewById(R.id.btn_drag_post);
        layout_drag = (LinearLayout) findViewById(R.id.center_right_layout); // layout drag
        box_drop_1 = (FrameLayout) findViewById(R.id.layout_drag_1);
        spinner_reserv_post = (Spinner) findViewById(R.id.spinner_reserv_post);
        text_drag = (TextView) findViewById(R.id.text_drag);

        //register drag event listeners for the target layout containers
        layout_drag.setOnDragListener(this);
        box_drop_1.setOnDragListener(this);


        //database sqlite
        db = new DataBaseHelper(DetailsPlanActivity.this);

        //set adapter for listview of collabs
        list_collabs_adapter = new CustomListCollabsAdapter(this, collabsNameList, "From List Collabs");
        listViewCollabs.setAdapter(list_collabs_adapter);

        btn_add_collab.setOnClickListener(this);
        btn_drag_reserv_post.setOnClickListener(this);
        btn_drag_collab_post.setOnClickListener(this);


        // get list of collabs names from database
        list_collabs_names = db.getAllNamesCollabs();
        Log.e("LIST", list_collabs_names.get(0).getFirst_last_name());


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_add_collab:

                // custom dialog for add collab
                final Dialog dialog_add = new Dialog(this);
                dialog_add.setContentView(R.layout.manag_collab);
                dialog_add.getWindow().setLayout(700, 370);
                dialog_add.setTitle(TITLE_DIALOG_ADD);

                // set the custom dialog components - text, textfield and buttons
                btn_ok_add = (Button) dialog_add.findViewById(R.id.btn_ok);
                btn_dismiss_add = (Button) dialog_add.findViewById(R.id.btn_dismiss);
                textfield_collab = (AutoCompleteTextView) dialog_add.findViewById(R.id.autoComplete_add_collab);


                // get Position selected in auto complete text field
                textfield_collab.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //get collab of item selected
                        selected_collab_ac = ((FirstLastName) auto_complete_adapter.getItem(position)).getFirst_last_name();
                        Log.i(TAG, "Item selected is " + selected_collab_ac);

                    }
                });

                // set adapter for auto complete

                auto_complete_adapter = new CustomListCollabsAdapter(this, list_collabs_names, "From Auto Complete");
                Log.e("HEY", list_collabs_names.get(0).getFirst_last_name());
                textfield_collab.setAdapter(auto_complete_adapter);

                // if button dismiss is clicked, close the custom dialog
                btn_dismiss_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_add.dismiss();
                    }
                });

                // if button dismiss is clicked, close the custom dialog
                btn_ok_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        // ADD to listview the collab selected
                        if (!selected_collab_ac.equals("")) {

                            // call method to verify if collab exists
                            boolean isCollabExist = collab_exist(selected_collab_ac);
                            // if collabs exists do not add
                            if (!isCollabExist) {
                                Log.e(TAG, selected_collab_ac);
                                collabsNameList.add(new FirstLastName(selected_collab_ac));
                                list_collabs_adapter.notifyDataSetChanged();
                            } else
                                Toast.makeText(DetailsPlanActivity.this, "Collaborateur existe déjà dans la liste", Toast.LENGTH_SHORT).show();

                        } else {
                            Log.i(TAG, "No Item selected");
                        }


                        dialog_add.dismiss();
                    }
                });


                dialog_add.show();

                break;


            case R.id.btn_drag_reserv_post:

                post_reserv_selected = spinner_reserv_post.getSelectedItem().toString();
                Log.d(TAG + "Spinner", " Item selected in spinner is : " + post_reserv_selected);

                ImageView m_img = new ImageView(this);

                if (post_reserv_selected.equals("Poste technique"))
                    id_bg_circle = R.drawable.ic_blue_circle;
                else if (post_reserv_selected.equals("Poste de passage"))
                    id_bg_circle = R.drawable.ic_orange_circle;

                else
                    id_bg_circle = R.drawable.ic_pink_circle;

                m_img.setBackgroundResource(id_bg_circle);
                m_img.setTag(id_bg_circle);

                layout_drag.addView(m_img, 50, 50);

                // register a long click listener for the circles
                m_img.setOnLongClickListener(this);


                break;

            case R.id.btn_drag_post:

                ImageView collab_img = new ImageView(this);
                collab_img.setBackgroundResource(R.drawable.ic_black_circle);

                layout_drag.addView(collab_img, 50, 50);

                // register a long click listener for the circles
                collab_img.setOnLongClickListener(this);
                collab_img.setTag(R.drawable.ic_black_circle);


                break;

            default:
                break;

        }
    }

    // verify if list collab contains collab or not (function that returns boolean)
    private boolean collab_exist(String collab) {


        boolean trouve = false;

        if (collabsNameList.size() != 0)
            for (int i = 0; i < collabsNameList.size(); i++) {
                if (collabsNameList.get(i).getFirst_last_name().equals(collab)) {
                    trouve = true;
                }
            }

        return trouve;


    }

    // TODO complet onDrag Action
    @Override
    public boolean onDrag(View receivingLayoutView, DragEvent dragEvent) {
        View draggedImageView = (View) dragEvent.getLocalState();

        // Handles each of the expected events
        switch (dragEvent.getAction()) {

            case DragEvent.ACTION_DRAG_STARTED:
                Log.i(TAG, "drag action started");

                // Determines if this View can accept the dragged data
                if (dragEvent.getClipDescription()
                        .hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    Log.i(TAG, "Can accept this data");

                    // returns true to indicate that the View can accept the dragged data.
                    return true;

                } else {
                    Log.i(TAG, "Can not accept this data");

                }

                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
                return false;

            case DragEvent.ACTION_DRAG_ENTERED:
                Log.i(TAG, "drag action entered");
                //  the drag point has entered the bounding box
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                Log.i(TAG, "drag action location");
                /*triggered after ACTION_DRAG_ENTERED
                stops after ACTION_DRAG_EXITED*/
                return true;

            case DragEvent.ACTION_DRAG_EXITED:
                Log.i(TAG, "drag action exited");
                //  the drag shadow has left the bounding box
                return true;

            case DragEvent.ACTION_DROP:
                  /* the listener receives this action type when
                  drag shadow released over the target view
            the action only sent here if ACTION_DRAG_STARTED returned true
            return true if successfully handled the drop else false*/

                Log.e("TYPE LAYOUT","Type is "+receivingLayoutView.getClass().toString());

                ViewGroup draggedImageViewParentLayout = (ViewGroup) draggedImageView.getParent();
                draggedImageViewParentLayout.removeView(draggedImageView);

                ViewGroup bottomLayout;
                if(receivingLayoutView.getClass().equals(FrameLayout.class)){
                    bottomLayout = (FrameLayout) receivingLayoutView;
                }
                else {
                    bottomLayout = (LinearLayout) receivingLayoutView;
                }




                switch (getDrawableId((ImageView) draggedImageView)) {
                    case R.drawable.ic_black_circle:
                        Log.e("Drop", " Poste COLLAB ");


                        bottomLayout.addView(draggedImageView);
                        draggedImageView.setVisibility(View.VISIBLE);

                        if (i1 == 0) {
                            i1++;

                            if (i2 == 1 || i3 == 1 || i4 == 1)
                                text_drag.setText("Double");
                            else
                                text_drag.setText("Poste Collab X");
                        } else {
                            i1--;
                            if (i2 == 1)
                                text_drag.setText("Poste Passage");
                            else if (i3 == 1)
                                text_drag.setText("Poste Technique");
                            else if (i4 == 1)
                                text_drag.setText("Poste Pouvoir");
                            else
                                text_drag.setText("Libre");
                        }
                        return true;

                    case R.drawable.ic_orange_circle:
                        Log.e("Drop", " Poste PASSAGE ");


                        bottomLayout.addView(draggedImageView);
                        draggedImageView.setVisibility(View.VISIBLE);


                        if (i2 == 0) {
                            i2++;

                            if (i1 == 1 || i3 == 1 || i4 == 1)
                                text_drag.setText("Double");
                            else
                                text_drag.setText("Poste Passage");
                        } else {
                            i2--;
                            if (i1 == 1)
                                text_drag.setText("Poste Collab X");
                            else if (i3 == 1)
                                text_drag.setText("Poste Technique");
                            else if (i4 == 1)
                                text_drag.setText("Poste Pouvoir");
                            else
                                text_drag.setText("Libre");
                        }


                        return true;

                    case R.drawable.ic_blue_circle:
                        Log.e("Drop", " Poste TECHNIQUE ");

                        bottomLayout.addView(draggedImageView);
                        draggedImageView.setVisibility(View.VISIBLE);

                        if (i3 == 0) {
                            i3++;

                            if (i1 == 1 || i2 == 1 || i4 == 1)
                                text_drag.setText("Double");
                            else
                                text_drag.setText("Poste Technique");
                        } else {
                            i3--;
                            if (i1 == 1)
                                text_drag.setText("Poste Collab X");
                            else if (i2 == 1)
                                text_drag.setText("Poste Passage");
                            else if (i4 == 1)
                                text_drag.setText("Poste Pouvoir");
                            else
                                text_drag.setText("Libre");
                        }




                        return true;
                    case R.drawable.ic_pink_circle:
                        Log.e("Drop", " Poste POUVOIR ");

                        bottomLayout.addView(draggedImageView);
                        draggedImageView.setVisibility(View.VISIBLE);

                        if (i4 == 0) {
                            i4++;
                            if (i1 == 1 || i3 == 1 || i2 == 1)
                                text_drag.setText("Double");
                            else
                                text_drag.setText("Poste Pouvoir");
                        } else {
                            i4--;
                            if (i1 == 1)
                                text_drag.setText("Poste Collab X");
                            else if (i2 == 1)
                                text_drag.setText("Poste Passage");
                            else if (i3 == 1)
                                text_drag.setText("Poste Technique");
                            else
                                text_drag.setText("Libre");
                        }


                        return true;

                    default:
                        Log.i(TAG, "in default");
                        return false;
                }


            case DragEvent.ACTION_DRAG_ENDED:

                Log.i(TAG, "drag action ended");
                Log.i(TAG, "getResult: " + dragEvent.getResult());

                //if the drop was not successful, set the ball to visible
                if (!dragEvent.getResult()) {
                    Log.i(TAG, "setting visible");
                    draggedImageView.setVisibility(View.VISIBLE);
                }

                return true;
            // An unknown action type was received.
            default:
                Log.i(TAG, "Unknown action type received by OnDragListener.");
                break;
        }
        return false;
    }

    // TODO complet onLongClick Action
    @Override
    public boolean onLongClick(View imageView) {
        // the circle has been touched
        // create clip data holding data of the type MIMETYPE_TEXT_PLAIN
        ClipData clipData = ClipData.newPlainText("", "");

        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(imageView);
            /*start the drag - contains the data to be dragged,
            metadata for this data and callback for drawing shadow*/
        imageView.startDrag(clipData, shadowBuilder, imageView, 0);
        // we're dragging the shadow so make the view invisible
        imageView.setVisibility(View.INVISIBLE);
        return true;
    }

    private int getDrawableId(ImageView iv) {
        return (Integer) iv.getTag();
    }
}
