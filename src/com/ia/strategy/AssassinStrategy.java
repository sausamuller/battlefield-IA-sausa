package com.ia.strategy;

import java.util.ArrayList;

import com.ia.game.MoveWarrior;
import com.ia.pathfinder.BoxFinder;
import com.ia.pathfinder.PathFinder;

import ia.battle.core.BattleField;
import ia.battle.core.FieldCell;
import ia.battle.core.actions.Action;
import ia.battle.core.actions.Attack;

public class AssassinStrategy extends AbstractGeneralStrategy {

	@Override
	public Action playTurn(long tick, int actionNumber) {
		if (actionNumber == 2) {
			this.setAttack(true);
		}
		if (actionNumber == 2 && BattleField.getInstance().getEnemyData().getInRange() && this.isAttack()) {
			this.setAttack(false);
			return new Attack(getEnemyPosition());	
		} else if (actionNumber == 3 && !this.isAttack()) {
			int newX = 0;
			int newY = 0;
			if (getEnemyPosition().getY() > getPosition().getY()) {
				newY = getPosition().getY() - (getEnemyPosition().getY() - getPosition().getY());
			} else if (getEnemyPosition().getY() < getPosition().getY()) {
				newY = getPosition().getY() + (getPosition().getY() - getEnemyPosition().getY());
			} else if( getEnemyPosition().getY() == getPosition().getY()){
				newY = getPosition().getY() + 2;
			}
			
			if (getEnemyPosition().getX() > getPosition().getX()) {
				newX = getEnemyPosition().getX() - Double.valueOf(Math.floor((getEnemyPosition().getX() - (getPosition().getX() * 1.5)))).intValue();
			}
			MoveWarrior move = new MoveWarrior();
			move.setMoves(PathFinder.getInstance().findPath(getPosition(), BattleField.getInstance().getFieldCell(newX, newY)));
			return move;
		} else {
			ArrayList<FieldCell> specialItems = BattleField.getInstance().getSpecialItems();
			if (specialItems.size() > 0 ) {
				ArrayList<FieldCell> path = BoxFinder.getInstance().getPathFrom(this.getPosition());
				if(path != null){
					MoveWarrior move = new MoveWarrior();
					move.setMoves(path);
					return move;
				}
			}
		}
		return null;
	}
}
