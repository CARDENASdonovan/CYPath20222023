package testQuadriageLogique;

import java.util.ArrayList;
import java.util.HashMap;

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
}
