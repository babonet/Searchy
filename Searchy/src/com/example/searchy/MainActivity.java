package com.example.searchy;

import java.util.ArrayList;
import com.example.searchy.Loaders.PersonLoader;
import com.example.searchy.Loaders.SmsLoader;
import com.example.searchy.Objects.Person;
import com.example.searchy.Objects.SMSMessage;
import com.example.searchy.Senders.GetData;
import com.example.searchy.Senders.PostData;

import android.app.ActionBar;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements
		ActionBar.OnNavigationListener {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current drop down position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	public ArrayList<SMSMessage> smsList = new ArrayList<SMSMessage>();
	public ArrayList<Person> personList = new ArrayList<Person>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar to show a drop down list.
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		// Set up the drop down list navigation in the action bar.
		actionBar.setListNavigationCallbacks(
		// Specify a SpinnerAdapter to populate the drop down list.
		new ArrayAdapter<String>(actionBar.getThemedContext(),
			android.R.layout.simple_list_item_1,
			android.R.id.text1, new String[] {
			getString(R.string.title_section1),
			getString(R.string.title_section2),
			getString(R.string.title_section3), }), this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		final EditText searchPhrase = (EditText) findViewById(R.id.searchPhrase);
		
		Button button= (Button) findViewById(R.id.searchBtn);
		button.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	GetData gd = new GetData(searchPhrase.getText().toString());
		    	gd.execute();
		    }
		});
		
		getPersons(); 
		getMessages();
		
	}

	

	private void getPersons() {
		PersonLoader pl = new PersonLoader(this); 
		personList = pl.fetchContacts();
		PostData<Person> pd = new PostData<Person>(personList);
		pd.execute();
	}

	private void getMessages() {
		SmsLoader sl = new SmsLoader(this);
		smsList = sl.fetchMessages();
		PostData<SMSMessage> pd = new PostData<SMSMessage>(smsList);
		pd.execute();
		indexMessages();
	}

	private void indexMessages() {
		//TODO filter our irrelevant information
		//TODO score result by relationship rate
		//TODO send indexed data with score to server
		
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current drop down position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current drop down position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
				.getSelectedNavigationIndex());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onNavigationItemSelected(int position, long id) {
		// When the given dropdown item is selected, show its contents in the
		// container view.
		Fragment fragment = new DummySectionFragment();
		Bundle args = new Bundle();
		args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
		fragment.setArguments(args);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.container, fragment).commit();
		return true;
	}

	/**
	 * A dummy fragment representing a section of the application, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

}
