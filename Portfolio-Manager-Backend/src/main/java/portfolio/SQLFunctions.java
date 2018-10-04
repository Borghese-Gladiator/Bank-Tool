package portfolio;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class SQLFunctions implements PortfolioDao
{
	void log(String yyy)
	{
		PrintWriter writer;
		try {
			writer = new PrintWriter("c:\\temp\\ddd.txt", "UTF-8");
			writer.println(yyy);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private SQLFormatter format = new SQLFormatter();
	SQLConnect conn = new SQLConnect();
	public SQLFunctions() {
		conn.sqlConnect(initialize());
	}
	private String createTables() {
		String result = "";
		result += format.createTable(Transaction.tblName, Transaction.assembleSQL());
		result += format.createTable(Account.tblName, Account.assembleSQL());
		return result;
	}
	private String initialize() {
		String result = "";
		result += createTables();
//		result += clearTables();
		return result;
	}
	private String clearTables() {
		String result = "";
		result += format.clearTable(Transaction.tblName);
		result += format.clearTable(Account.tblName);
		return result;
	}
	private String insertTrans(Transaction trans) {
		return format.insertCol(Transaction.tblName, trans.rows(), trans.values());
	}
	public void makeTrans(Transaction trans) {
		// TODO Auto-generated method stub
		conn.sqlConnect(insertTrans(trans));
		setBalance(calcBalance());
	}
	@Override
	public void makeTrans(String title, int amount, String description, short howOften) {
		// TODO Auto-generated method stub
		conn.sqlConnect(insertTrans(new Transaction(title, amount, getToday(), description, howOften)));
		setBalance(calcBalance());
	}
	@Override
	public int calcBalance() {
		int balance = 0;
		long today = getToday();
		List<Transaction> transHistory = conn.sqlAllTransactions();
		for (Transaction trans: transHistory) {
			balance += trans.addToBalance(today);
		}
		return balance;
	}
	private void setBalance(int balance) {
		String sql = format.updateBalIfNullInsert(balance);
		conn.sqlConnect(sql);
	}
	//For Transaction History tab ---->rework to support table?
	@Override
	public String showTransactions() {
		String result = "";
		List<Transaction> transHistory = conn.sqlAllTransactions();
		for (Transaction trans: transHistory) {
			result += trans.toString();
		}
		return result;
	}
	@Override
	public String showBalance() {
		return "Balance: " + conn.sqlGetBalance();
	}
	//For graphical summary tab
	@Override
	public List<SummaryObject> getSummaryArr() {
		List<SummaryObject> result = new ArrayList<SummaryObject>();
		long today = getToday();
		List<Transaction> transHistory = conn.sqlAllTransactions();
		for (Transaction trans: transHistory) {
			result.add(new SummaryObject(trans.getTitle(), new Integer(trans.addToBalance(today)))); 
		}
		return result;
	}
	//Use for line chart if time
	public Hashtable<Long, Integer> getOverTimeArr () {
		Hashtable<Long, Integer> result = new Hashtable<Long, Integer>();
		return result;
	}
	@Override
	public long getToday() {
		// TODO Auto-generated method stub
		Date temp = new Date();
		return temp.getTime();
	}
}
