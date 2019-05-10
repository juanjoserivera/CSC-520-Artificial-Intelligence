import java.util.HashMap;
import java.util.Map;

/**
 * @author Juan Jose Rivera
 *
 */
public class Graph {
	private int numVertex;
	private Map<String,Vertex> mapVertex;

	public Graph() {
		this.numVertex=0;

		this.mapVertex = new HashMap<String,Vertex>();
	}

	public boolean addVertex(Vertex vertex) {
		boolean status = false;

		this.mapVertex.put(vertex.getName(), vertex);

		return status;

	}

	public Vertex getVertexByName(String name) {
		if (this.mapVertex.containsKey(name)) {
			return this.mapVertex.get(name);
		}
		return null;
	}

	public String toString() {
		String strGraph = "";
		strGraph = "Graph, numVertex: "+this.numVertex+"\n";
		for (String name:this.mapVertex.keySet()) {
			strGraph = strGraph+this.mapVertex.get(name)+"\n";
		}
		
		return strGraph;
	}

	public int getNumVertex() {
		return numVertex;
	}

	public void setNumVertex(int numVertex) {
		this.numVertex = numVertex;
	}

	public Map<String,Vertex> getMapVertex() {
		return mapVertex;
	}

	public void setMapVertex(Map<String,Vertex> listVertex) {
		this.mapVertex = listVertex;
	}







}
