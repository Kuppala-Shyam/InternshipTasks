package InternPack;

public class Item {
	private String itemName;
	private double price;
	private int quantity;
	public Item(String itemName, double price, int quantity) {
		super();
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getPrice() {
		return price;
	}
	public void updatePrice(double price) {
		this.price += price;
	}
	public double getQuantity() {
		return quantity;
	}
	public void updateQuantity(int quantity) {
		this.quantity += quantity;
	}
	@Override
	public String toString() {
		return "Item [itemName=" + itemName + ", price=" + price + ", quantity=" + quantity + "]";
	}
	
}
