package com.ia.strategy;

import java.util.ArrayList;

import com.ia.game.MoveWarrior;
import com.ia.pathfinder.BoxFinder;
import com.ia.pathfinder.PathFinder;

import ia.battle.core.BattleField;
import ia.battle.core.ConfigurationManager;
import ia.battle.core.FieldCell;
import ia.battle.core.actions.Action;
import ia.battle.core.actions.Attack;

public class AbstractGeneralStrategy implements Strategy {
	
	private FieldCell position;
	private FieldCell enemyPosition;
	
	private boolean attack = true;
	
	public boolean showLog = true;

	public AbstractGeneralStrategy() {
		attack = true;
	}

	@Override
	public Action playTurn(long tick, int actionNumber) {
		if (showLog) {
			System.out.println("Is Here!!");
		}
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
				newX = getPosition().getX() - (getEnemyPosition().getX() - getPosition().getX());
			} else if (getEnemyPosition().getX() < getPosition().getX()) {
				newX = getPosition().getX() + (getPosition().getX() - getEnemyPosition().getX());
			} else if (getEnemyPosition().getX() == getPosition().getX()) {
				newX = getPosition().getX() + 2;
			}
			
			if (newY <= 1 || newY >= ConfigurationManager.getInstance().getMapHeight()) {
				newX += newY;
				newY = 0;
			}
			if (newX <= 1 || newX >= ConfigurationManager.getInstance().getMapWidth()) {
				newY += newX;
				newX = 0;
			}
			
			MoveWarrior move = new MoveWarrior();
			move.setMoves(PathFinder.getInstance().findPath(getPosition(), BattleField.getInstance().getFieldCell(newX, newY)));
			return move;
		} else {
			if (showLog) {
				System.out.println("Is Ready to move!!");
			}
			ArrayList<FieldCell> specialItems = BattleField.getInstance().getSpecialItems();
			if (showLog) {
				System.out.println("special Items size: " + specialItems.size());
			}
			if (specialItems.size() > 0 ) {
				ArrayList<FieldCell> path = BoxFinder.getInstance().getPathFrom(this.getPosition());
				if(path != null){
					MoveWarrior move = new MoveWarrior();
					move.setMoves(path);
					return move;
				}
			} else {
				if (showLog) {
					System.out.println("will go to attack");
				}
				MoveWarrior move = new MoveWarrior();
				move.setMoves(PathFinder.getInstance().findPath(getPosition(), BattleField.getInstance().getEnemyData().getFieldCell()));
				return move;
			}
		}
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
