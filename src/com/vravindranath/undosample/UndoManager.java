package com.vravindranath.undosample;

import java.util.ArrayList;

public class UndoManager {
	private ArrayList<Command> undoStack = new ArrayList<Command>();
	private ArrayList<Command> redoStack = new ArrayList<Command>();
	
	private static UndoManager mUndoManager = null;
//	private String TAG = "UndoManager";
	
	private UndoManager() {
		//Do any initializations here if required.
	}
	
	/**
	 * Returns the instance of undo manager
	 * @return
	 * UndoManager Object
	 */
	public static UndoManager getInstance() {
		if(mUndoManager == null) {
			mUndoManager = new UndoManager();
		}
		return mUndoManager;
	}
	
	/**
	 * Performs undo on the topmost item in the undo stack
	 */
	public void undo() {
		if (isUndoAvailable()) {
			Command command = undoStack.remove(undoStack.size() - 1);
			command.execute(Command.UNDO);
		}
		
//		Log.d(TAG  , "undoStack length: " + undoStack.size());
//		Log.d(TAG  , "redoStack length: " + redoStack.size());
	}
	
	public void redo() {
		if (isRedoAvailable()) {
			Command command = redoStack.remove(redoStack.size() - 1);
			command.execute(Command.REDO);
		}
		
//		Log.d(TAG  , "undoStack length: " + undoStack.size());
//		Log.d(TAG  , "redoStack length: " + redoStack.size());
	}
	
	public void resetRedoStack() {
		redoStack.clear();
	}
	
	public boolean isUndoAvailable() {
		if(undoStack.size() > 0) {
			return true;
		}
		return false;
	}
	
	public boolean isRedoAvailable() {
		if(redoStack.size() > 0) {
			return true;
		}
		return false;
	}
	
	public void addToUndoStack(Command command) {
		undoStack.add(command);
	}
	
	public void addToRedoStack(Command command) {
		redoStack.add(command);
	}
}
