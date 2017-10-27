package com.ia.manager;

import com.ia.warrior.Archer;
import com.ia.warrior.Assassin;
import com.ia.warrior.Berserker;
import com.ia.warrior.Lancer;
import com.ia.warrior.Rider;
import com.ia.warrior.Saber;

import ia.battle.core.BattleField;
import ia.battle.core.ConfigurationManager;
import ia.battle.core.Warrior;
import ia.battle.core.WarriorManager;
import ia.exceptions.RuleException;

public class Ruler extends WarriorManager {

	private int maxPoints;
	
	private static int turn = 1;
	
	@Override
	public String getName() {
		return "Ruler";
	}

	@Override
	public Warrior getNextWarrior() throws RuleException {
		if (turn == 1) {
			turn ++;
			return calculateAssassinAtributes();
		}  else if (BattleField.getInstance().getEnemyData().getHealth() <= (BattleField.getInstance().getEnemyData().getHealth() * 0.5)) {
			turn ++;
			return calculateBerserkerAtributes();
		} else if (turn%2 == 0) {
			turn ++;
			return calculateArcherAtributes();
		}  else if (turn%3 == 0) {
			turn ++;
			return calculateLancerAtributes();
		}  else if (turn%5 == 0) {
			turn ++;
			return calculateSaberAtributes();
		} else {
			turn ++;
			return calculateRiderAtributes();
		}
//		return null;
	}

	public Warrior calculateArcherAtributes() {

		maxPoints = ConfigurationManager.getInstance().getMaxPointsPerWarrior();
		
		int health = Double.valueOf(Math.floor(maxPoints * 0.13)).intValue();
		int defense = Double.valueOf(Math.floor(maxPoints * 0.13)).intValue();
		int strength = Double.valueOf(Math.floor(maxPoints * 0.26)).intValue();
		int speed = Double.valueOf(Math.floor(maxPoints * 0.20)).intValue();
		int range = Double.valueOf(Math.floor(maxPoints * 0.27)).intValue();
		
		int sum = health + defense + strength + speed + range;

		if (sum < maxPoints) {
			strength = strength + (sum - maxPoints);
		}
		
		Warrior archer = null;
		try {
			archer = new Archer("Archer", health, defense, strength, speed, range);
		} catch (RuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return archer;
	}

	public Warrior calculateAssassinAtributes() {
		maxPoints = ConfigurationManager.getInstance().getMaxPointsPerWarrior();
		
		int health = Double.valueOf(Math.floor(maxPoints * 0.13)).intValue();
		int defense = Double.valueOf(Math.floor(maxPoints * 0.10)).intValue();
		int strength = Double.valueOf(Math.floor(maxPoints * 0.16)).intValue();
		int speed = Double.valueOf(Math.floor(maxPoints * 0.30)).intValue();
		int range = Double.valueOf(Math.floor(maxPoints * 0.30)).intValue();
		
		int sum = health + defense + strength + speed + range;

		if (sum < maxPoints) {
			speed = speed + (sum - maxPoints);
		}
		
		Warrior assassin = null;
		try {
			assassin = new Assassin("Assassin", health, defense, strength, speed, range);
		} catch (RuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return assassin;
	}

	public Warrior calculateBerserkerAtributes() {
		maxPoints = ConfigurationManager.getInstance().getMaxPointsPerWarrior();
		
		int health = Double.valueOf(Math.floor(maxPoints * 0.10)).intValue();
		int defense = Double.valueOf(Math.floor(maxPoints * 0.10)).intValue();
		int strength = Double.valueOf(Math.floor(maxPoints * 0.33)).intValue();
		int speed = Double.valueOf(Math.floor(maxPoints * 0.30)).intValue();
		int range = Double.valueOf(Math.floor(maxPoints * 0.13)).intValue();
		
		int sum = health + defense + strength + speed + range;

		if (sum < maxPoints) {
			strength = strength + (sum - maxPoints);
		}
		
		Warrior berserker = null;
		try {
			berserker = new Berserker("Berserker", health, defense, strength, speed, range);
		} catch (RuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return berserker;
	}

	public Warrior calculateRiderAtributes() {
		maxPoints = ConfigurationManager.getInstance().getMaxPointsPerWarrior();
		
		int health = Double.valueOf(Math.floor(maxPoints * 0.13)).intValue();
		int defense = Double.valueOf(Math.floor(maxPoints * 0.25)).intValue();
		int strength = Double.valueOf(Math.floor(maxPoints * 0.18)).intValue();
		int speed = Double.valueOf(Math.floor(maxPoints * 0.30)).intValue();
		int range = Double.valueOf(Math.floor(maxPoints * 0.13)).intValue();
		
		int sum = health + defense + strength + speed + range;

		if (sum < maxPoints) {
			defense = defense + (sum - maxPoints);
		}
		
		Warrior saber = null;
		try {
			saber = new Rider("Rider", health, defense, strength, speed, range);
		} catch (RuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return saber;
	}

	public Warrior calculateSaberAtributes() {
		maxPoints = ConfigurationManager.getInstance().getMaxPointsPerWarrior();
		
		int health = Double.valueOf(Math.floor(maxPoints * 0.18)).intValue();
		int defense = Double.valueOf(Math.floor(maxPoints * 0.18)).intValue();
		int strength = Double.valueOf(Math.floor(maxPoints * 0.25)).intValue();
		int speed = Double.valueOf(Math.floor(maxPoints * 0.20)).intValue();
		int range = Double.valueOf(Math.floor(maxPoints * 0.15)).intValue();
		
		int sum = health + defense + strength + speed + range;

		if (sum < maxPoints) {
			defense = defense + (sum - maxPoints);
		}
		
		Warrior saber = null;
		try {
			saber = new Saber("Saber", health, defense, strength, speed, range);
		} catch (RuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return saber;
	}
	
	public Warrior calculateLancerAtributes() {
		maxPoints = ConfigurationManager.getInstance().getMaxPointsPerWarrior();
		
		int health = Double.valueOf(Math.floor(maxPoints * 0.25)).intValue();
		int defense = Double.valueOf(Math.floor(maxPoints * 0.22)).intValue();
		int strength = Double.valueOf(Math.floor(maxPoints * 0.17)).intValue();
		int speed = Double.valueOf(Math.floor(maxPoints * 0.20)).intValue();
		int range = Double.valueOf(Math.floor(maxPoints * 0.12)).intValue();
		
		int sum = health + defense + strength + speed + range;

		if (sum < maxPoints) {
			defense = defense + (sum - maxPoints);
		}
		
		Warrior lancer = null;
		try {
			lancer = new Lancer("Lancer", health, defense, strength, speed, range);
		} catch (RuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lancer;
	}
}
