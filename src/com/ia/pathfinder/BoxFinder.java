package com.ia.pathfinder;

import java.util.ArrayList;

import ia.battle.core.BattleField;
import ia.battle.core.FieldCell;

public final class BoxFinder {
	
	private static BoxFinder instance;
	
	private BoxFinder(){
		
	}
	
	public static final BoxFinder getInstance(){
		if(instance == null){
			instance = new BoxFinder();
		}
		return instance;
	}
	
	public ArrayList<FieldCell> getPathFrom(FieldCell position){
		
		ArrayList<FieldCell> boxes = BattleField.getInstance().getSpecialItems();
		if(boxes.isEmpty()){
			return null;
		}else{
			FieldCell selectedBox = boxes.get(0);
			for(FieldCell sItem : boxes){
				int deltaX = Math.abs(position.getX() - sItem.getX());
				int deltaY = Math.abs(position.getY() - sItem.getY());
				
				if(deltaX < Math.abs(position.getX() - selectedBox.getX()) &&
				   deltaY < Math.abs(position.getY() - selectedBox.getY())){
					selectedBox = sItem;
				}
			}
			
			ArrayList<FieldCell> path = PathFinder.getInstance().findPath(position, selectedBox);
			return path;
		}
	}

}
