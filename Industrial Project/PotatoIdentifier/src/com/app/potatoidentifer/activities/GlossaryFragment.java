package com.app.potatoidentifer.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.potatoidentifier.R;

public class GlossaryFragment extends BaseFragment {
    ListView list;
    String[] glossary_list = {
            "Leaf",
            "Potato",
            "Placeholder",
            "Bug",
            "Window",
            "Insect",
            "Nitrogen"
    };
    Integer[] imageId = {
            R.drawable.leaf,
            R.drawable.leaf2,
            R.drawable.leaf,
            R.drawable.leaf,
            R.drawable.leaf,
            R.drawable.leaf,
            R.drawable.leaf
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.glossary_fragment_layout, container, false);
        CustomListView adapter = new CustomListView(getActivity(), glossary_list, imageId);
        list = (ListView) v.findViewById(R.id.glossary_listview);
        list.setAdapter(adapter);
        list.setOnItemClickListener(listViewListenerHandler);
        return v;
    }

    //Create a message handling object as an anonymous class.
    private AdapterView.OnItemClickListener listViewListenerHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(android.R.id.tabcontent, new FurtherInfo());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            Log.d(GlossaryFragment.class.getName(), "FUCK SAKE");
        }
    };
}
