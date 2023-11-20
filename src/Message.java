public enum Message {
    Success("ok"),
    SensorAlreadyDeployed("Sensor deployed"),
    LocationAlreadyCovered("Location already covered"),
    LocationUnknown("Location not covered"),
    SensorNotDeployed("Sensor not deployed");
    public final String label;

    private Message(String label) {
        this.label = label;
    }
}
