package com.app.potatoidentifer.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.app.potatoidentifer.models.GlossaryCategoriesBean;
import com.app.potatoidentifer.models.GlossaryCategoriesDataSource;
import com.example.potatoidentifier.R;

import java.io.ByteArrayInputStream;
import java.util.List;

public class GlossaryCategoriesListFragment extends BaseFragment {
	private ListView list;
    private String[] glossary_list;
	private Bitmap[] imageId;
    private Integer[] categoryId;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.glossary_fragment_layout,
				container, false);
		
		final Context context = this.getActivity();
        final GlossaryCategoriesDataSource ds = new GlossaryCategoriesDataSource(context);
        ds.open();
        final EditText srchText =(EditText) v.findViewById(R.id.editText1);
		Button btn = (Button) v.findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ds.open();
                boolean exists = ds.doesDieaseExist(srchText.getText().toString());
                Log.v("Exists", "Exist " + exists);
                if (exists == true) {
                    Bundle bundle = new Bundle();
                    bundle.putString("category", srchText.getText().toString());
                    FurtherInfo fi = new FurtherInfo();
                    fi.setArguments(bundle);
                    fragmentTabActivity.addFragments(Const.TAB_FIRST, fi, true);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("The disease you searched doesn't exist in the application");
                    builder.setTitle("Error");
                    builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();

                }
            }

        });

        List<GlossaryCategoriesBean> categories = ds.getGlossaryCategoryInfo();
        //Set sizes of arrays when we know the size of the list.
        glossary_list = new String[categories.size()];
        imageId = new Bitmap[categories.size()];
        categoryId = new Integer[categories.size()];

        Bitmap theImage = null;
        //Convert and add the list to the appropriate arrays.
        for(int i = 0; i < categories.size(); i++) {
            categoryId[i] = categories.get(i).getID();
            //Converting strings to a drawable.
            byte[] blob = categories.get(i).getImageID();
            ByteArrayInputStream imageStream = new ByteArrayInputStream(blob);
            imageId[i] = BitmapFactory.decodeStream(imageStream);
            //int resID = getResources().getIdentifier(mDrawableName , "drawable", context.getPackageName());
            glossary_list[i] = categories.get(i).getTitle();
        }


        //Sets up custom list view for categories
		CategoriesCustomListView adapter = new CategoriesCustomListView(getActivity(), glossary_list, imageId);
		list = (ListView) v.findViewById(R.id.glossary_listview);
		list.setAdapter(adapter);
		//Sets up on an item click listener - to check for when a click occurs on the item list
		list.setOnItemClickListener(listViewListenerHandler);
		return v;

	}

	private AdapterView.OnItemClickListener listViewListenerHandler = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView parent, View v, int position, long id) {
            Bundle bundle = new Bundle();
            bundle.putString("symptom", glossary_list[position]);
            GlossaryFragment gf = new GlossaryFragment();
            gf.setArguments(bundle);
			fragmentTabActivity.addFragments(Const.TAB_FIRST, gf, true);
		}
	};
}