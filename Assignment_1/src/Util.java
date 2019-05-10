
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * @author Juan Jose Rivera
 *
 */
public class Util {

	public static final int IS_VERTEX = 1;
	public static final int IS_EDGE = 2;
	public static final int IS_BLANK = 0;
	public static final int IS_UNIDENTFIED = -1;
	public static final String DELIMITER = ",";
	public static final boolean DIGRAPH = true;
	public static final int VERTEX_PATTERN_LENGHT = 3;
	public static final int EDGE_PATTERN_LENGHT = 3;
	public static final String GRAPH_FILE_NAME = "graph.txt";
	public static final String STR_ASTAR = "ASTAR";
	public static final String STR_DYNAMYC = "DYNAMIC";
	public static final String STR_GREEDY = "GREEDY";

	public Util() {

	}

	public int getPattern(String line) {

		if (isVertex(line)) {
			return Util.IS_VERTEX;

		} else if (isEdge(line)) {
			return Util.IS_EDGE;
		} else if (line.isEmpty()) {
			return Util.IS_BLANK;
		} else {
			return Util.IS_UNIDENTFIED;
		}
	}

	private boolean isVertex(String line) {
		boolean status = false;
		if (!line.isEmpty()) {
			String[] splitLine = line.split(Util.DELIMITER);
			if (splitLine.length == Util.VERTEX_PATTERN_LENGHT && isNumeric(splitLine[1]) && isNumeric(splitLine[2])) {
				status = true;
			}
		}
		return status;
	}

	private boolean isEdge(String line) {
		boolean status = false;
		if (!line.isEmpty()) {
			String[] splitLine = line.split(Util.DELIMITER);
			if (splitLine.length == Util.EDGE_PATTERN_LENGHT && !isNumeric(splitLine[1]) && isNumeric(splitLine[2])) {
				status = true;
			}
		}
		return status;

	}

	private boolean isNumeric(String str) {
		boolean status = false;
		try {
			Double.parseDouble(str);
			status = true;

		} catch (NumberFormatException e) {
			status = false;
		}

		return status;
	}

	public Vertex getVertexfromLine(String line) {
		// TODO Auto-generated method stub
		String[] attrVertex;
		Vertex vertex;
		attrVertex = line.split(Util.DELIMITER);
		vertex = new Vertex(attrVertex[0].trim(), Double.parseDouble(attrVertex[1]), Double.parseDouble(attrVertex[2]));

		return vertex;
	}

	public Edge getEdgefromLine(String line) {
		// TODO Auto-generated method stub
		String[] attrEdge;
		Edge edge;
		attrEdge = line.split(Util.DELIMITER);
		edge = new Edge(attrEdge[0].trim(), attrEdge[1].trim(), Double.parseDouble(attrEdge[2]));

		return edge;
	}

	public Graph loadGraph() {
		String line = "";
		Util fileUtil = new Util();
		BufferedReader br =null;
		Vertex vertex;
		Vertex vertexSrc;
		Vertex vertexDest;

		Edge edgeSrc;
		Edge edgeDest;
		Graph graph = new Graph();
		try {
			
			
		    InputStream is = getClass().getResourceAsStream(Util.GRAPH_FILE_NAME);
		    InputStreamReader isr = new InputStreamReader(is);
		    br = new BufferedReader(isr);
			
			while ((line = br.readLine()) != null) {

				switch (fileUtil.getPattern(line)) {

				case Util.IS_VERTEX:
					vertex = fileUtil.getVertexfromLine(line);
					graph.addVertex(vertex);
					graph.setNumVertex(graph.getNumVertex() + 1);
					break;

				case Util.IS_EDGE: // all Vertex should be already created
					edgeSrc = fileUtil.getEdgefromLine(line);

					// from Vertex to adjacent
					vertexSrc = graph.getVertexByName(edgeSrc.getSrc());
					vertexSrc.addEdge(edgeSrc);
					vertexSrc.setNumEdge(vertexSrc.getNumEdge() + 1);

					// from adjacent to Vertex (DiGraph)
					if (Util.DIGRAPH) {
						vertexDest = graph.getVertexByName(edgeSrc.getDest());
						// create edge
						edgeDest = new Edge(edgeSrc.getDest(), edgeSrc.getSrc(), edgeSrc.getWeight());
						vertexDest.addEdge(edgeDest);
						vertexDest.setNumEdge(vertexDest.getNumEdge() + 1);

					}

					break;
				case Util.IS_BLANK:
					break;
				case Util.IS_UNIDENTFIED:
					break;
				default:
					break;
				}

			}
			return graph;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;

	}

	public Path getPathByName(PriorityQueue<Path> prioQueue, String name) {
		Iterator<Path> it = prioQueue.iterator();
		Path path = null;
		boolean found = false;
		while (!found && it.hasNext()) {
			path = it.next();
			if (path.getLastVertexName().equals(name)) {
				return path;
			}

		}

		return null;
	}

	public int getSearchStrategy(String strStrategy) {
		// TODO Auto-generated method stub

		if (strStrategy != null) {
			if (strStrategy.trim().toUpperCase().equals(Util.STR_DYNAMYC)) {
				return GraphSearch.DYNAMIC;

			} else if (strStrategy.trim().toUpperCase().equals(Util.STR_GREEDY)) {
				return GraphSearch.GREEDY;

			}else if (strStrategy.trim().toUpperCase().equals(Util.STR_ASTAR)) {
				return GraphSearch.ASTAR;

			}else {
				return -1;
			}
		}
		return -1;

	}

}
