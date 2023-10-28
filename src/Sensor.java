public class Sensor {

    private boolean deployed = false;
    private int sensorID;

    private Location location;

    public Sensor(int sensorId){
        this.sensorID = sensorId;
    }

    public void setLocation(Location location){
        this.location = location;
    }

    public void setDeployed(boolean deployed){
        this.deployed = deployed;
    }


    public boolean isDeployed() {
        return deployed;
    }

    public int getSensorID() {
        return sensorID;
    }

    public Location getLocation() {
        return location;
    }


}
