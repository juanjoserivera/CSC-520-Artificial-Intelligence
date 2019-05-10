/**
 * @author Juan Jose Rivera
 *
 */
public class Edge {
	
	private String src;
	private String dest;
	private double weight;
	
	public Edge (String src, String dest, double weight) {
		this.src = src;
		this.dest = dest;
		this.weight = weight;
		
	}
	public String toString() {
		String strEdge = "";
		
		strEdge = "< "+this.src +" -> "+this.dest +" >, "+ this.weight;
		
		return strEdge;
	}
	
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	

}
