package portfolio;

import java.util.Date;
import java.util.List;

public class App3 {

	public static void main(String[] args) {
		SQLFunctions sql = new SQLFunctions();
		System.out.println(sql.showBalance());
		System.out.println(sql.showTransactions());
		//public Transaction(String aTi, int aAmo, long aTo, String des, short howOften) {
		//1 - one-time, 2- daily, 3-weekly, 4-monthly, 5-yearly
		Date x = new Date();
		long a = x.getTime();
		short b = 1;
		Transaction trans = new Transaction("'Salary'", 100, a, "'Great day to get paid'", b);
		sql.makeTrans(trans);
		System.out.println(sql.showBalance());
		System.out.println(sql.showTransactions());
		String result = "/hello\\\\n" + 
				"/getBalance\\\\n" +
				"/getToday\\\\n" + 
				"/add\\\\n" + 
				"/show\\\\n" + 
				"/showTable\\\\n";
		result = result.replaceAll("\n","\\\\n");
		System.out.println("RESULT" + result);
		List<SummaryObject> tempList = sql.getSummaryArr();
		for (SummaryObject tempX: tempList) {
			System.out.println(tempX.toString());
		}
	}
}