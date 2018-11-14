package portfolio;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("/services")
public class RestAPI {
/*	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Profile add(Profile profile) {
		mongo.add(profile);
		return profile;
	}
	public void makeTrans(String title, 
			int amount,String description, short howOften);
	public int calcBalance();
	public String showBalance();
	public String showTransactions();
	public Hashtable<String, Integer> getSummaryArr(); //can make table and graph from it 
	public long getToday();
*/
	private SQLFunctions sql = new SQLFunctions();
	Gson gson = new Gson();
	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_HTML)
	public String hello() {
		return "<p>Hello</p>";
	}
	@GET
	@Path("/getBalance")
	@Produces(MediaType.APPLICATION_JSON)
	public String retrieveBalance() {
		return gson.toJson(sql.calcBalance());
	}
	@GET
	@Path("/getToday")
	@Produces(MediaType.APPLICATION_JSON)
	public String getToday() {
		return gson.toJson(sql.getToday());
	}
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Transaction add(Transaction trans) {
		sql.makeTrans(trans);
		return trans;
	}
	@POST
	@Path("/showSummary")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SummaryObject> showAll() {
		return sql.getSummaryArr();
	}
	@POST
	@Path("/showFullTable")
	@Produces(MediaType.APPLICATION_JSON)
	public String showTable() {
		return gson.toJson(sql.showTransactions());
	}
	@GET
	@Path("/functions")
	@Produces(MediaType.APPLICATION_JSON)
	public String functions() {
		String result = "/hello " + 
				"/getBalance " +
				"/getToday " + 
				"/add " + 
				"/show " + 
				"/showTable ";
		return gson.toJson(result);
	}
}
