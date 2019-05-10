
/**
 * @author Juan Jose Rivera
 *
 */
public class SearchUSA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean paramError = false;
		Util util = new Util();
		Graph graph = util.loadGraph();
		String src = "";
		String dest = "";
		int searchStrategy = 0;
		Solution solutionObj = null;
		
		try {
			// getting arguments from command line
			searchStrategy = util.getSearchStrategy(args[0]);
			src = args[1];
			dest = args[2];
		} catch (Exception e) {
			System.out.println("Program usage: java SearchUSA searchType srccityname destcityname\n"
					+ "searchType values: {astar, greedy, dynamic}\n"
					+ "srccityname,destcityname: {name of cities in map} ");
			paramError = true;
		}

		if (!paramError) {
			GraphSearch graphSearch = new GraphSearch();

			solutionObj = graphSearch.graphSearch(searchStrategy, graph, src, dest);
			
			System.out.println(solutionObj);

		}
	}

}
