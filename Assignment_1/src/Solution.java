import java.util.HashSet;
import java.util.Set;

/**
 * @author Juan Jose Rivera
 *
 */
public class Solution {

	private Set<String> explored ;
	private int numNodesExplored;
	private Path path;
	private int searchStrategy;
	
	public Solution() {
		
	}
	
	public Solution(Path path, Set<String> explored, int searchStrategy) {
		this.path=path;
		this.explored=new HashSet<String>(explored);
		this.numNodesExplored = this.explored.size();
		this.searchStrategy=searchStrategy;
		
	}
	
	public String toString() {
		String result="";
		result = result+"Expanded nodes\t->"+this.explored+"\n" ;
		result = result+"#expanded\t->"+this.numNodesExplored+"\n";
		result = result+"Solution Path\t->"+this.path.printPath()+"\n";
		result = result+"#path\t\t->"+this.path.getNumNodes()+"\n";
		result = result+"Total distance\t->"+this.path.getDistance()+"\n";
				
		
		return result;
	}
	
	public int getSearchStrategy() {
		return searchStrategy;
	}


	public void setSearchStrategy(int searchStrategy) {
		this.searchStrategy = searchStrategy;
	}


	public Set<String> getExplored() {
		return explored;
	}
	public void setExplored(Set<String> explored) {
		this.explored = explored;
	}
	public int getNumNodesExplored() {
		return numNodesExplored;
	}
	public void setNumNodesExplored(int numNodesExplored) {
		this.numNodesExplored = numNodesExplored;
	}
	public Path getPath() {
		return path;
	}
	public void setPath(Path path) {
		this.path = path;
	}
	


	
}
