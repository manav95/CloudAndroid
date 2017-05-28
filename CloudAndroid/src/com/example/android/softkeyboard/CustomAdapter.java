package com.example.android.softkeyboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView; 
/**
 * This is the adapter for the list view in the Tests Activity
 * @author manavdutta1
 *
 * @param <Test>
 */
public class CustomAdapter<Test> extends ArrayAdapter<Test> {
     private final Context context;
     private final Test[] tests;
     /**
      * This contructs the custom array adapter for TestActivity
      * @param context- the context we require
      * @param vals- the test values we will display in the list view
      */
     public CustomAdapter(Context context, Test[] vals) {
    	 super(context, R.layout.simple_row, vals);
    	 this.context = context;
    	 this.tests = vals;
     }
	 @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		 LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 View layerView = inflater.inflate(R.layout.simple_row, parent, false);
		 TextView textView = (TextView) layerView.findViewById(R.id.rowText);
		 Test t = (Test)tests[position];
		 String value = t.toString();
		 int pos = value.indexOf("type");
		 textView.setText(value.substring(0, pos));
		 Button editView = (Button) layerView.findViewById(R.id.button2);
		 editView.setTag((Integer) position);
		 Button runView = (Button) layerView.findViewById(R.id.button1);
		 runView.setTag((Integer) position);
		 Button recordView = (Button) layerView.findViewById(R.id.button0);
		 recordView.setTag((Integer) position);
		return layerView;
	 }
}
