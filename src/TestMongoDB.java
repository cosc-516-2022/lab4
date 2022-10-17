import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

import static org.junit.Assert.assertEquals;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;


/**
 * Tests Mongo queries and methods.
 */
public class TestMongoDB
{
	/**
	 * Class being tested
	 */
	private static MongoDB q;
			
	
	/**
	 * Requests a connection to the Mongo server.
	 * 
	 * @throws Exception
	 * 		if an error occurs
	 */
	@BeforeClass
	public static void init() throws Exception 
	{		
		q = new MongoDB();		
		q.connect();
	}		
		    
    /**
     * Tests load.
     */
    @Test
    public void testLoad() throws Exception
    {   
    	// Load data
    	q.load();
    	
    	// Perform query to get customer data
		MongoCollection<Document> col = q.db.getCollection("customer");		
				
		Bson filter = eq("c_custkey", 1500);
		MongoCursor<Document> cursor = col.find().filter(filter).projection(fields(include("c_custkey", "c_name", "c_acctbal", "c_comment"), excludeId())).iterator();

    	String result = MongoDB.toString(cursor);
    	System.out.println(result);

    	String answer = "Rows:\n"    	
						+"{\"c_custkey\": 1500, \"c_name\": \"Customer#000001500\", \"c_acctbal\": {\"$numberDecimal\": \"6910.79\"}, \"c_comment\": \"2x7Oh1NCN65xkiACRS1jOChj1L61B5g 7 Sl16l7Q5Qk56BhgNAn1ylRzR567LlOn6xhz4Q Pixk0063y4m\"}"				
    					+"\nNumber of rows: 1";    	
    	
    	System.out.println("Result after load customer :\n"+result);
    	assertEquals(answer, result);    	

		// Perform query to get orders data
		col = q.db.getCollection("orders");		
				
		filter = eq("o_orderkey", 60000);
		cursor = col.find().filter(filter).projection(fields(include("o_orderkey", "o_custkey", "o_totalprice", "o_clerk"), excludeId())).iterator();

    	result = MongoDB.toString(cursor);
    	System.out.println(result);

    	answer = "Rows:\n"    	
						+"{\"o_orderkey\": 60000, \"o_custkey\": 1444, \"o_totalprice\": {\"$numberDecimal\": \"270761.27\"}, \"o_clerk\": \"Clerk#000000194\"}"				
    					+"\nNumber of rows: 1";    	
    	
    	System.out.println("Result after load:\n"+result);
    	assertEquals(answer, result);    	
    }
    
