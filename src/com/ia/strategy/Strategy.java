package com.ia.strategy;

import ia.battle.core.FieldCell;
import ia.battle.core.actions.Action;

public interface Strategy {
	
	public Action playTurn(long tick, int actionNumber);
	
	public void setPosition(FieldCell position);
	
	public void setEnemyPosition(FieldCell enemyPosition);

}
