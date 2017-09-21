package com.ia.game;

import java.util.ArrayList;

import ia.battle.core.FieldCell;
import ia.battle.core.actions.Move;

public class MoveWarrior extends Move {

	private ArrayList<FieldCell> moves;
	
	public MoveWarrior() {
        moves = new ArrayList<FieldCell>();
	}

	public void setMoves(ArrayList<FieldCell> moves){
		this.moves = moves;
	}
	
	@Override
	public ArrayList<FieldCell> move() {
		return moves;
	}

}