	/**
     * Tests nested load.
     */
    @Test
    public void testLoadNest() throws Exception
    {   
    	// Load data in nested format
    	q.loadNest();
    	
    	// Perform query to get customer data
		MongoCollection<Document> col = q.db.getCollection("custorders");		
				
		Bson filter = eq("c_custkey", 10);
		MongoCursor<Document> cursor = col.find().filter(filter).projection(fields(include("c_custkey", "c_name", "c_acctbal", "orders"), excludeId())).iterator();

    	String result = MongoDB.toString(cursor);
    	System.out.println(result);

    	String answer = "Rows:\n"    	
    					+"{\"c_custkey\": 10, \"c_name\": \"Customer#000000010\", \"c_acctbal\": {\"$numberDecimal\": \"2753.54\"}, \"orders\": [{\"o_orderkey\": 1153, \"o_custkey\": 10, \"o_orderstatus\": \"O\", \"o_totalprice\": {\"$numberDecimal\": \"336184.20\"}, \"o_orderdate\": \"1996-04-18\", \"o_orderpriority\": \"5-LOW\", \"o_clerk\": \"Clerk#000000059\", \"o_shippriority\": 0, \"o_comment\": \" hCMxjkNLgmMBAw55iR4nOBmLB5MnAN4mxi0O3N0SjPCCmgx7nxN7BCB3LwMzn\"}, {\"o_orderkey\": 6374, \"o_custkey\": 10, \"o_orderstatus\": \"O\", \"o_totalprice\": {\"$numberDecimal\": \"176950.17\"}, \"o_orderdate\": \"1995-08-08\", \"o_orderpriority\": \"1-URGENT\", \"o_clerk\": \"Clerk#000000058\", \"o_shippriority\": 0, \"o_comment\": \"kB ORL3RANSzgnwB4RQOmz5 R33\"}, {\"o_orderkey\": 7427, \"o_custkey\": 10, \"o_orderstatus\": \"F\", \"o_totalprice\": {\"$numberDecimal\": \"230441.27\"}, \"o_orderdate\": \"1992-12-16\", \"o_orderpriority\": \"4-NOT SPECIFIED\", \"o_clerk\": \"Clerk#000000606\", \"o_shippriority\": 0, \"o_comment\": \"3LkOwi5w CjMl6z3BkPRAPmy7ixQ3Li24ylSi7zhm4Px1nCQl iQ74g3A5RnBhjw\"}, {\"o_orderkey\": 8550, \"o_custkey\": 10, \"o_orderstatus\": \"F\", \"o_totalprice\": {\"$numberDecimal\": \"212723.40\"}, \"o_orderdate\": \"1993-02-04\", \"o_orderpriority\": \"4-NOT SPECIFIED\", \"o_clerk\": \"Clerk#000000359\", \"o_shippriority\": 0, \"o_comment\": \"w kQLgNjANmB7kMjyOL74NQ3O4z6m6lR0i6g05Rkm37yj1l\"}, {\"o_orderkey\": 10849, \"o_custkey\": 10, \"o_orderstatus\": \"O\", \"o_totalprice\": {\"$numberDecimal\": \"222960.48\"}, \"o_orderdate\": \"1997-01-31\", \"o_orderpriority\": \"1-URGENT\", \"o_clerk\": \"Clerk#000000279\", \"o_shippriority\": 0, \"o_comment\": \"mQNOx1 Rylxzn7PkjACjgggA 3CO7wk32S2xL2MyBnw0m0wm2Lw4z77PC6 \"}, {\"o_orderkey\": 13730, \"o_custkey\": 10, \"o_orderstatus\": \"O\", \"o_totalprice\": {\"$numberDecimal\": \"30413.44\"}, \"o_orderdate\": \"1995-05-10\", \"o_orderpriority\": \"2-HIGH\", \"o_clerk\": \"Clerk#000000826\", \"o_shippriority\": 0, \"o_comment\": \"jLN6hwCO0lzBCzO4gL54N70NSL2w32j111OniOCw4m6B B\"}, {\"o_orderkey\": 14213, \"o_custkey\": 10, \"o_orderstatus\": \"F\", \"o_totalprice\": {\"$numberDecimal\": \"53165.06\"}, \"o_orderdate\": \"1993-12-28\", \"o_orderpriority\": \"1-URGENT\", \"o_clerk\": \"Clerk#000000959\", \"o_shippriority\": 0, \"o_comment\": \"33Bj7MxB nwx225xBkNOMP Lg41C i0hkz42nhkC wxOP7 6ik\"}, {\"o_orderkey\": 17831, \"o_custkey\": 10, \"o_orderstatus\": \"F\", \"o_totalprice\": {\"$numberDecimal\": \"262358.66\"}, \"o_orderdate\": \"1992-10-26\", \"o_orderpriority\": \"1-URGENT\", \"o_clerk\": \"Clerk#000000005\", \"o_shippriority\": 0, \"o_comment\": \"26C3OO1RwBm46AwPBMQASlAB1lj0MLj5P6lllNnLw24MnOLRxz2 N4QnO003jl3 A3 w0klyM\"}, {\"o_orderkey\": 19941, \"o_custkey\": 10, \"o_orderstatus\": \"F\", \"o_totalprice\": {\"$numberDecimal\": \"291853.99\"}, \"o_orderdate\": \"1993-11-26\", \"o_orderpriority\": \"4-NOT SPECIFIED\", \"o_clerk\": \"Clerk#000000972\", \"o_shippriority\": 0, \"o_comment\": \"Byw0jRh6S3iQmiNQCwyy7gAQ1 0OnP6\"}, {\"o_orderkey\": 23717, \"o_custkey\": 10, \"o_orderstatus\": \"F\", \"o_totalprice\": {\"$numberDecimal\": \"108989.91\"}, \"o_orderdate\": \"1993-11-17\", \"o_orderpriority\": \"3-MEDIUM\", \"o_clerk\": \"Clerk#000000751\", \"o_shippriority\": 0, \"o_comment\": \"654O zC2xRz544AnOLyCBn4kOjCwPOC2\"}, {\"o_orderkey\": 28481, \"o_custkey\": 10, \"o_orderstatus\": \"F\", \"o_totalprice\": {\"$numberDecimal\": \"42876.10\"}, \"o_orderdate\": \"1994-01-22\", \"o_orderpriority\": \"4-NOT SPECIFIED\", \"o_clerk\": \"Clerk#000000786\", \"o_shippriority\": 0, \"o_comment\": \"z55PCANh l4A3lyghM3lCAAS5xAQC0l00230jQ7N5hgQjk21 6nkMxQxjn\"}, {\"o_orderkey\": 32705, \"o_custkey\": 10, \"o_orderstatus\": \"F\", \"o_totalprice\": {\"$numberDecimal\": \"72860.79\"}, \"o_orderdate\": \"1994-03-26\", \"o_orderpriority\": \"5-LOW\", \"o_clerk\": \"Clerk#000000324\", \"o_shippriority\": 0, \"o_comment\": \"ACRmQB4Px42lwM3xR42Rz lxjl5iQA 10R xyN0h0LP4SgOR6y0BR1S3x3lwinwk7\"}, {\"o_orderkey\": 33092, \"o_custkey\": 10, \"o_orderstatus\": \"O\", \"o_totalprice\": {\"$numberDecimal\": \"134723.31\"}, \"o_orderdate\": \"1997-03-29\", \"o_orderpriority\": \"5-LOW\", \"o_clerk\": \"Clerk#000000497\", \"o_shippriority\": 0, \"o_comment\": \"ggkN3xBAhzjzLSAhyAC5hS7y3zyRRyj62yj7MnR60yOPwBRhxLhnB44RORzx2l\"}, {\"o_orderkey\": 41315, \"o_custkey\": 10, \"o_orderstatus\": \"O\", \"o_totalprice\": {\"$numberDecimal\": \"106877.08\"}, \"o_orderdate\": \"1998-07-24\", \"o_orderpriority\": \"3-MEDIUM\", \"o_clerk\": \"Clerk#000000954\", \"o_shippriority\": 0, \"o_comment\": \"0 PAzRO kCxMQCM24m7BN4PySOA36AMPM5N 5zLLmiRjz6lSAmg3\"}, {\"o_orderkey\": 44259, \"o_custkey\": 10, \"o_orderstatus\": \"O\", \"o_totalprice\": {\"$numberDecimal\": \"67198.11\"}, \"o_orderdate\": \"1997-07-29\", \"o_orderpriority\": \"4-NOT SPECIFIED\", \"o_clerk\": \"Clerk#000000651\", \"o_shippriority\": 0, \"o_comment\": \"hB2OPhz6C7x3 m3h733O2xPPOgO53P430RA5nMC0Bkn0Al5R\"}, {\"o_orderkey\": 50722, \"o_custkey\": 10, \"o_orderstatus\": \"F\", \"o_totalprice\": {\"$numberDecimal\": \"45274.18\"}, \"o_orderdate\": \"1992-10-11\", \"o_orderpriority\": \"4-NOT SPECIFIED\", \"o_clerk\": \"Clerk#000000420\", \"o_shippriority\": 0, \"o_comment\": \"3MNwQSQy4 nN2OihBOwiOhA\"}, {\"o_orderkey\": 54723, \"o_custkey\": 10, \"o_orderstatus\": \"F\", \"o_totalprice\": {\"$numberDecimal\": \"288831.80\"}, \"o_orderdate\": \"1994-01-16\", \"o_orderpriority\": \"5-LOW\", \"o_clerk\": \"Clerk#000000792\", \"o_shippriority\": 0, \"o_comment\": \" LjNw204g1m6MSMRj65NNyil6Cz\"}, {\"o_orderkey\": 56385, \"o_custkey\": 10, \"o_orderstatus\": \"O\", \"o_totalprice\": {\"$numberDecimal\": \"66419.39\"}, \"o_orderdate\": \"1998-03-29\", \"o_orderpriority\": \"4-NOT SPECIFIED\", \"o_clerk\": \"Clerk#000000184\", \"o_shippriority\": 0, \"o_comment\": \"6kMO75NLjxRP6L4mhP561Nx2iC6\"}, {\"o_orderkey\": 58593, \"o_custkey\": 10, \"o_orderstatus\": \"O\", \"o_totalprice\": {\"$numberDecimal\": \"339971.33\"}, \"o_orderdate\": \"1995-09-09\", \"o_orderpriority\": \"1-URGENT\", \"o_clerk\": \"Clerk#000000370\", \"o_shippriority\": 0, \"o_comment\": \"7wMiOC4C5SNjzniR3y707727gil41QPwBggSyCBgiLMi55nMwO0N13zCiAxLRk wLjmRghQ \"}]}"
						+"\nNumber of rows: 1";    	
    	
    	System.out.println("Result:\n"+result);
    	assertEquals(answer, result);    	    	
    }
    

