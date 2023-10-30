
public class Main {

    public static void main(String[] args) {
        TempMonitor tempMonitor = new TempMonitor();
        Sensor newSensor = new Sensor(21);

        // deploy sensor
        System.out.println("------------- deploying new sensor in montreal ------------------");
        String response = tempMonitor.deploySensor(newSensor, new Location("montreal"), new Temperature(20));
        System.out.printf("response: %s\n", response);

        // deploy same sensor in different location
        System.out.println("------------- deploying same sensor in different location ------------------");
        response = tempMonitor.deploySensor(newSensor, new Location("vancouver"), new Temperature(20));
        System.out.printf("response: %s\n", response);

        // deploy new sensor in already covered location
        System.out.println("------------- deploying new sensor in already covered location ------------------");
        response = tempMonitor.deploySensor(new Sensor(22), new Location("montreal"), new Temperature(20));
        System.out.printf("response: %s\n", response);

        // deploy new sensor in new location
        System.out.println("------------- deploying new sensor in new location ------------------");
        response = tempMonitor.deploySensor(new Sensor(22), new Location("vancouver"), new Temperature(20));
        System.out.printf("response: %s\n", response);

        // Reading temperature of unknown location
        System.out.println("------------- reading temperature of unknown location ------------------");
        response = tempMonitor.readTemperature(new Location("florida"));
        System.out.printf("response: %s\n", response);


        // Reading temperature of known location
        System.out.println("------------- reading temperature of known location ------------------");
        response = tempMonitor.readTemperature(new Location("montreal"));
        System.out.printf("response: %s", response);


    }


}