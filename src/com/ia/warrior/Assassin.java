package com.ia.warrior;

import com.ia.strategy.AssassinStrategy;
import com.ia.strategy.Strategy;

import ia.battle.core.BattleField;
import ia.battle.core.FieldCell;
import ia.battle.core.Warrior;
import ia.battle.core.actions.Action;
import ia.exceptions.RuleException;

public class Assassin extends Warrior {
	
	private Strategy strategy = new AssassinStrategy();

	public Assassin(String name, int health, int defense, int strength, int speed, int range) throws RuleException {
		super(name, health, defense, strength, speed, range);
	}

	@Override
	public Action playTurn(long tick, int actionNumber) {
		strategy.setPosition(getPosition());
		strategy.setEnemyPosition(BattleField.getInstance().getEnemyData().getFieldCell());
		return strategy.playTurn(tick, actionNumber);
	}

	@Override
	public void wasAttacked(int damage, FieldCell source) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enemyKilled() {
		// TODO Auto-generated method stub

	}

	@Override
	public void worldChanged(FieldCell oldCell, FieldCell newCell) {
		// TODO Auto-generated method stub

	}

}
