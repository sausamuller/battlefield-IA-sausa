package com.ia.strategy;

import ia.battle.core.FieldCell;
import ia.battle.core.actions.Action;

public class AbstractGeneralStrategy implements Strategy {
	
	private FieldCell position;
	private FieldCell enemyPosition;
	
	private boolean attack = true;

	public AbstractGeneralStrategy() {
		attack = true;
	}

	@Override
	public Action playTurn(long tick, int actionNumber) {
		
		return null;
	}

	public FieldCell getPosition() {
		return position;
	}

	public void setPosition(FieldCell position) {
		this.position = position;
	}

	public boolean isAttack() {
		return attack;
	}

	public void setAttack(boolean attack) {
		this.attack = attack;
	}

	public FieldCell getEnemyPosition() {
		return enemyPosition;
	}

	public void setEnemyPosition(FieldCell enemyPosition) {
		this.enemyPosition = enemyPosition;
	}

}
