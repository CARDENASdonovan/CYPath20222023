package testQuadriageLogique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class Adj {
	//private HashMap<String, ArrayList<String>> listeAdj;
	private HashMap<String, ArrayList<String>> listeAdj;
	public Adj() {
		 //this.listeAdj = new HashMap<String, ArrayList<String>>();
		this.listeAdj = new HashMap<String, ArrayList<String>>();
	}
	
	// Getters
	protected HashMap<String, ArrayList<String>> getAdjacencyList(){
		return this.listeAdj;
		
	}
	
	// Methods
	
	// Two vertices are linked by an edge.
	// We create here a non oriented graph.
	protected boolean addEdge(String idEdge1, String idEdge2) {
		HashMap<String, ArrayList<String>> a = getAdjacencyList();
		if(a.containsKey(idEdge1)) {
			//a.get(idEdge1).add(idEdge2);
		
			if(a.containsKey(idEdge2)) {
				a.get(idEdge1).add(idEdge2);
				a.get(idEdge2).add(idEdge1);
				
				System.out.println(listeAdj);
				return(true);
			}
	
			else {
				a.get(idEdge1).add(idEdge2);
				
				ArrayList<String> b = new ArrayList<>();
				a.put(idEdge2, b);
				a.get(idEdge2).add(idEdge1);
		
				System.out.println(listeAdj);
				return(true);
			}
		}
		
		else if(a.containsKey(idEdge2)) {
			//a.get(idEdge1).add(idEdge2);
		
			if(a.containsKey(idEdge1)) {
				a.get(idEdge1).add(idEdge2);
				a.get(idEdge2).add(idEdge1);
				
				System.out.println(listeAdj);
				return(true);
			}
	
			else {
				a.get(idEdge2).add(idEdge1);
				
				ArrayList<String> b = new ArrayList<>();
				a.put(idEdge1, b);
				a.get(idEdge1).add(idEdge2);
		
				System.out.println(listeAdj);
				return(true);
			}
		}
		
		// Case: idEdge1 and idEdge2 don't exist in the adjacency list.
		else {
			ArrayList<String> b = new ArrayList<>();
			ArrayList<String> c = new ArrayList<>();
			
			a.put(idEdge1, b);
			System.out.println("Création de idEdge1: " + idEdge1 + b);
			
			a.put(idEdge2, c);
			System.out.println("Création de idEdge2: " + idEdge2 + c);
			
			a.get(idEdge1).add(idEdge2);
			a.get(idEdge2).add(idEdge1);
			
			System.out.println("Adjacency updated" + listeAdj);
			System.out.println("\n");
			return(true);
		}
	}
	
	protected void dfs(Adj adjacencyList) {
		String startVertex = "0";
		
		Stack<String> stack = new Stack<>();
		
		LinkedList<String> visited = new LinkedList<>();
		visited.add(startVertex);
		
		LinkedList<String> notVisited = new LinkedList<>();

		ArrayList<String> vertexNeighborsNotVisited = new ArrayList<>() ;
		
		ArrayList<String> vertexNeighbors = adjacencyList.getAdjacencyList().get(startVertex);
		System.out.println("vertexNeighbors: " + vertexNeighbors);
		
		for(String vertexName : vertexNeighbors) {
			if(!visited.contains(vertexName) && !stack.contains(vertexName)) {
				notVisited.add(vertexName);
			}
		}
		System.out.println("\n");
		System.out.println("visited START: " + visited);
		System.out.println("stack START: " + stack);
		stack.addAll(notVisited);
	
		
		while(stack.size() > 0) {
			System.out.println("\n");
			System.out.println("DFS Start");
			
			String stackFirstElement = stack.get(0);
			vertexNeighborsNotVisited.clear();
			
			if(!visited.contains(stackFirstElement)) {
				visited.add(stackFirstElement);
				System.out.println("visited: " + visited);
				vertexNeighbors = adjacencyList.getAdjacencyList().get(stackFirstElement);
				System.out.println("vertexNeighbors: " + vertexNeighbors);
				
				for(String vertex : vertexNeighbors) {
					if(!visited.contains(vertex) && !stack.contains(vertex)) {
						vertexNeighborsNotVisited.add(vertex);
					}		
				}
				System.out.println("vertexNeighborsNotVisited: " + vertexNeighborsNotVisited);
				stack.addAll(vertexNeighborsNotVisited);
			}		
			stack.remove(0);
			System.out.println("visited FINAL: " + visited);
			System.out.println("stack FINAL: " + stack);			
		}
	}
}
