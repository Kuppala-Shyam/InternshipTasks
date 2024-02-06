package InternPack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Inventory {
	private HashMap<String, Item> items;

	public Inventory() {
		items = new HashMap<>();
	}
	public void addItem(Item item) {
		items.put(item.getItemName(), item);
	}
	public void removeItem(String itemName) {
		items.remove(itemName);
	}
	public void updatePrice(String itemName, double price) {
		Item item =items.get(itemName);
		if(item != null) {
			item.updatePrice(price);
		}
		else {
			System.out.println("Item is not found..");
		}
	}
	public void updateItemQuantity(String itemName, int quantity) {
		Item item = items.get(itemName);
		if(item != null) {
			item.updateQuantity(quantity);
		}
		else {
			System.out.println("Item is not found..");
		}
	}
	public void display() {
		for(Item item : items.values()) {
			System.out.println(item.getItemName()+" Price= "+item.getPrice()+" Quantity = "+item.getQuantity());
		}
	}
	public void findByItemName(String name) {
		List<Item>  nameOfItem = new ArrayList();
		for(Item item :items.values()) {
			if(item.getItemName().equalsIgnoreCase(name)) {
				nameOfItem.add(item);
			}
		}
		System.out.println(nameOfItem);
	}
	public void generateReport() {
		List<Item>  reportItems= new ArrayList(items.values());
		System.out.println(reportItems);
	}
	
}
