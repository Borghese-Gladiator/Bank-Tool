package portfolio;

public class SummaryObject {
	private String title;
	private int num;
	public SummaryObject() {}
	public SummaryObject(String aTi, int aNum) {
		title = aTi;
		num = aNum;
	}
	@Override
	public String toString() {
		return title + " ," + num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
}
