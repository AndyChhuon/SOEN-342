public class Main {
    public static void main(String[] args) {
       TempMonitor tempMonitor = new TempMonitor();
        Sensor newSensor = new Sensor(21);
        tempMonitor.deploySensor(newSensor, new Location("Montreal"));
    }


}