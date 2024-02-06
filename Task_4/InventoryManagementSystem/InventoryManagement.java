package InternPack;

public class InventoryManagement {

	public static void main(String[] args) {
		Inventory inventory = new Inventory();
		Item piston = new Item("Piston",5000.0,1);
		Item shaft = new Item("Shaft",10000.50,5);
		Item bearings = new Item("Bearings",6000,10);
		Item valves = new Item("Valves",400,4);
		inventory.addItem(piston);
		inventory.addItem(shaft);
		inventory.addItem(bearings);
		inventory.addItem(valves);
		inventory.updatePrice("Shaft", 5000);
		inventory.updateItemQuantity("Bearings", -2);
		inventory.removeItem("Valves");
		inventory.display();
		inventory.findByItemName("piston");
		inventory.generateReport();
		
	}

}
