public class Automobile {
    private String make;
    private String model;
    private String color;
    private int year;
    private int mileage;

    public Automobile() {
    }

    public Automobile(String make, String model, String color, int year, int mileage) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.mileage = mileage;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getYear() {
        return year;
    }

    public int getMileage() {
        return mileage;
    }

    public String addOrUpdateVehicle(String make, String model, String color, int year, int mileage) {
        try {
            this.make = make;
            this.model = model;
            this.color = color;
            this.year = year;
            this.mileage = mileage;
            return "Vehicle added/updated successfully.";
        } catch (Exception e) {
            return "Failed to add/update vehicle.";
        }
    }

    public String removeVehicle() {
        try {
            this.make = null;
            this.model = null;
            this.color = null;
            this.year = 0;
            this.mileage = 0;
            return "Vehicle removed successfully.";
        } catch (Exception e) {
            return "Failed to remove vehicle.";
        }
    }

    public String[] getVehicleInfo() {
        try {
            return new String[]{this.make, this.model, this.color, Integer.toString(this.year), Integer.toString(this.mileage)};
        } catch (Exception e) {
            return new String[]{"Failed to retrieve vehicle information."};
        }
    }
}