package uk.org.whitecottage.gnosis.validate;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class TaxonomyNode {
	protected String nodeId;
	protected String name;
	protected String description;
	protected List<TaxonomyNode> children;

	@SuppressWarnings("unchecked")
	public TaxonomyNode(Document node) {
		nodeId = (String) node.get("nodeId");
		name = (String) node.get("name");
		description = (String) node.get("description");

		children = new ArrayList<>();
		for (Document child: (Iterable<Document>) node.get("children")) {
			children.add(new TaxonomyNode(child));
		}
	}
	
	public boolean isClassifier(String id) {
		if (children.isEmpty()) {
			return (id.equals(nodeId));
		} else {
			for (TaxonomyNode child: children) {
				if (child.isClassifier(id)) {
					return true;
				}
			}
		}
		
		return false;
	}

	public boolean isLeafNode() {
		return children.isEmpty();
	}

	public String getNodeId() {
		return nodeId;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<TaxonomyNode> getChildren() {
		return children;
	}
}
