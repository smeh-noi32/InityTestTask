package org.example;

class TrafficQueue {
    private int vehicles;
    private int pedestrians;

    public TrafficQueue(int vehicles, int pedestrians) {
        this.vehicles = vehicles;
        this.pedestrians = pedestrians;
    }

    public int getVehicles() {
        return vehicles;
    }

    public void setVehicles(int vehicles) {
        this.vehicles = vehicles;
    }

    public int getPedestrians() {
        return pedestrians;
    }

    public void setPedestrians(int pedestrians) {
        this.pedestrians = pedestrians;
    }

    public void decreaseVehicle() {
        if (vehicles > 0) vehicles--;
    }

    public void decreasePedestrian() {
        if (pedestrians > 0) pedestrians--;
    }
}