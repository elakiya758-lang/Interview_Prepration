import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Vehicle {
    private int vehicleId;
    private String vehicleName;
    private String ownerName;

    public Vehicle(int vehicleId, String vehicleName, String ownerName) {
        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
        this.ownerName = ownerName;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public String toString() {
        return "ID: " + vehicleId + ", " + vehicleName + " owned by " + ownerName;
    }
}

class Slot {
    private int slotId;
    private Vehicle vehicle;

    public Slot(int slotId) {
        this.slotId = slotId;
        this.vehicle = null;
    }

    public boolean isEmpty() {
        return vehicle == null;
    }

    public boolean park(Vehicle v) {
        if (isEmpty()) {
            this.vehicle = v;
            return true;
        }
        return false;
    }

    public boolean remove() {
        if (!isEmpty()) {
            this.vehicle = null;
            return true;
        }
        return false;
    }

    public int getSlotId() {
        return slotId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Slot " + slotId + ": Empty";
        } else {
            return "Slot " + slotId + ": Occupied by " + vehicle;
        }
    }
}

class ParkingLot {
    private List<Slot> slots;

    public ParkingLot(int totalSlots) {
        slots = new ArrayList<>();
        for (int i = 1; i <= totalSlots; i++) {
            slots.add(new Slot(i));
        }
    }

    
    public boolean isVehicleExists(int vehicleId) {
        for (Slot slot : slots) {
            if (!slot.isEmpty() && slot.getVehicle().getVehicleId() == vehicleId) {
                return true;
            }
        }
        return false;
    }

    public boolean parkVehicle(Vehicle v) {
        if (isVehicleExists(v.getVehicleId())) {
            System.out.println("Vehicle ID already exists!");
            return false;
        }

        for (Slot slot : slots) {
            if (slot.isEmpty()) {
                slot.park(v);
                System.out.println("Vehicle " + v + " parked in Slot " + slot.getSlotId());
                return true;
            }
        }

        System.out.println("Parking Lot Full! Cannot park " + v);
        return false;
    }

    public boolean removeVehicle(int vehicleId) {
        for (Slot slot : slots) {
            if (!slot.isEmpty() && slot.getVehicle().getVehicleId() == vehicleId) {
                slot.remove();
                System.out.println("Vehicle ID " + vehicleId + " removed from Slot " + slot.getSlotId());
                return true;
            }
        }

        System.out.println("Vehicle ID " + vehicleId + " not found.");
        return false;
    }

    public void displayStatus() {
        System.out.println("\n--- Parking Lot Status ---");
        for (Slot slot : slots) {
            System.out.println(slot);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int totalSlots = 100;
        ParkingLot lot = new ParkingLot(totalSlots);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Remove Vehicle");
            System.out.println("3. Display Status");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter vehicle ID: ");
                    int vId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter vehicle name: ");
                    String vName = sc.nextLine();

                    System.out.print("Enter owner name: ");
                    String oName = sc.nextLine();

                    Vehicle v = new Vehicle(vId, vName, oName);
                    lot.parkVehicle(v);
                    break;

                case 2:
                    System.out.print("Enter vehicle ID to remove: ");
                    int removeId = sc.nextInt();
                    sc.nextLine();

                    lot.removeVehicle(removeId);
                    break;

                case 3:
                    lot.displayStatus();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}