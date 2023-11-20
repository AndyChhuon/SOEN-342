
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
        System.out.printf("response: %s\n", response);


        Sensor newSensor2 = new Sensor(12);
        Sensor newSensor3 = new Sensor(15);

        System.out.println("\n\n-------------Iteration 2 ------------------\n\n");

        // Replacing sensor not deployed yet
        System.out.println("------------- Replacing not deployed sensor ------------------");
        response = tempMonitor.replaceSensor(newSensor2,newSensor3);
        System.out.printf("response: %s\n", response);

        // Replacing valid deployed sensor
        System.out.println("------------- Replacing valid deployed sensor ------------------");
        response = tempMonitor.replaceSensor(newSensor,newSensor3);
        System.out.printf("response: %s\n", response);


    }


}