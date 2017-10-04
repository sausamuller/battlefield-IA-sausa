package com.ia.strategy;

import com.ia.game.MoveWarrior;
import com.ia.pathfinder.PathFinder;

import ia.battle.core.BattleField;
import ia.battle.core.actions.Action;
import ia.battle.core.actions.Attack;

public class BerserkerStrategy extends AbstractGeneralStrategy{
	
	@Override
	public Action playTurn(long tick, int actionNumber) {
		
		if (BattleField.getInstance().getEnemyData().getInRange()) {
			return new Attack(getEnemyPosition());	
		} else {
			MoveWarrior move = new MoveWarrior();
			move.setMoves(PathFinder.getInstance().findPath(getPosition(), BattleField.getInstance().getEnemyData().getFieldCell()));
			return move;
		}
	}

}
