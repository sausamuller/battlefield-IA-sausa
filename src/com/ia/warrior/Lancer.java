package com.ia.warrior;

import com.ia.strategy.LancerStrategy;
import com.ia.strategy.Strategy;

import ia.battle.core.BattleField;
import ia.battle.core.FieldCell;
import ia.battle.core.Warrior;
import ia.battle.core.actions.Action;
import ia.exceptions.RuleException;

public class Lancer extends Warrior {
	
	private Strategy strategy = new LancerStrategy();

	public Lancer(String name, int health, int defense, int strength, int speed, int range) throws RuleException {
		super(name, health, defense, strength, speed, range);
		// TODO Auto-generated constructor stub
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
		// Hola soy un ataque de gae bolg
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
