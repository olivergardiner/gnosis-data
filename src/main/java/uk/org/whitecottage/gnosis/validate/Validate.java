package uk.org.whitecottage.gnosis.validate;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class Validate {

	private static final String DB = "gnosis";
	private static final String APPLICATIONS = "applications";
	private static final String APPLICATION_TAXONOMY = "application-taxonomy";
	
	private Validate() {		
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase(DB);
		
    	Taxonomy applicationTaxonomy = new Taxonomy(db.getCollection(APPLICATION_TAXONOMY).find());

    	MongoCollection<Document> applications = db.getCollection(APPLICATIONS);
    	FindIterable<Document> result = applications.find();
		result.forEach((final Document application) -> {
			
	    	List<String> classifications = (List<String>) application.get("logical-apps");
			List<String> newClassifications = new ArrayList<>();
	    	
	    	if (classifications != null) {
	    		for (String logicalApplication: classifications) {
					if (applicationTaxonomy.isClassifier(logicalApplication)) {
						newClassifications.add(logicalApplication);
					}
	    		}
	    	}
	    	
	    	Document updateOperation = new Document("$set", new Document("logical-apps", newClassifications));
	    	applications.updateOne(eq("_id", application.get("_id")), updateOperation);
		});
    	
    	mongoClient.close();
	}
}
