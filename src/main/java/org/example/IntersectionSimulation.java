package org.example;

import java.util.ArrayList;
import java.util.List;

public class IntersectionSimulation {
    private final List<TrafficLight> trafficLights;

    public IntersectionSimulation() {
        trafficLights = new ArrayList<>();
        trafficLights.add(new TrafficLight(1, 10, 5));
        trafficLights.add(new TrafficLight(2, 15, 3));
        trafficLights.add(new TrafficLight(3, 7, 8));
        trafficLights.add(new TrafficLight(4, 12, 6));
    }

    public void runSimulation(int steps) {
        for (int i = 0; i < steps; i++) {
            System.out.println("Step " + (i + 1));
            for (TrafficLight light : trafficLights) {
                light.process();
                System.out.println("TrafficLight " + light.getId() + " - State: " + light.getState() + ", Queue: " + light.getTrafficQueue().getVehicles());
            }
            for (TrafficLight light : trafficLights) {
                for (TrafficLight target : trafficLights) {
                    if (light != target) {
                        light.sendEvent(target);
                    }
                }
            }
        }
    }
}
