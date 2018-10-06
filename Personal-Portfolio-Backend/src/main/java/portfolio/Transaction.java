package portfolio;
import java.util.Date;

public class Transaction implements SQLTable
{
	public static String tblName = "tblTransaction";
	private String title;
	private int amount;
	private long dateEntered;
	private String description;
	//1 - one-time, 2- daily, 3-weekly, 4-monthly, 5-yearly
	private short howOften;
	public Transaction() {}
	public Transaction(String aTi, int aAmo, long aTo, String des, short howOften) {
		title = aTi;
		amount = aAmo;
		dateEntered = aTo;
		description = des;
		this.howOften = howOften;
	}
	public static String assembleSQL() {
		String out = "tblTransaction ( " ;
		out += "Title nvarchar(50), ";
		out += "Amount int, ";
		out += "DateEntered BIGINT, ";
		out += "Description nvarchar(50), ";
		out += "HowOften smallint)";
		return out;
	}
	@Override
	public String rows() {
		return "Title, Amount, DateEntered, Description, HowOften";
	}
	@Override
	public String values() 
	{
		String result = 
				title + ", " + 
				amount + ", " +
				dateEntered + ", " +
				description + ", " +
				howOften + "";
		return result;
	}
	public int addToBalance(long today) {
		long timeDiff = today - dateEntered;
		long numTimes = Math.floorDiv(timeDiff, numSeconds(timeDiff));
		int result = (int)numTimes * amount;
		return result;
	}
	private long numSeconds(long timeDiff) {
		long result = timeDiff;
		switch(howOften) {
		case 1: //one-time
			break;
		case 2: //daily
			result = 86400000;
			break;
		case 3: //weekly
			result =604800000;
			break;
		case 4: //monthly
			break;
		case 5: //yearly
			final long temp = (long)( 86400000 * 365.25);
			result = temp;
			break;
		}
		return result;
	}
	@Override
	public String toString() {
		return values();
	}
	public static String getTblName() {
		return tblName;
	}
	public static void setTblName(String tblName) {
		Transaction.tblName = tblName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public long getToday() {
		return dateEntered;
	}
	public void setDateEntered(long today) {
		this.dateEntered = today;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public short getHowOften() {
		return howOften;
	}
	public void setHowOften(short howOften) {
		this.howOften = howOften;
	}
}