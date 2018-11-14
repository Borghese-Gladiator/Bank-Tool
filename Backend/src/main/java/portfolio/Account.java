package portfolio;


public class Account implements SQLTable{
	public static String tblName = "tblAccount";
	private int balance;
	public Account() {}
	public Account(int aBalance) {
		setBalance(aBalance);
	}
	public static String assembleSQL() {
		String out = "tblAccount (" ;
		out += "Balance int)";
		return out;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	@Override
	public String rows() {
		return "Balance";
	}
	@Override
	public String values() {
		return balance + "";
	}
}