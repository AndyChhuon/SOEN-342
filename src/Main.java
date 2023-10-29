import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
       TempMonitor tempMonitor = new TempMonitor(null, null,null);
       Sensor newSensor = new Sensor(21);
       tempMonitor.deploySensor(newSensor, new Location("Montreal"));



       //TESTING...TO DELETE
       Sensor testSensor = new Sensor(22);
       Sensor testSensorNoLoc = new Sensor(23);
       Location testLocation = new Location("Canada");
       testSensor.setLocation(testLocation);
       Sensor othersensor = new Sensor(10);
       Location otherlocation = new Location("USA");
       othersensor.setLocation(otherlocation);

//       Map<Sensor, Location> testHM  = new HashMap<Sensor, Location>() {{
//          put(testSensor, testLocation);
//          put(newSensor, otherlocation);
//       }};
       System.out.println(tempMonitor.deploySensor(testSensor,testLocation));






    }


}