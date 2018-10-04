package portfolio;


public class SQLFormatter 
{
	public SQLFormatter() 
	{}
	public String clearTable(String tblName) {
		
		return "DELETE FROM " + tblName + "; "; //+ " IF EXISTS " + tblName + "." + tbl;
	}
	public String createTable(String tblName, String tbl) {
		return "if not exists (select * from sysobjects where name='" + tblName + "' and xtype='U')\r\n" + 
				"    CREATE TABLE " + tbl + '\n';
	}
	public String insertCol(String tblName,String infoCol, String infoValues) 
	{
		String result = "INSERT INTO " + tblName + 
				" (" + infoCol + ")" +
				" VALUES(" + infoValues + ");";
		return result;
	}
	public String updateBalIfNullInsert(int balance) {
		String result = "IF EXISTS (SELECT * FROM tblAccount)\r\n" + 
				"UPDATE tblAccount SET Balance =" +  balance + "\r\n" + 
				"ELSE\r\n" + 
				"INSERT INTO tblAccount (BALANCE) VALUES(" + balance + ");";
		return result;
	}
}
