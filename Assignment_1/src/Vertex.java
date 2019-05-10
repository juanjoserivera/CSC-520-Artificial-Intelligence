import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Vertex {
	private String name;
	private double latitude;
	private double longitude;
	private int numEdge;
	private LinkedList<Edge> listEdge;

	public Vertex(String name, double latitude, double longitude) {
		this.name = name;
		this.latitude=latitude;
		this.longitude=longitude;
		this.numEdge=0;
		this.listEdge = new LinkedList<Edge>();
	
	}
	
	public String toString() {
		String strVertex="";
		ListIterator<Edge> it =this.listEdge.listIterator();
		strVertex="City: "+this.name+" ( "+"lat:"+this.latitude+", lon:"+this.longitude+" )"+",numEdge:"+this.numEdge+"\n";
		
		while(it.hasNext()) {
			strVertex = strVertex + "\t"+it.next()+"\n";
		}
		
		return strVertex;
	}
	
	
	public int getNumEdge() {
		return numEdge;
	}



	public void setNumEdge(int numEdge) {
		this.numEdge = numEdge;
	}



	public boolean addEdge(Edge edge) {
		boolean status = false;
		
		status = this.listEdge.add(edge);
		
		return status;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public double getLatitude() {
		return latitude;
	}



	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}



	public double getLongitude() {
		return longitude;
	}



	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}



	public LinkedList<Edge> getListEdge() {
		return listEdge;
	}



	public void setListEdge(LinkedList<Edge> listEdge) {
		this.listEdge = listEdge;
	}

	public List<Edge> moveGenerator() {
		//this should be modified if there is an specific logic to generate successor besides than provides the adjList from vertex
		List<Edge> listSuccessor = this.listEdge;
		return listSuccessor;
		
	}



	
}



