package katherine.gotovsky;

public class Cell {

	String value = "";
	boolean visited = false;
	
	public Cell(String val){
		value = val;
		visited = false;
	}
	
	public void setVisited(){
		visited=true;
	}
	
	public void unVisit(){
		visited=false;
	}
	
	public boolean wasVisited(){
		if (visited) {
			return true;
		}
		else {
			return false;
		}
	}
	public String toString(){
		return value;
	}
	
}
