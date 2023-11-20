import java.util.*;

public class TempMonitor {
    private final List<Sensor> deployed = new ArrayList<>();
    private final HashMap<Sensor, Location> map = new HashMap<>();
    private final HashMap<Sensor, Temperature> read = new HashMap<>();
    private final HashMap<Location, Temperature> LocationTemp = new HashMap<>();



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

    public String replaceSensor(Sensor sensorToReplace, Sensor newSensor){
        String sensorNotDeployed = checkIfSensorNotDeployed(sensorToReplace);
        String newSensorAlreadyDeployed = checkIfSensorDeployed(newSensor);
        if(!Objects.isNull(sensorNotDeployed)){
            return sensorNotDeployed;

        }else if(!Objects.isNull(newSensorAlreadyDeployed)) {
            return newSensorAlreadyDeployed;
        }
        else{
            replaceSensorOk(sensorToReplace,newSensor);
            return success();
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
            LocationTemp.put(location, temperature);
            sensor.setLocation(location);
            sensor.setDeployed(true);
            System.out.printf("Sensor was deployed at %s\n", location.getLocationName());

        }

    }


    private void replaceSensorOk(Sensor sensorToReplace, Sensor newSensor){
        System.out.println("-------------------Before replaceSensorOk--------------------");
        printDeployed();
        printSensorLocations();
        printSensorTemperatures();

        //Replace in deployed
        deployed.stream()
                .filter(sensor -> sensor.getSensorID() == sensorToReplace.getSensorID())
                .findFirst()
                .ifPresent(sensor -> deployed.set(deployed.indexOf(sensor), newSensor));

        //Replace in map
        map.entrySet().stream()
                .filter(entry -> entry.getKey().getSensorID() == sensorToReplace.getSensorID())
                .findFirst()
                .ifPresent(entry -> {
                    Location previousLocation = entry.getValue();
                    newSensor.setLocation(previousLocation);
                    map.remove(entry.getKey());
                    map.put(newSensor, previousLocation);
                });

        //Replace in read
        read.entrySet().stream()
                .filter(entry -> entry.getKey().getSensorID() == sensorToReplace.getSensorID())
                .findFirst()
                .ifPresent(entry -> {
                    Temperature previousTemperature = entry.getValue();
                    read.remove(entry.getKey());
                    read.put(newSensor, previousTemperature);
                });
        newSensor.setDeployed(true);


        System.out.println("-------------------After replaceSensorOk--------------------");
        printDeployed();
        printSensorLocations();
        printSensorTemperatures();
    }

    private void printDeployed(){
        System.out.println("-----Deployed-------");
        for (Sensor sensor : deployed) {
            System.out.println("Sensor ID: " + sensor.getSensorID());
        }
    }

    public void printSensorLocations() {
        System.out.println("-----Sensor Location Pairs-------");
        for (Map.Entry<Sensor, Location> entry : map.entrySet()) {
            Sensor sensor = entry.getKey();
            Location location = entry.getValue();
            System.out.println("Sensor ID: " + sensor.getSensorID() + ", Location: " + location.getLocationName());
        }
    }

    public void printSensorTemperatures() {
        System.out.println("-----Sensor Temperature Pairs-------");
        for (Map.Entry<Sensor, Temperature> entry : read.entrySet()) {
            Sensor sensor = entry.getKey();
            Temperature temperature = entry.getValue();

            int sensorID = sensor.getSensorID();
            double tempValue = temperature.getTemperature();

            System.out.println("Sensor ID: " + sensorID + ", Temperature: " + tempValue);
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

    private String checkIfSensorNotDeployed(Sensor sensor){
        //returns null if is deployed
        if(sensor.isDeployed()){
            return null;
        }else{
            return Message.SensorNotDeployed.label;
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

    public void printLocationTemp() {
        System.out.println("-----Location Temperature Pairs-------");
        for (Map.Entry<Location, Temperature> entry : LocationTemp.entrySet()) {
            Location location = entry.getKey();
            Temperature temperature = entry.getValue();
            System.out.println("Location: " + location.getLocationName() + ", Temperature: " + temperature.getTemperature());
        }
    }
}
