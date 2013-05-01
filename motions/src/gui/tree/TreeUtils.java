package gui.tree;


import gui.tree.builder.BaseTreeNodeBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TreeUtils {
	private static final int maxTreeDepth = 128; 
	public static CategoryTreeNode loadCategoryHierarchy(CategoryTreeNode root, List<Map<String, Object>> queryNodes, String parentColumnName, String idColumnName, BaseTreeNodeBuilder builder) {
		parentColumnName = parentColumnName.toUpperCase(); idColumnName = idColumnName.toUpperCase();
		HashMap<Map<String, Object>, BaseTreeNode> previousLayer = new HashMap<Map<String, Object>, BaseTreeNode>();  
		HashMap<Map<String, Object>, BaseTreeNode> nextLayer = new HashMap<Map<String, Object>, BaseTreeNode>();
		for(Map<String, Object> queryNode : queryNodes) {
			if(queryNode.get(parentColumnName) == null) {
				BaseTreeNode node = builder.buildNode(queryNode);
				root.add(node);
				nextLayer.put(queryNode, node);
			}
		}
		queryNodes.removeAll(nextLayer.keySet());
		int currentDepth = 1;
		while(!queryNodes.isEmpty() && (currentDepth++ < maxTreeDepth)) {
			previousLayer = nextLayer;
			nextLayer = new HashMap<Map<String, Object>, BaseTreeNode>();
			for(BaseTreeNode parentNode : previousLayer.values()) {
				for(Map<String, Object> queryNode : queryNodes) {
					if(parentNode.getId().equals(queryNode.get(parentColumnName))) {
						BaseTreeNode childNode = builder.buildNode(queryNode);
						nextLayer.put(queryNode, childNode);
						parentNode.add(childNode);
					}
				}
			}
			queryNodes.removeAll(nextLayer.keySet());
		}
 		return root;
	}
	
	public static CategoryTreeNode loadCategoryHierarchy(List<Map<String, Object>> queryNodes, String parentColumnName, String idColumnName, BaseTreeNodeBuilder builder) {
		return loadCategoryHierarchy(new CategoryTreeNode(), queryNodes, parentColumnName, idColumnName, builder);
	}
}
