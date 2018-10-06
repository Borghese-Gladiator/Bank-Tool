package portfolio;

import java.util.List;

public interface PortfolioDao {
	public void makeTrans(String title, 
			int amount,String description, short howOften);
	public int calcBalance();
	public String showBalance();
	public String showTransactions();
	public List<SummaryObject> getSummaryArr(); //can make table and graph from it 
	public long getToday();
}
