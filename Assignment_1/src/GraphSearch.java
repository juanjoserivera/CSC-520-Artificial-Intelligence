import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Set;


/**
 * @author Juan Jose Rivera
 *
 */
public class GraphSearch {
	public static final int ASTAR = 1;
	public static final int GREEDY = 2;
	public static final int DYNAMIC = 3;

	public GraphSearch() {

	}

	public Solution graphSearch(int searchStrategy, Graph graph, String src, String dest) {
		Solution solutionObj = null;

		Path solutionPath = null;
		Path currentPath = null;

		Util util = new Util();
		graph = util.loadGraph();
		Vertex vertex = null;

		// initialize frontier as priority queue with initial state
		PriorityQueue<Path> frontier = new PriorityQueue<Path>();
		Path path = new Path(src);
		frontier.add(path);

		// successors list and explored set
		List<Edge> listSuccessor = null;
		Set<String> explored = new HashSet<String>();

		double cost;
		Edge edgeSuccessor;
		Path successorPath;

		while (!frontier.isEmpty()) {
			currentPath = null;
			// Remove highest priority from frontier

			currentPath = frontier.poll();

			// verify if current path == goal
			if (dest.equals(currentPath.getLastVertexName())) {

				solutionPath = currentPath;
				solutionObj = new Solution(solutionPath,explored,searchStrategy);
		
				break;
			}
			// Insert current path into explored
			explored.add(currentPath.getLastVertexName());

			// generate successors
			vertex = graph.getVertexByName(currentPath.getLastVertexName());
			listSuccessor = vertex.moveGenerator();

			cost = 0;
			edgeSuccessor = null;

			// for each successor
			if (!listSuccessor.isEmpty()) {
				ListIterator<Edge> it = listSuccessor.listIterator();
				while (it.hasNext()) {
					// generate cost
					successorPath = null;
					cost = 0;
					edgeSuccessor = it.next();
					cost = computeCost(searchStrategy, graph, currentPath, edgeSuccessor, dest);
					// update parent
					successorPath = currentPath.updateLastVertex(currentPath, edgeSuccessor);
					successorPath.setCost(cost);

					// if successor is not in frontier and successor is not in explored, then insert
					// successor in frontier
					if (!frontier.contains(successorPath) && !explored.contains(successorPath.getLastVertexName())) {
						
						frontier.add(successorPath);
						
					} else if (frontier.contains(successorPath)) {
						// if successor exists in frontier
						Path existingPath = null;
						existingPath = util.getPathByName(frontier, successorPath.getLastVertexName());

						if (existingPath.getCost() > successorPath.getCost()) {
							// if existing cost is greater than new, remove existing and add new
							frontier.remove(existingPath);

							frontier.add(successorPath);

						}

					}

				}

			}

		}

		return solutionObj;
	}

	public double computeCost(int searchStrategy, Graph graph, Path path, Edge edge, String dest) {
		double result = 0;

		switch (searchStrategy) {

		case GraphSearch.ASTAR:
			result = computeAstarCost(graph, path, edge, dest);
			break;

		case GraphSearch.DYNAMIC:
			result = computeDynamicCost(graph, path, edge);

			break;

		case GraphSearch.GREEDY:
			result = computeGreedyCost(graph, path, edge, dest);

			break;

		default:
			break;

		}

		return result;
	}

	private double computeGreedyCost(Graph graph, Path path, Edge edge, String dest) {
		Vertex vertexEdge = graph.getVertexByName(edge.getDest());
		Vertex vertexDest = graph.getVertexByName(dest);

		return getDistanceBetweenVertex(vertexEdge, vertexDest);

	}

	private double getTotalDistance(Path path, Edge edge) {

		double cost = 0;

		cost = path.getDistance() + edge.getWeight();

		return cost;

	}

	private double computeDynamicCost(Graph graph, Path path, Edge edge) {
		double cost = 0;

		cost = getTotalDistance(path, edge);

		return cost;
	}

	private double computeAstarCost(Graph graph, Path path, Edge edge, String dest) {
		double cost = 0;
		double dynamic = 0;
		double greedy = 0;

		dynamic = computeDynamicCost(graph, path, edge);
		greedy = computeGreedyCost(graph, path, edge, dest);

		cost = dynamic + greedy;

		return cost;
	}

	public double getDistanceBetweenVertex(Vertex vertSrc, Vertex vertDest) {
		// sqrt((69.5 * (Lat1 - Lat2)) ^ 2 + (69.5 * cos((Lat1 + Lat2)/360 * pi) *
		// (Long1 - Long2)) ^ 2)
		double result = 0;
		result = Math.sqrt(Math.pow(((69.5) * (vertDest.getLatitude() - vertSrc.getLatitude())), 2)
				+ Math.pow((((69.5) * Math.cos(((vertDest.getLatitude() + vertSrc.getLatitude()) / 360) * Math.PI))
						* (vertDest.getLongitude() - vertSrc.getLongitude())), 2));
		return result;

	}

}
