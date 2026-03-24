import java.util.Scanner;

class Vehicle {
    private String vehiclename;
    private int vehicleno;

    public Vehicle(String vehiclename, int vehicleno) {
        this.vehiclename = vehiclename;
        this.vehicleno = vehicleno;
    }

    public int getvehicleno() {
        return vehicleno;
    }

    public String getvehiclename() {
        return vehiclename;
    }
}

class Slot {
    private int slotno;
    private Vehicle vehicle;
    private boolean isOccupied;

    public Slot(int slotno) {
        this.slotno = slotno;
        this.isOccupied = false;
    }

    public int getslotno() {
        return slotno;
    }

    public Vehicle getvehicle() {
        return vehicle;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void park(Vehicle v) {
        this.vehicle = v;
        this.isOccupied = true;
    }

    public void unpark() {
        this.vehicle = null;
        this.isOccupied = false;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 100;
        Slot[] slots = new Slot[n];

        for(int i = 0; i < n; i++) {
            slots[i] = new Slot(i + 1);
        }

        while(true) {
            System.out.println("\nEnter the choice");
            System.out.println("1. Park");
            System.out.println("2. Unpark");
            System.out.println("3. Display");
            System.out.println("4. Exit");

            int choice = sc.nextInt();

            switch(choice) {
                case 1:
                    System.out.println("Enter vehicle name");
                    String name = sc.next();
                    System.out.println("Enter vehicle number");
                    int no = sc.nextInt();
                    Vehicle v = new Vehicle(name, no);

                    boolean parked = false;

                    for(int i = 0; i < n; i++) {
                        if(!slots[i].isOccupied()) {
                            slots[i].park(v);
                            System.out.println("Vehicle parked in slot " + slots[i].getslotno());
                            parked = true;
                            break;
                        }
                    }

                    if(!parked) {
                        System.out.println("Parking Full");
                    }
                    break;

                case 2:
                    System.out.println("Enter vehicle number");
                    int no1 = sc.nextInt();
                    boolean found = false;

                    for(int i = 0; i < n; i++) {
                        if(slots[i].isOccupied() &&
                                slots[i].getvehicle().getvehicleno() == no1) {
                            slots[i].unpark();
                            System.out.println("Vehicle unparked from slot " + slots[i].getslotno());
                            found = true;
                            break;
                        }
                    }

                    if(!found) {
                        System.out.println("Vehicle not found");
                    }
                    break;

                case 3:
                    System.out.println("\nParking Status:");
                    for(int i = 0; i < 100; i++) {
                        if(slots[i].isOccupied()) {
                            System.out.println("Slot " + slots[i].getslotno() +
                                    " is occupied by " +
                                    slots[i].getvehicle().getvehiclename() + " " +
                                    slots[i].getvehicle().getvehicleno());
                        } else {
                            System.out.println("Slot " + slots[i].getslotno() + " is empty");
                        }
                    }
                    break;

                case 4:
                    System.out.println("Thank you");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}