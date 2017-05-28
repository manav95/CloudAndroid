package com.example.android.softkeyboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
/**
 * This is the custom adapter used in TestDetailsActivity
 * @author manavdutta1
 *
 * @param <String>
 */
public class DetailsAdapter<String> extends ArrayAdapter<String>{
	 private final Context context;
     private final String[] tees;
     /**
      * This constructs the adapter
      * @param context- the context we require
      * @param vals- an array of values displaying test results
      */
     public DetailsAdapter(Context context, String[] vals) {
    	 super(context, R.layout.details_row, vals);
    	 this.context = context;
    	 this.tees = vals;
     }
     public View getView(int position, View convertView, ViewGroup parent) {
		 LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 View layerView = inflater.inflate(R.layout.details_row, parent, false);
		 TextView butt = (TextView) layerView.findViewById(R.id.editingText);
		 butt.setText((CharSequence) tees[position]);
		 return layerView;
     }
}
