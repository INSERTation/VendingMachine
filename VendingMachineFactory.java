import java.util.Scanner;

public class VendingMachineFactory {
    private static Scanner scanner = new Scanner(System.in);
    private static RegularVendingMachine currentVendingMachine;

    public static void main(String[] args) {
        boolean exitProgram = false;

        while (!exitProgram) {
            displayMainMenu();
            int choice = getUserChoice(1, 3);

            switch (choice) {
                case 1:
                    createVendingMachine();
                    break;
                case 2:
                    testVendingMachine();
                    break;
                case 3:
                    exitProgram = true;
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("--------- Vending Machine Factory ---------");
        System.out.println("1. Create a Vending Machine");
        System.out.println("2. Test a Vending Machine");
        System.out.println("3. Exit");
        System.out.println("--------------------------------------------");
    }

    private static void createVendingMachine() {
        System.out.println("Choose the type of Vending Machine:");
        System.out.println("1. Regular Vending Machine");
        System.out.println("2. Special Vending Machine");
        int choice = getUserChoice(1, 2);

        if (choice == 1) {
            System.out.println("Enter the number of item slots for the Regular Vending Machine:");
            int numSlots = getUserChoice(1, Integer.MAX_VALUE);
            currentVendingMachine = new RegularVendingMachine(numSlots);
            initializeRegularVendingMachine();
            System.out.println("Regular Vending Machine created.");
        } else if (choice == 2) {
            System.out.println("Enter the number of item slots for the Special Vending Machine:");
            int numSlots = getUserChoice(1, Integer.MAX_VALUE);
            currentVendingMachine = new RegularVendingMachine(numSlots);
            initializeSpecialVendingMachine();
            System.out.println("Special Vending Machine created.");
        }
    }

    private static void initializeRegularVendingMachine() {
        for (int slotIndex = 0; slotIndex < currentVendingMachine.getNumSlots(); slotIndex++) {
            System.out.println("Enter the name of the item for slot " + slotIndex + ":");
            String itemName = scanner.nextLine();
            System.out.println("Enter the quantity for slot " + slotIndex + ":");
            int quantity = getUserChoice(1, Integer.MAX_VALUE);
            currentVendingMachine.addItemSlot(slotIndex, new ItemSlot(itemName, quantity));

            System.out.println("Enter the price for the item in slot " + slotIndex + ":");
            double price = scanner.nextDouble();
            currentVendingMachine.setItemPrice(slotIndex, price);

            System.out.println("Enter the calories for the item in slot " + slotIndex + ":");
            int calories = getUserChoice(0, Integer.MAX_VALUE);
            currentVendingMachine.setItemCalories(slotIndex, calories);
            // Clear the newline character from input buffer
        }
    }
    static class SpecialVendingMachine{

        public SpecialVendingMachine(int numSlots) {

        }
    }

        // Additional methods and properties specific to Special Vending Machine


    private static void initializeSpecialVendingMachine() {
        for (int slotIndex = 0; slotIndex < currentVendingMachine.getNumSlots(); slotIndex++) {
            System.out.println("Enter the name of the item for slot " + slotIndex + ":");
            String itemName = scanner.nextLine();
            System.out.println("Enter the quantity for slot " + slotIndex + ":");
            int quantity = getUserChoice(1, Integer.MAX_VALUE);
            currentVendingMachine.addItemSlot(slotIndex, new ItemSlot(itemName, quantity));

            System.out.println("Enter the price for the item in slot " + slotIndex + ":");
            double price = scanner.nextDouble();
            currentVendingMachine.setItemPrice(slotIndex, price);

            System.out.println("Enter the calories for the item in slot " + slotIndex + ":");
            int calories = getUserChoice(0, Integer.MAX_VALUE);
            currentVendingMachine.setItemCalories(slotIndex, calories);

            scanner.nextLine(); // Clear the newline character from input buffer
        }
    }
    private static void testVendingMachine() {
        if (currentVendingMachine == null) {
            System.out.println("No Vending Machine has been created yet. Please create one first.");
            return;
        }

        boolean exitTest = false;

        while (!exitTest) {
            displayTestMenu();
            int choice = getUserChoice(1, 3);

            switch (choice) {
                case 1:
                    testVendingFeatures();
                    break;
                case 2:
                    testMaintenanceFeatures();
                    break;
                case 3:
                    exitTest = true;
                    System.out.println("Returning to Test a Vending Machine menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void displayTestMenu() {
        System.out.println("--------- Test a Vending Machine ---------");
        System.out.println("1. Test Vending Features");
        System.out.println("2. Test Maintenance Features");
        System.out.println("3. Back to Main Menu");
        System.out.println("-------------------------------------------");
    }

    private static void testVendingFeatures() {
        if (currentVendingMachine.getStartingInventory() == null) {
            System.out.println("No inventory available. Please perform restocking first.");
            return;
        }

        System.out.println("Vending Features Test:");
        currentVendingMachine.displayInventory();

        // Prompt user for choice and perform the necessary actions

        System.out.println("Vending Features Test completed.");
    }

    private static void testMaintenanceFeatures() {
        System.out.println("Maintenance Features Test:");
        // Implement maintenance features like restocking, setting price, collecting money, etc.
        System.out.println("Maintenance Features Test completed.");
    }

    private static int getUserChoice(int min, int max) {
        int choice;

        while (true) {
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();

                if (choice >= min && choice <= max) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
                }
            } else {
                scanner.nextLine(); // Clear the input buffer
                System.out.println("Invalid choice. Please enter a valid number.");
            }
        }

        scanner.nextLine(); // Clear the newline character from input buffer
        return choice;
    }
}

class RegularVendingMachine {
    private ItemSlot[] itemSlots;
    private double[] itemPrices;
    private int[] itemCalories;
    private int[] startingInventory;
    private int[] endingInventory;
    private int numSlots;

    public RegularVendingMachine(int numSlots) {
        this.numSlots = numSlots;
        itemSlots = new ItemSlot[numSlots];
        itemPrices = new double[numSlots];
        itemCalories = new int[numSlots];
        startingInventory = new int[numSlots];
        endingInventory = new int[numSlots];
    }

    public void addItemSlot(int slotIndex, ItemSlot itemSlot) {
        if (isValidSlotIndex(slotIndex)) {
            itemSlots[slotIndex] = itemSlot;
        } else {
            System.out.println("Invalid slot index.");
        }
    }

    public void setItemPrice(int slotIndex, double price) {
        if (isValidSlotIndex(slotIndex)) {
            itemPrices[slotIndex] = price;
        } else {
            System.out.println("Invalid slot index.");
        }
    }

    public void setItemCalories(int slotIndex, int calories) {
        if (isValidSlotIndex(slotIndex)) {
            itemCalories[slotIndex] = calories;
        } else {
            System.out.println("Invalid slot index.");
        }
    }

    public void displayInventory() {
        System.out.println("Current Inventory:");
        for (int slotIndex = 0; slotIndex < numSlots; slotIndex++) {
            ItemSlot itemSlot = itemSlots[slotIndex];
            if (itemSlot != null) {
                String itemName = itemSlot.getItemName();
                int quantity = itemSlot.getQuantity();
                double price = itemPrices[slotIndex];
                int calories = itemCalories[slotIndex];

                System.out.println("Slot " + slotIndex + ": " + itemName + " (Quantity: " + quantity +
                        ", Price: " + price + ", Calories: " + calories + ")");
            }
        }
    }

    private boolean isValidSlotIndex(int slotIndex) {
        return slotIndex >= 0 && slotIndex < numSlots;
    }

    public int getNumSlots() {
        return numSlots;
    }

    public int[] getStartingInventory() {
        return startingInventory;
    }
}

class ItemSlot {
    private String itemName;
    private int quantity;

    public ItemSlot(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }
}


