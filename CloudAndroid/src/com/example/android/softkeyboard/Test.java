package com.example.android.softkeyboard;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;
/**
 * This is a Test object that we use throughout our app.
 * @author manavdutta1
 *
 */
public class Test implements Parcelable{
	private String sampleTest;
	private ArrayList<String> events;
	private ArrayList<String> widgets; 
	private ArrayList<String> args;
	private String type;
	public Test(String s, String t) {
		sampleTest = s;
		type = t;
		events = new ArrayList<String>();
		widgets = new ArrayList<String>();
		args = new ArrayList<String>();
	}
	public void setType(String t) {
		type = t;
	}
	/**
	 * This sets the key events
	 * @param strings
	 */
	public void setEvents(ArrayList<String> strings) {
		this.events = strings;
	}
	/**
	 * This adds an event to our list of events
	 * @param event
	 */
	public void addToEvents(String event) {
		events.add(event);
	}
	public ArrayList<String> getEvents() {
		return this.events;
	}
	public String getNam() {
		return this.sampleTest;
	}
	public void deleteString(int position) {
		this.events.remove(position);
	}
	public void addToWidgets(String widget) {
		widgets.add(widget);
	}
	/**
	 * This is needed for our Test object to be Parcelable.
	 * Then we can send tests from Activity to Activity using Intents.
	 */
    public static final Parcelable.Creator<Test> CREATOR = new Creator<Test>() { 
		   public Test createFromParcel(Parcel source) { 
		       Test test = new Test(source.readString(), source.readString()); 
		       test.events = (ArrayList<String>) source.readValue(null); 
		       test.widgets = (ArrayList<String>) source.readValue(null); 
		       test.args = (ArrayList<String>) source.readValue(null); 
		       return test; 
		   }

		@Override
		public Test[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Test[size];
		} 
    };
	public void addToType(String arg) {
		args.add(arg);
	}
	/**
	 * This returns a string representation of our test containing all the details.
	 */
	public String toString() {
		return "Name: " + sampleTest + "type: " + type + events.toString();
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(sampleTest);
		dest.writeString(type);
		dest.writeValue(events);
		dest.writeValue(widgets);
		dest.writeValue(args);
		
	}
}
