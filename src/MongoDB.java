import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Projections.fields;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * Program to create a collection, insert JSON objects, and perform simple
 * queries on MongoDB.
 */
public class MongoDB {
	/**
	 * MongoDB database name
	 */
	public static final String DATABASE_NAME = "cosc516";

	/**
	 * MongoDB collection name
	 */
	public static final String COLLECTION_NAME = "data";

	/**
	 * Mongo client connection to server
	 */
	public MongoClient mongoClient;

	/**
	 * Mongo database
	 */
	public MongoDatabase db;

	/**
	 * Main method
	 * 
	 * @param args
	 *             no arguments required
	 */
	public static void main(String[] args) throws Exception {
		MongoDB qmongo = new MongoDB();
		qmongo.connect();
		qmongo.load();
		qmongo.loadNest();
		System.out.println(qmongo.query1(1000));
		System.out.println(qmongo.query2(32));
		System.out.println(qmongo.query2Nest(32));
		System.out.println(qmongo.query3());
		System.out.println(qmongo.query3Nest());
		System.out.println(MongoDB.toString(qmongo.query4()));
		System.out.println(MongoDB.toString(qmongo.query4Nest()));
	}

	/**
	 * Connects to Mongo database and returns database object to manipulate for
	 * connection.
	 * 
	 * @return
	 *         Mongo database
	 */
	public MongoDatabase connect() {
		try {
			// Provide connection information to MongoDB server
			// TODO: Replace with your cluster info
			String url = "";
			mongoClient = MongoClients.create(url);
		} catch (Exception ex) {
			System.out.println("Exception: " + ex);
			ex.printStackTrace();
		}

		// Provide database information to connect to
		// Note: If the database does not already exist, it will be created
		// automatically.
		db = mongoClient.getDatabase(DATABASE_NAME);
		return db;
	}

	/**
	 * Loads TPC-H data into MongoDB.
	 * 
	 * @throws Exception
	 *                   if a file I/O or database error occurs
	 */
	public void load() throws Exception {
		// TODO: Load customer and orders data
	}

	/**
	 * Loads customer and orders TPC-H data into a single collection.
	 * 
	 * @throws Exception
	 *                   if a file I/O or database error occurs
	 */
	public void loadNest() throws Exception {
		// TODO: Load customer and orders data into single collection called custorders
		// TODO: Consider using insertMany() for bulk insert for faster performance
	}

	/**
	 * Performs a MongoDB query that prints out all data (except for the _id).
	 */
	public String query1(int custkey) {
		System.out.println("\nExecuting query 1:");
		// TODO: Write query
		MongoCollection<Document> col = db.getCollection("customer");

		// See: https://docs.mongodb.com/drivers/java/sync/current/usage-examples/find/

		return null;
	}

	/**
	 * Performs a MongoDB query that returns order date for a given order id using
	 * the orders collection.
	 */
	public String query2(int orderId) {
		// TODO: Write a MongoDB query
		System.out.println("\nExecuting query 2:");

		return null;
	}

	/**
	 * Performs a MongoDB query that returns order date for a given order id using
	 * the custorders collection.
	 */
	public String query2Nest(int orderId) {
		// TODO: Write a MongoDB query
		System.out.println("\nExecuting query 2 nested:");

		MongoCollection<Document> col = db.getCollection("custorders");

		return null;
	}

	/**
	 * Performs a MongoDB query that returns the total number of orders using the
	 * orders collection.
	 */
	public long query3() {
		// TODO: Write a MongoDB query
		System.out.println("\nExecuting query 3:");

		MongoCollection<Document> col = db.getCollection("orders");
		return 0;
	}

	/**
	 * Performs a MongoDB query that returns the total number of orders using the
	 * custorders collection.
	 */
	public long query3Nest() {
		// TODO: Write a MongoDB query
		System.out.println("\nExecuting query 3 nested:");
		MongoCollection<Document> col = db.getCollection("custorders");

		return 0;
	}

	/**
	 * Performs a MongoDB query that returns the top 5 customers based on total
	 * order amount using the customer and orders collections.
	 */
	public MongoCursor<Document> query4() {
		// TODO: Write a MongoDB query. Note: Return an iterator.
		System.out.println("\nExecuting query 4:");

		return null;
	}

	/**
	 * Performs a MongoDB query that returns the top 5 customers based on total
	 * order amount using the custorders collection.
	 */
	public MongoCursor<Document> query4Nest() {
		// TODO: Write a MongoDB query. Note: Return an iterator.
		System.out.println("\nExecuting query 4 nested:");

		MongoCollection<Document> col = db.getCollection("custorders");

		return null;
	}

	/**
	 * Returns the Mongo database being used.
	 * 
	 * @return
	 *         Mongo database
	 */
	public MongoDatabase getDb() {
		return db;
	}

	/**
	 * Outputs a cursor of MongoDB results in string form.
	 * 
	 * @param cursor
	 *               Mongo cursor
	 * @return
	 *         results as a string
	 */
	public static String toString(MongoCursor<Document> cursor) {
		StringBuilder buf = new StringBuilder();
		int count = 0;
		buf.append("Rows:\n");
		if (cursor != null) {
			while (cursor.hasNext()) {
				Document obj = cursor.next();
				buf.append(obj.toJson());
				buf.append("\n");
				count++;
			}
			cursor.close();
		}
		buf.append("Number of rows: " + count);
		return buf.toString();
	}
}
