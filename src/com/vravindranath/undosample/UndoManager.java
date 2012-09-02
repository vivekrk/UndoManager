package com.vravindranath.undosample;

import java.util.ArrayList;

public class UndoManager {
	private ArrayList<Command> undoStack = new ArrayList<Command>();
	private ArrayList<Command> redoStack = new ArrayList<Command>();
	
//	private MainActivity mContext = null;
	
	private static UndoManager mUndoManager = null;
//	private String TAG = "UndoManager";
	
	private UndoManager() {
//		mContext = (MainActivity) context;
	}
	
	public static UndoManager getInstance() {
		if(mUndoManager == null) {
			mUndoManager = new UndoManager();
		}
		return mUndoManager;
	}
	
	public void undo() {
		if (undoStack.size() > 0) {
			Command command = undoStack.remove(undoStack.size() - 1);
			command.execute(Command.UNDO);
		}
		
//		Log.d(TAG  , "undoStack length: " + undoStack.size());
//		Log.d(TAG  , "redoStack length: " + redoStack.size());
	}
	
	public void redo() {
		if (redoStack.size() > 0) {
			Command command = redoStack.remove(redoStack.size() - 1);
			command.execute(Command.REDO);
		}
		
//		Log.d(TAG  , "undoStack length: " + undoStack.size());
//		Log.d(TAG  , "redoStack length: " + redoStack.size());
	}
	
	public void addToUndoStack(Command command) {
		undoStack.add(command);
	}
	
	public void addToRedoStack(Command command) {
		redoStack.add(command);
	}
}
