package gui.motions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TreeUtils {
	private static final int maxTreeDepth = 128; 
	public static CategoryTreeNode loadCategoryHierarchy(List<Map<String, Object>> queryNodes, String parentColumnName, String userObjectColumnName, String idColumnName) {
		parentColumnName = parentColumnName.toUpperCase(); userObjectColumnName = userObjectColumnName.toUpperCase(); idColumnName = idColumnName.toUpperCase();
		CategoryTreeNode root = new CategoryTreeNode();
		root.setAllowsChildren(true);
		HashMap<Map<String, Object>, CategoryTreeNode> previousLayer = new HashMap<Map<String, Object>, CategoryTreeNode>();  
		HashMap<Map<String, Object>, CategoryTreeNode> nextLayer = new HashMap<Map<String, Object>, CategoryTreeNode>();
		for(Map<String, Object> queryNode : queryNodes) {
			if(queryNode.get(parentColumnName) == null) {
				CategoryTreeNode node = new CategoryTreeNode(queryNode.get(userObjectColumnName));
				node.setID(queryNode.get(idColumnName));
				root.add(node);
				nextLayer.put(queryNode, node);
			}
		}
		queryNodes.removeAll(nextLayer.keySet());
		int currentDepth = 1;
		while(!queryNodes.isEmpty() && (currentDepth++ < maxTreeDepth)) {
			previousLayer = nextLayer;
			nextLayer = new HashMap<Map<String, Object>, CategoryTreeNode>();
			for(CategoryTreeNode parentNode : previousLayer.values()) {
				for(Map<String, Object> queryNode : queryNodes) {
					if(parentNode.getID().equals(queryNode.get(parentColumnName))) {
						CategoryTreeNode childNode = new CategoryTreeNode(queryNode.get(userObjectColumnName));
						childNode.setID(queryNode.get(idColumnName));
						nextLayer.put(queryNode, childNode);
						parentNode.add(childNode);
					}
				}
			}
			queryNodes.removeAll(nextLayer.keySet());
		}
 		return root;
	}
}
