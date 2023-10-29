import java.util.HashMap;
import java.util.List;

public class TempMonitor {
    private List<Sensor> deployed;
    private HashMap<Sensor, Location> map;
    private HashMap<Sensor, Temperature> read;
    
//constructor
public TempMonitor(List<Sensor> deployed, HashMap<Sensor, Location> map, HashMap<Sensor, Temperature> read) {
        this.deployed = deployed;
        this.map = map;
        this.read = read;
    }
    
    //TODO
    public String deploySensor(Sensor sensor, Location location){
        // return success, alreadyDeployed, or locationAlreadyCovered
        return "";
    }

    //TODO
    public String readTemperature(Location location){
        // return success, or locationUnkown
        return "";
    }

    //TODO
    private Temperature readTemperatureOk(Location location){
        return null;
    }

  
    private void deploySensorOk(Sensor sensor, Location location){
        deployed.add(sensor);
        map.put(sensor, location);
        read.put(sensor, new Temperature(0));
        sensor.setLocation(location);
        sensor.setDeployed(true);
    }
    //TODO
    private String checkIfSensorDeployed(Sensor sensor){
        return Message.SensorAlreadyDeployed.label;
    }

    //TODO
    private String checkIfLocationCovered(Location location){
        return Message.LocationAlreadyCovered.label;
    }

    //TODO
    private String checkIfLocationUnknown(Location location){
        return Message.LocationUnknown.label;
    }
    private String succcess(){
        return Message.Success.label;
    }




}
