import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author Juan Jose Rivera
 *
 */
public class Path implements Comparable<Path> {
	private double distance;
	private int numNodes;
	private double cost;
	private String lastVertexName;
	private LinkedList<String> listPath;

	public Path() {
		this.cost = 0;
		this.distance = 0;
		this.numNodes = 0;

	}

	//for start node
	public Path(String lastVertexName) {
		this.cost = 0;
		this.distance = 0;
		this.numNodes = 1;
		this.listPath = new LinkedList<String>();
		this.lastVertexName = lastVertexName;
		this.listPath.addLast(lastVertexName);

	}

	public Path updateLastVertex(Path existingPath, Edge edge) {
		Path path = new Path();
		//set total distance
		path.setDistance(existingPath.getTotalDistance(edge));
		//add num node
		path.setNumNodes(existingPath.getNumNodes()+1);
		//update new node
		path.setLastVertexName(edge.getDest());
		//add total path
		path.setListPath(new LinkedList<String>(existingPath.getListPath()));
		path.getListPath().addLast(edge.getDest());

		return path;
	}

	//distance path + edge (successor)
	public double getTotalDistance(Edge edge) {
		return this.getDistance()+edge.getWeight();
	}
	public String toString() {
		return this.lastVertexName+" : "+this.cost;
	}
	public String printPath() {
		String strPath = "[";
		boolean firstElement = true;
		ListIterator<String> it = this.listPath.listIterator();
	

		while (it.hasNext()) {
			if (firstElement) {
				strPath = strPath + it.next();
				firstElement = false;
			} else {
				strPath = strPath + ", " + it.next();

			}
		}
		return strPath+"]";
	}

	
	
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getNumNodes() {
		return numNodes;
	}

	public void setNumNodes(int numNodes) {
		this.numNodes = numNodes;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getLastVertexName() {
		return lastVertexName;
	}

	public void setLastVertexName(String lastVertexName) {
		this.lastVertexName = lastVertexName;
	}

	public LinkedList<String> getListPath() {
		return listPath;
	}

	public void setListPath(LinkedList<String> listPath) {
		this.listPath = listPath;
	}

	// to order elements by cost within priorityqueue
	public int compareTo(Path path) {
		// TODO Auto-generated method stub
		int result = -2;
		if (this.cost == path.getCost()) {
			result = 0;
		} else if (this.cost > path.getCost()) {
			result = 1;
		} else {
			result = -1;

		}
		return result;
	}

	// to get path by name within priorityqueue
	public boolean equals(Object o) {
		return this.lastVertexName.equals(((Path) o).getLastVertexName());
	}

}
