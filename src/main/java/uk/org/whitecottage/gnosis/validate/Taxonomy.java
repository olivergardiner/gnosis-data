package uk.org.whitecottage.gnosis.validate;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;

public class Taxonomy {
	protected List<TaxonomyNode> taxonomyNodes;
	
	public Taxonomy(FindIterable<Document> taxonomy) {
		taxonomyNodes = new ArrayList<>();

		taxonomy.forEach((final Document document) -> {
			taxonomyNodes.add(new TaxonomyNode(document));
		});
	}

	public boolean isClassifier(String id) {
		for (TaxonomyNode taxonomyNode: taxonomyNodes) {
			if (taxonomyNode.isClassifier(id)) {
				return true;
			}
		}
		
		return false;
	}
}
