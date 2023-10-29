import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if(checkIfSensorDeployed(sensor).equals(Message.SensorAlreadyDeployed.toString()))
            return Message.SensorAlreadyDeployed.toString();
        if (checkIfLocationCovered(location).equals(Message.LocationAlreadyCovered.toString()))
            return Message.LocationAlreadyCovered.toString();

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

    private String checkIfSensorDeployed(Sensor sensor){
    //To check if sensor is deployed, must check if sensor has a location
        String msg = null;
        try{
            sensor.getLocation().getLocationName();
            msg = Message.SensorAlreadyDeployed.toString();
            return msg;
            }
        catch (Exception e){
           msg = "Sensor has not been deployed";}
        return msg;

    }

    public String checkIfLocationCovered(Location location){
    // we need to check if location is in map hashmap
        String msg = null;

            if (map.containsValue(location)){
                System.out.println(location.getLocationName());
                msg = Message.LocationAlreadyCovered.label;
            }
            else{
                msg = ("not there");
            }
        return msg;

    }

    //TODO
    private String checkIfLocationUnknown(Location location){
        return Message.LocationUnknown.label;
    }
    private String succcess(){
        return Message.Success.label;
    }




}
