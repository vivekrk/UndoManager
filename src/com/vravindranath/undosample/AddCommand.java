package com.vravindranath.undosample;

import android.content.Context;

public class AddCommand implements Command{
	
	private MainActivity mContext = null;
	private Integer mItem = null;
	
	private UndoManager mUndoManager = null;
			
	public AddCommand(Context context, Integer item) {
		mContext = (MainActivity) context;
		mItem = item;
		
		mUndoManager = UndoManager.getInstance();
	}

	@Override
	public void execute(String command) {
		if (command == REDO) {
			mContext.getItemsArray().add(mItem);
			mUndoManager.addToUndoStack(this);
		} else if(command == UNDO) {
			mContext.getItemsArray().remove(mItem);
			mUndoManager.addToRedoStack(this);
		} else {
			mContext.getItemsArray().add(mItem);
			mUndoManager.addToUndoStack(this);
		}
	}

}
