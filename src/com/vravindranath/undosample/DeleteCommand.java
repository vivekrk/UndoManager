package com.vravindranath.undosample;

import android.content.Context;

public class DeleteCommand implements Command {
	
	private MainActivity mContext = null;
	private Integer mItem = null;
	private UndoManager mUndoManager = null;
	
	public DeleteCommand(Context context, Integer item) {
		mContext = (MainActivity) context;
		mItem = item;
		mUndoManager = UndoManager.getInstance();
	}

	@Override
	public void execute(String command) {
		if (command == REDO) {
			mContext.getItemsArray().remove(mItem);
			mUndoManager.addToUndoStack(this);
		} else if(command == UNDO) {
			mContext.getItemsArray().add(mItem);
			mUndoManager.addToRedoStack(this);
		} else {
			mContext.getItemsArray().remove(mItem);
			mUndoManager.addToUndoStack(this);
			mUndoManager.resetRedoStack();
		}
	}

}
