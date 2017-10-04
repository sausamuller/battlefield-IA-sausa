package com.ia.pathfinder;

import ia.battle.core.BattleField;
import ia.battle.core.FieldCell;
import ia.battle.core.FieldCellType;

import java.util.ArrayList;
import java.util.Collections;

public class PathFinder {
	
	private ArrayList<Node> nodes;
	private ArrayList<Node> closedNodes, openedNodes;
	private Node origin, destination;
	
	private static PathFinder instance;
	
	private boolean activateSpecialItems;

	public PathFinder(){
		
	}
	
	public PathFinder activateSpecialItems(){
		activateSpecialItems = true;
		return this;
	}
	

	public static final PathFinder getInstance() {
		if(instance == null){
			instance = new PathFinder();
		}
		return instance;
	}

	/**
	 * 
	 * @param from FieldCell actual del warrior
	 * @param to FieldCell destino
	 * @return lista de FieldCell a recorrer
	 */
	public ArrayList<FieldCell> findPath(FieldCell from, FieldCell to) {
		try{
			nodes = new ArrayList<Node>();
			closedNodes = new ArrayList<Node>();
			openedNodes = new ArrayList<Node>();

			origin = new Node(from);
			destination = new Node(to);

			Node currentNode = origin;
			while ( !currentNode.equals(destination) ){
				processNode(currentNode);
				currentNode = getMinFValueNode();
			}
			return retrievePath();
			
		}catch(IndexOutOfBoundsException exp){
			//TODO Revisar cuando pasa porque.
		}
		
		return new ArrayList<FieldCell>();
	}

	private ArrayList<FieldCell> retrievePath(){
		ArrayList<FieldCell> path = new ArrayList<FieldCell>();
		Node node = destination;
		
		while(!origin.equals(node)){
			path.add(node.getCell());
			node = node.getParent();
		}
		
		Collections.reverse(path);
		
		return path;
	}

	private void processNode(Node node){
		
		ArrayList<FieldCell> adj = (ArrayList<FieldCell>)BattleField.getInstance().getAdjacentCells(node.getCell());
		
		ArrayList<Node> adjacentNodes = new ArrayList<Node>();
		for (FieldCell fieldCell : adj) {
			adjacentNodes.add(new Node(fieldCell));
		}

		openedNodes.remove(node);
		for (Node node2 : adjacentNodes) {
			if (node2.getCell().getCost() > 1.1f) {
				System.out.println("Entro en este punto cuando no deb�a");
				closedNodes.add(node2);
			}
		}
		closedNodes.add(node);

		for (Node n : adjacentNodes) {
			
			if (closedNodes.contains(n) || n.getCell().getFieldCellType().equals(FieldCellType.BLOCKED))
				continue;

			//Compute the Manhattan distance from node 'n' to destination
			int h = Math.abs(destination.getX() - n.getX());
			h += Math.abs(destination.getY() - n.getY());

//			Compute the distance from origin to node 'n' 
			int g = node.getG();
			if (node.getX() == n.getX() || node.getY() == n.getY())
				g += 1; //Recta
			else
				g += 1.41; //Diagonal

			if (!openedNodes.contains(n)) {
				if(n.equals(destination)){
					destination.setParent(node);
					destination.setH(h);
					destination.setG(g);
				}else{
					n.setParent(node);
					n.setH(h);
					n.setG(g);	
				}
				

				openedNodes.add(n);
			} else {

				if (h + g < n.getF()) {

					n.setParent(node);
					n.setH(h);
					n.setG(g);
				}
			}
		}
	}

	private Node getMinFValueNode() throws IndexOutOfBoundsException{
		Node node = openedNodes.get(0);

		for (Node n : openedNodes){
			
			//Retorna el nodo si tiene un regalo.
			if(activateSpecialItems && BattleField.getInstance().getSpecialItems().contains(n.getCell())){
				return n;
			}
		
			if (n.getF() < node.getF()){
				node = n;
			}
		}
		return node;
	}
}
