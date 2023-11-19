import java.util.*;

public class TempMonitor {
    private final List<Sensor> deployed = new ArrayList<>();
    private final HashMap<Sensor, Location> map = new HashMap<>();
    private final HashMap<Sensor, Temperature> read = new HashMap<>();
    private final HashMap<Location, Temperature> LocationTemp = new HashMap<>();

  // Constructor
    public TempMonitor() {
        this.sensorCatalog = new SensorCatalog();
        this.temperatureCatalog = new TemperatureCatalog();
        this.locationCatalog = new LocationCatalog();
        this.deploymentCatalog = new DeploymentCatalog();
        
    }
    
    // Replace a sensor
    public String replaceSensor(Sensor sensorToReplace) {
        // Check if the sensor to replace is deployed
        String sensorDeployedStatus = checkIfSensorDeployed(sensorToReplace);
        if (!Objects.isNull(sensorDeployedStatus)) {
            // Create a new sensor to replace the old one
            Sensor newSensor = makeSensor();

            // Remove the old sensor from the catalogs
            sensorCatalog.removeSensor(sensorToReplace);
            deploymentCatalog.removeSensor(sensorToReplace);

            // Add the new sensor to the catalogs
            sensorCatalog.addSensor(newSensor);
            deploymentCatalog.deploySensor(newSensor, sensorToReplace.getLocation(), getTemperatureForReplacement());

            return success();
        } else {
            // If the sensor is not deployed, return an appropriate message
            return sensorDeployedStatus;
        }
    }

    public String deploySensor(Sensor sensor, Location location, Temperature temperature){
        // return success, alreadyDeployed, or locationAlreadyCovered
        String sensorAlreadyDeployed = checkIfSensorDeployed(sensor);
        if(!Objects.isNull(sensorAlreadyDeployed))
            return sensorAlreadyDeployed;

        String locationAlreadyCovered = checkIfLocationCovered(location);
        if (!Objects.isNull(locationAlreadyCovered))
            return locationAlreadyCovered;

        deploySensorOk(sensor, location, temperature);
        return success();
    }

    public String readTemperature(Location location){
        Temperature locationTemperature = readTemperatureOk(location);
        if(!Objects.isNull(locationTemperature)){
            System.out.printf("Temperature at %s was found to be %s\n", location.getLocationName(), locationTemperature.getTemperature());
            return success();
        }else{
            return checkIfLocationUnknown(location);
        }
    }

    private Temperature readTemperatureOk(Location location){
        if(!Objects.isNull(checkIfLocationCovered(location))){
            Optional<Sensor> findMatchingSensor = map.entrySet()
                    .stream()
                    .filter(entry -> location.getLocationName().equals(entry.getValue().getLocationName()))
                    .map(Map.Entry::getKey)
                    .findFirst();
            if (findMatchingSensor.isPresent()) {
                return read.get(findMatchingSensor.get());
            }
        }

        return null;
    }


    private void deploySensorOk(Sensor sensor, Location location, Temperature temperature){
        if(Objects.isNull(checkIfSensorDeployed(sensor)) && Objects.isNull(checkIfLocationCovered(location))){
            deployed.add(sensor);
            map.put(sensor, location);
            read.put(sensor, temperature);
            LocationTemp.put(location,temperature);
            sensor.setLocation(location);
            sensor.setDeployed(true);
            System.out.printf("Sensor was deployed at %s\n", location.getLocationName());

        }

    }

    private String checkIfSensorDeployed(Sensor sensor){
        //To check if sensor is deployed, must check if sensor has a location
        if(sensor.isDeployed()){
            return Message.SensorAlreadyDeployed.label;
        }else{
            return null;
        }

    }

    public String checkIfLocationCovered(Location location){
        // we need to check if location is in map hashmap
        if (map.entrySet()
                .stream()
                .anyMatch(entry -> location.getLocationName().equals(entry.getValue().getLocationName()))){
            return Message.LocationAlreadyCovered.label;
        }
        else{
            return null;
        }

    }

    private String checkIfLocationUnknown(Location location){
        // we need to check that location is not in map
        if (map.entrySet()
                .stream()
                .noneMatch(entry -> location.getLocationName().equals(entry.getValue().getLocationName()))){
            return Message.LocationUnknown.label;
        }
        else
            return null;
    }
    private String success(){
        return Message.Success.label;
    }

    public HashMap<Location, Temperature> getLocationTemp() {
        //getting location temperature pairs
        return LocationTemp;
    }
}
