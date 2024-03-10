import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class AutomobileInventory {
    private static final String DIRECTORY = "vehicles";
    private static Automobile car = new Automobile();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        new File(DIRECTORY).mkdirs();

        while (true) {
            System.out.println("\nVehicle Inventory System:");
            System.out.println("1. Add/Update Vehicle");
            System.out.println("2. Remove Vehicle");
            System.out.println("3. List Vehicle Information");
            System.out.println("4. Save Vehicle to File");
            System.out.println("5. Load Vehicle from File");
            System.out.println("6. Delete Vehicle File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter vehicle make: ");
                    String make = scanner.nextLine();
                    System.out.println("Enter vehicle model: ");
                    String model = scanner.nextLine();
                    System.out.println("Enter vehicle color: ");
                    String color = scanner.nextLine();
                    System.out.println("Enter vehicle year: ");
                    int year = scanner.nextInt();
                    System.out.println("Enter vehicle mileage: ");
                    int mileage = scanner.nextInt();
                    scanner.nextLine();
                    car = new Automobile(make, model, color, year, mileage);
                    System.out.println("Vehicle added/updated successfully.");
                    break;
                case 2: // Remove Vehicle
                    car = new Automobile();
                    System.out.println("Vehicle removed successfully.");
                    break;
                case 3:
                    String[] vehicleInfo = car.getVehicleInfo();
                    System.out.println("Vehicle Information:");
                    for (String info : vehicleInfo) {
                        System.out.println(info);
                    }
                    break;
                case 4:
                    saveVehicleToFile();
                    break;
                case 5:
                    System.out.print("Enter the vehicle filename to load (make_model_year.txt): ");
                    String filename = scanner.nextLine();
                    loadVehicleFromFile(filename);
                    break;
                case 6:
                    System.out.print("Enter the vehicle filename to delete (make_model_year.txt): ");
                    filename = scanner.nextLine();
                    deleteVehicleFile(filename);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void saveVehicleToFile() {
        String filename = DIRECTORY + "/" + car.getMake() + "_" + car.getModel() + "_" + car.getYear() + ".txt";
        try (FileWriter writer = new FileWriter(filename)) {
            for (String info : car.getVehicleInfo()) {
                writer.write(info + "\n");
            }
            System.out.println("Vehicle saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Failed to save vehicle to file.");
            e.printStackTrace();
        }
    }

    private static void loadVehicleFromFile(String filename) {
        try {
            java.util.List<String> lines = Files.readAllLines(Paths.get(DIRECTORY, filename));
            if (lines.size() >= 5) {
                car = new Automobile(lines.get(0), lines.get(1), lines.get(2), Integer.parseInt(lines.get(3)), Integer.parseInt(lines.get(4)));
                System.out.println("Vehicle loaded from file: " + filename);
            } else {
                System.out.println("File format incorrect or file is incomplete.");
            }
        } catch (IOException e) {
            System.out.println("Failed to load vehicle from file.");
            e.printStackTrace();
        }
    }

    private static void deleteVehicleFile(String filename) {
        File file = new File(DIRECTORY, filename);
        if (file.delete()) {
            System.out.println("Vehicle file deleted: " + filename);
        } else {
            System.out.println("Failed to delete vehicle file. File may not exist.");
        }
    }
}