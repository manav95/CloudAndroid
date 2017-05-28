package com.example.android.softkeyboard;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.android.softkeyboard.ImageHolder;
/**
 * This is the adapter for our APK drawables.
 * @author manavdutta1
 *
 * @param <String>
 */
public class MotherAdapter<String> extends ArrayAdapter<String> {
	 private final Context context;
     private String[] resolves;
     private Drawable[] imageIds;
     public MotherAdapter(Context context, String[] vals) {
    	 super(context, R.layout.apk, vals);
    	 this.context = context;
    	 this.resolves = vals;
    	 imageIds = ImageHolder.getResids();
     }
     @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		 LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 View layerView = inflater.inflate(R.layout.apk, parent, false);
		 TextView textView = (TextView) layerView.findViewById(R.id.stuff);
		 textView.setText((CharSequence) resolves[position]);
		 Button valueView = (Button) layerView.findViewById(R.id.buttonChoose);
		 valueView.setTag((Integer) position);
		 ImageView image = (ImageView) layerView.findViewById(R.id.imView1);
		 image.setImageDrawable(imageIds[position]);
		return layerView;
     }
		 
}
