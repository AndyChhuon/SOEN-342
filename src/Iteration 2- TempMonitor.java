public class TempMonitor {
    private SensorCatalog sensorCatalog;
    private TemperatureCatalog temperatureCatalog;
    private LocationCatalog locationCatalog;
    private DeploymentCatalog deploymentCatalog;

    // Constructor
    public TempMonitor() {
        this.sensorCatalog = new SensorCatalog();
        this.temperatureCatalog = new TemperatureCatalog();
        this.locationCatalog = new LocationCatalog();
        this.deploymentCatalog = new DeploymentCatalog();
    }

    // Deploy a sensor at a location with a given temperature
    public void deploySensor(Sensor sensor, Location location, Temperature temperature) {
        sensorCatalog.addSensor(sensor);
        locationCatalog.addLocation(location);
        temperatureCatalog.addTemperature(temperature);
        deploymentCatalog.deploySensor(sensor, location, temperature);
    }

    // Read temperature at a specific location
    public Temperature readTemperature(Location location) {
        return deploymentCatalog.readTemperature(location);
    }

    // Replace a sensor
    public void replaceSensor(Sensor sensorToReplace) {
        Sensor newSensor = makeNewSensor(); 
        sensorCatalog.addSensor(newSensor);
        deploymentCatalog.replaceSensor(sensorToReplace, newSensor);
    }

    // Get sensor information by location
    public Sensor getSensorByLocation(Location location) {
        return deploymentCatalog.getSensorByLocation(location);
    }

    // Get temperature by sensor
    public Temperature getTemperatureBySensor(Sensor sensor) {
        return deploymentCatalog.getTemperatureBySensor(sensor);
    }

    // Get locations and temperatures
    public Map<Location, Temperature> getLocationsAndTemperatures() {
        return deploymentCatalog.getLocationsAndTemperatures();
    }

    // Create a new sensor
    private Sensor makeNewSensor() {
     
        return new Sensor();
    }

    // Create a new location
    private Location makeNewLocation(String location) {
        return new Location(location);
    }

    // Create a new temperature
    private Temperature makeNewTemperature(int temperature) {
          return new Temperature(temperature);
    }
}
