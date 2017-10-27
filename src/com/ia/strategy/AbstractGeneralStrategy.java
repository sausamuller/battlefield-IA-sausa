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

	public AbstractGeneralStrategy() {
		attack = true;
	}

	@Override
	public Action playTurn(long tick, int actionNumber) {
		if (actionNumber == 1) {
			this.setAttack(true);
		}
		if (BattleField.getInstance().getEnemyData().getInRange() && this.isAttack()) {
			this.setAttack(false);
			return new Attack(getEnemyPosition());	
		} else if (!this.isAttack()) {
			int newX = 0;
			int newY = 0;
			if (getEnemyPosition().getY() > getPosition().getY()) {
				newY = (getPosition().getY() - (getEnemyPosition().getY() - getPosition().getY())) * 10;
				if (newY >= ConfigurationManager.getInstance().getMapHeight()) {
					newY = ConfigurationManager.getInstance().getMapHeight() - 1;
				}
			} else if (getEnemyPosition().getY() < getPosition().getY()) {
				newY = (getPosition().getY() + (getPosition().getY() - getEnemyPosition().getY())) * 10;
				if (newY >= ConfigurationManager.getInstance().getMapHeight()) {
					newY = ConfigurationManager.getInstance().getMapHeight() - 1;
				}
			} else if( getEnemyPosition().getY() == getPosition().getY()){
				newY = (getPosition().getY() + 2) * 10;
				if (newY >= ConfigurationManager.getInstance().getMapHeight()) {
					newY = ConfigurationManager.getInstance().getMapHeight() - 1;
				}
			}
			
			if (getEnemyPosition().getX() > getPosition().getX()) {
				newX = (getPosition().getX() - (getEnemyPosition().getX() - getPosition().getX())) * 10;
				if (newX >= ConfigurationManager.getInstance().getMapWidth()) {
					newX = ConfigurationManager.getInstance().getMapWidth() - 1;
				}
			} else if (getEnemyPosition().getX() < getPosition().getX()) {
				newX = (getPosition().getX() + (getPosition().getX() - getEnemyPosition().getX())) * 10;
				if (newX >= ConfigurationManager.getInstance().getMapWidth()) {
					newX = ConfigurationManager.getInstance().getMapWidth() - 1;
				}
			} else if (getEnemyPosition().getX() == getPosition().getX()) {
				newX = (getPosition().getX() + 2) * 10;
				if (newX >= ConfigurationManager.getInstance().getMapWidth()) {
					newX = ConfigurationManager.getInstance().getMapWidth() - 1;
				}
			}
			
			MoveWarrior move = new MoveWarrior();
			try {
				move.setMoves(PathFinder.getInstance().findPath(getPosition(), BattleField.getInstance().getFieldCell(newX, newY)));
			} catch (Exception e) {
				System.out.println("enter here");
				System.out.println(newX);
				System.out.println(newY);
				e.printStackTrace();
			}
			
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
			} else {
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
