package com.vravindranath.undosample;

import java.util.ArrayList;

import com.example.undosample.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private ArrayList<Integer> items = new ArrayList<Integer>();
	private String TAG = "MainActivity";
	
	private int newNumber = 0;
	
	private UndoManager mUndoManager = null;
	
	private ArrayAdapter<Integer> mListAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView listView = (ListView) findViewById(R.id.listView);
		
		//Store the instance of undomanager locally
		mUndoManager = UndoManager.getInstance();
		
		mListAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, items);
		listView.setAdapter(mListAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public ArrayList<Integer> getItemsArray() {
		return items;
	}

	public void add(View view) {
		Integer integer = Integer.valueOf(newNumber++);
		
		Command command = new AddCommand(this, integer);
		//mUndoManager.addToUndoStack(command);
		command.execute(null);
		
		mListAdapter.notifyDataSetChanged();
		
		//Log.d(TAG , "Items length: " + items.size());
		
		Log.d(TAG , "Add************Items: ");
		for (Integer item : items) {
			Log.d(TAG, "" + item.intValue());
		}
	}

	public void delete(View view) {
		if (items.size() > 0) {
			Integer integer = items.remove(items.size() - 1);
			Command command = new DeleteCommand(this, integer);
			command.execute(null);
		}
		mListAdapter.notifyDataSetChanged();
		
		Log.d(TAG , "Delete***************Items : ");
		for (Integer item : items) {
			Log.d(TAG, "" + item.intValue());
		}
	}

	public void undo(View view) {
		mUndoManager.undo();
		mListAdapter.notifyDataSetChanged();
		Log.d(TAG , "Undo***************Items: ");
		for (Integer item : items) {
			Log.d(TAG, "" + item.intValue());
		}
		
	}

	public void redo(View view) {
		mUndoManager.redo();
		mListAdapter.notifyDataSetChanged();
		Log.d(TAG , "Redo***************Items : ");
		for (Integer item : items) {
			Log.d(TAG, "" + item.intValue());
		}
	}
}
