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
                case 1 -> createVendingMachine();
                case 2 -> testVendingMachine();
                case 3 -> {
                    exitProgram = true;
                    System.out.println("Exiting the program.");
                }
                default -> System.out.println("Invalid choice. Please try again.");
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
            int numSlots = getUserChoice(1, Integer.MAX_VALUE); // make this 8 later
            currentVendingMachine = new RegularVendingMachine(numSlots);
            initializeRegularVendingMachine();
            System.out.println("Regular Vending Machine created.");
        } else if (choice == 2) {
            System.out.println("Enter the number of item slots for the Special Vending Machine:");
            int numSlots = getUserChoice(1, Integer.MAX_VALUE);
            currentVendingMachine = new SpecialVendingMachine(numSlots);
            initializeSpecialVendingMachine();
            System.out.println("Special Vending Machine created.");
        }
    }

        private static void initializeRegularVendingMachine() {
        for (int slotIndex = 0; slotIndex < currentVendingMachine.getNumSlots(); slotIndex++) {
            System.out.println("Enter the name of the item for slot " + (slotIndex + 1)+ ":");
            String itemName = scanner.nextLine();
            System.out.println("Enter the quantity for slot " + (slotIndex + 1)+ ":");
            int quantity = getUserChoice(1, Integer.MAX_VALUE);
            currentVendingMachine.addItemSlot(slotIndex, new ItemSlot(itemName, quantity));

            System.out.println("Enter the price for the item in slot " +(slotIndex + 1)+ ":");
            double price = scanner.nextDouble();
            currentVendingMachine.setItemPrice(slotIndex, price);

            System.out.println("Enter the calories for the item in slot " +(slotIndex + 1)+ ":");
            int calories = getUserChoice(0, Integer.MAX_VALUE);
            currentVendingMachine.setItemCalories(slotIndex, calories);
        }
    }
    public static class SpecialVendingMachine extends RegularVendingMachine {
        private String[][] recipes;
        private String[][] preparationSteps;

        public SpecialVendingMachine(int numSlots) {
            super(numSlots);
            this.recipes = new String[numSlots][];
            this.preparationSteps = new String[numSlots][];
        }

        public void addRecipe(int index, String[] recipe, String[] steps) {
            recipes[index] = recipe;
            preparationSteps[index] = steps;
        }

        public void prepareItem(int index) {
            if (recipes[index] == null) {
                System.out.println("Recipe not found. Dispensing as an individual item...");
                // TODO: Add logic for dispensing individual items
            } else {
                String[] steps = preparationSteps[index];

                for (String step : steps) {
                    System.out.println(step + "...");
                }

                System.out.println("Item " + index + " Done!");
            }
        }
    }




    private static void initializeSpecialVendingMachine() {
        for (int slotIndex = 0; slotIndex < currentVendingMachine.getNumSlots(); slotIndex++) {
            System.out.println("Enter the name of the item for slot " + (slotIndex + 1)+ ":");
            String itemName = scanner.nextLine();
            System.out.println("Enter the quantity for slot " + (slotIndex + 1)+ ":");
            int quantity = getUserChoice(1, Integer.MAX_VALUE);
            currentVendingMachine.addItemSlot(slotIndex, new ItemSlot(itemName, quantity));

            System.out.println("Enter the price for the item in slot " + (slotIndex + 1)+ ":");
            double price = scanner.nextDouble();
            currentVendingMachine.setItemPrice(slotIndex, price);

            System.out.println("Enter the calories for the item in slot " + (slotIndex + 1)+ ":");
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
                case 1 -> testVendingFeatures();
                case 2 -> testMaintenanceFeatures();
                case 3 -> {
                    exitTest = true;
                    System.out.println("Returning to Test a Vending Machine menu.");
                }
                default -> System.out.println("Invalid choice. Please try again.");
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

        // Let's add a test for inserting money
        currentVendingMachine.insertMoney(5.0);

        // Let's add a test for checking out an item
        System.out.println("Choose a slot to checkout:");
        int slotChoice = getUserChoice(0, currentVendingMachine.getNumSlots() - 1);
        currentVendingMachine.checkout(slotChoice);

        // Let's add a test for returning change
        currentVendingMachine.returnChange();

        System.out.println("Vending Features Test completed.");
    }

    private static void testMaintenanceFeatures() {
        boolean running = true;

        while (running) {
            currentVendingMachine.displayInventory();

            System.out.println("Maintenance Features Test:");
            System.out.println("Choose an option:\n1. Restock\n2. Set price\n3. Collect money\n4. End maintenance");
            int choice = getUserChoice(1, 4);

            switch (choice) {
                case 1 -> { // Restock
                    System.out.println("Choose a slot to restock:");
                    int slotChoice = getUserChoice(0, currentVendingMachine.getNumSlots() - 1);
                    System.out.println("Enter the quantity to restock:");
                    int quantity = getUserChoice(1, Integer.MAX_VALUE);
                    currentVendingMachine.restock(slotChoice, quantity);
                }
                case 2 -> { // Set price
                    System.out.println("Choose a slot to set price:");
                    int slotChoice = getUserChoice(0, currentVendingMachine.getNumSlots() - 1);
                    System.out.println("Enter the new price:");
                    double price = scanner.nextDouble();
                    currentVendingMachine.setPrice(slotChoice, price);
                }
                case 3 -> { // Collect money
                    double collectedMoney = currentVendingMachine.collectMoney();
                    System.out.println("Collected money: $" + collectedMoney);
                }
                case 4 -> { // End maintenance
                    running = false;
                }
                default -> System.out.println("Invalid choice");
            }
        }

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
    private double machineBalance;

    public RegularVendingMachine(int numSlots) {
        this.numSlots = numSlots;
        itemSlots = new ItemSlot[numSlots];
        itemPrices = new double[numSlots];
        itemCalories = new int[numSlots];
        startingInventory = new int[numSlots];
        endingInventory = new int[numSlots];
        this.machineBalance = 0.0;
    }

    public void insertMoney(double amount) {
        this.machineBalance += amount;
        System.out.println("Inserted $" + amount + ". Current balance: $" + this.machineBalance);
    }

    public void checkout(int slotIndex) {
        if (isValidSlotIndex(slotIndex)) {
            ItemSlot itemSlot = itemSlots[slotIndex];
            double price = itemPrices[slotIndex];

            if (itemSlot != null && itemSlot.getQuantity() > 0) {
                if (this.machineBalance >= price) {
                    this.machineBalance -= price;
                    itemSlot.setQuantity(itemSlot.getQuantity() - 1);
                    System.out.println("Dispensed " + itemSlot.getItemName() + ". Remaining balance: $" + this.machineBalance);
                } else {
                    System.out.println("Insufficient balance. Please insert more money.");
                }
            } else {
                System.out.println("Item slot is empty. Please select a different slot.");
            }
        } else {
            System.out.println("Invalid slot index.");
        }
    }

    public void returnChange() {
        System.out.println("Returning change: $" + this.machineBalance);
        this.machineBalance = 0.0;
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

    public void restock(int slotChoice, int quantity) {
        if (isValidSlotIndex(slotChoice)) {
            ItemSlot itemSlot = itemSlots[slotChoice];
            if (itemSlot != null) {
                itemSlot.setQuantity(itemSlot.getQuantity() + quantity);
                System.out.println("Restocked " + quantity + " items to slot " + slotChoice);
            } else {
                System.out.println("Item slot not initialized. Please initialize it first.");
            }
        } else {
            System.out.println("Invalid slot index.");
        }

    }

    public void setPrice(int slotChoice, double price) {
        if (isValidSlotIndex(slotChoice)) {
            itemPrices[slotChoice] = price;
            System.out.println("Price set to $" + price + " for slot " + slotChoice);
        } else {
            System.out.println("Invalid slot index.");
        }
    }

    public double collectMoney() {
        double collectedMoney = this.machineBalance;
        this.machineBalance = 0.0;
        return collectedMoney;
    }
}

class ItemSlot {
    private String itemName;
    private int quantity;

    public ItemSlot(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }
}



