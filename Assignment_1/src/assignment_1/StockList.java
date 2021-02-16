package assignment_1;
import java.util.Collections;
import java.util.List;
//import assignment_1.Stocks;

public class StockList {
	
	List <Stocks> data = null;
	
//	public List <Stocks> stockReturn() {
//		return data;
//	}
	public void setData(List <Stocks> data) {
		this.data = data;
	}
	public List <Stocks> getData(){
		return data;
	}
	public Stocks getByIndex(int i) {
		return data.get(i);
	}
	public void addStock(Stocks s) {
		this.data.add(s);
	}
	public void reverseStock(StockList s) {
		Collections.reverse(data);
	}
}