    /**
     * Tests query #1. 
     */
    @Test
    public void testQuery1() throws Exception
    {       
    	// Perform query to get data
    	String result = q.query1(1000);

		assertEquals("Customer#000001000", result);
    }
    
    /**
     * Tests query #2.  
     */
    @Test
    public void testQuery2() throws Exception
    {       	    
		// Perform query to get data
    	String result = q.query2(32);
		System.out.println(result);
		assertEquals("1995-07-16", result);	
    }

	/**
     * Tests query #2 with nesting.  
     */
    @Test
    public void testQuery2Nest() throws Exception
    {       	    
		// Perform query to get data
    	String result = q.query2Nest(225);
		System.out.println(result);
		assertEquals("1995-05-25", result);	
    }

	/**
     * Tests query #3.  
     */
    @Test
    public void testQuery3() throws Exception
    {       	    
		// Perform query to get data
    	long result = q.query3();
		System.out.println(result);
		assertEquals(15000, result);	
    }

	/**
     * Tests query #3 with nesting.  
     */
    @Test
    public void testQuery3Nest() throws Exception
    {       	    
		// Perform query to get data
    	long result = q.query3Nest();
		System.out.println(result);
		assertEquals(15000, result);	
    }

	/**
     * Tests query #4.  
     */
    @Test
    public void testQuery4() throws Exception
    {       	    
		// Perform query to get data
		String result = MongoDB.toString(q.query4());    	
		System.out.println(result);

		String answer = "Rows:"
						+"\n{\"_id\": 413, \"totalAmount\": {\"$numberDecimal\": \"4452196.73\"}}"
						+"\n{\"_id\": 686, \"totalAmount\": {\"$numberDecimal\": \"4130676.48\"}}"
						+"\n{\"_id\": 1202, \"totalAmount\": {\"$numberDecimal\": \"4018729.78\"}}"
						+"\n{\"_id\": 464, \"totalAmount\": {\"$numberDecimal\": \"3986153.08\"}}"
						+"\n{\"_id\": 98, \"totalAmount\": {\"$numberDecimal\": \"3980984.34\"}}"
						+"\nNumber of rows: 5";
		assertEquals(answer, result);	
    }

	/**
     * Tests query #4 with nesting.  
     */
    @Test
    public void testQuery4Nest() throws Exception
    {       	    
		// Perform query to get data
    	String result = MongoDB.toString(q.query4Nest());    	
		System.out.println(result);

		String answer = "Rows:"
						+"\n{\"_id\": 413, \"totalAmount\": {\"$numberDecimal\": \"4452196.73\"}}"
						+"\n{\"_id\": 686, \"totalAmount\": {\"$numberDecimal\": \"4130676.48\"}}"
						+"\n{\"_id\": 1202, \"totalAmount\": {\"$numberDecimal\": \"4018729.78\"}}"
						+"\n{\"_id\": 464, \"totalAmount\": {\"$numberDecimal\": \"3986153.08\"}}"
						+"\n{\"_id\": 98, \"totalAmount\": {\"$numberDecimal\": \"3980984.34\"}}"
						+"\nNumber of rows: 5";
		assertEquals(answer, result);	
    }
}
