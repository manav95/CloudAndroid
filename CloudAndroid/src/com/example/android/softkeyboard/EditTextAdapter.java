package com.example.android.softkeyboard;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/**
 * This is the adapter for EditTextActivity
 * @author manavdutta1
 *
 * @param <String>
 */
public class EditTextAdapter<String> extends ArrayAdapter<String> {
	 private final Context context;
     private final String[] strings;
     /**
      * Constructs the adapter
      * @param context- the context we require
      * @param vals- the sequence of key presses we have
      */
     public EditTextAdapter(Context context, String[] vals) {
    	 super(context, R.layout.clito_row, vals);
    	 this.context = context;
    	 this.strings = vals;
     }
     @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
    	 ViewHolder holder = null;
    	 final int positioning = position;
    	 LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	 if (convertView == null) {
             holder = new ViewHolder();
    	     View layerView = inflater.inflate(R.layout.clito_row, parent, false);
    	     holder.view = (TextView) layerView.findViewById(R.id.keyText);
    	     holder.butt = (Button) layerView.findViewById(R.id.delButton);
    	     holder.buttTwo = (Button) layerView.findViewById(R.id.buttSwap);
    	     layerView.setTag(holder);
    	     convertView = layerView;
    	 }
    	 else {
    		 holder = (ViewHolder) convertView.getTag();
    	 }
		     holder.view.setText((CharSequence) strings[position]);
		     holder.view.setTag((Integer) position);
		     holder.butt.setTag((Integer) position);
		     holder.buttTwo.setTag((Integer) position); 
		     return convertView;
     }
}
