public enum Message {
    Success("ok"),
    SensorAlreadyDeployed("Sensor deployed"),
    LocationAlreadyCovered("Location already covered"),
    LocationUnknown("Location not covered");
    public final String label;

    private Message(String label) {
        this.label = label;
    }
}
