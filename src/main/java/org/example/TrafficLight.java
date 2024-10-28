package org.example;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TrafficLight {
    private final int id;
    private TrafficLightState state;
    private final TrafficQueue trafficQueue;
    private int greenTime = 30;    // стандартное время зеленого сигнала в секундах
    private final int yellowTime = 5;    // стандартное время желтого сигнала в секундах
    private final int minGreenTime = 20; // минимальное время зеленого сигнала
    private final int maxGreenTime = 60; // максимальное время зеленого сигнала
    private final Queue<TrafficEvent> eventQueue = new ConcurrentLinkedQueue<>();

    public TrafficLight(int id, int initialVehicles, int initialPedestrians) {
        this.id = id;
        this.state = TrafficLightState.RED;
        this.trafficQueue = new TrafficQueue(initialVehicles, initialPedestrians);
    }

    public TrafficLightState getState() {
        return state;
    }

    public void setState(TrafficLightState state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public TrafficQueue getTrafficQueue() {
        return trafficQueue;
    }

    public void process() {
        while (!eventQueue.isEmpty()) {
            TrafficEvent event = eventQueue.poll();
            adaptGreenTime(event.getQueueLength());
        }

        switch (state) {
            case RED:
                if (trafficQueue.getVehicles() > 0) {
                    state = TrafficLightState.GREEN;
                    setGreenTime();
                }
                break;
            case GREEN:
                greenTime--;
                trafficQueue.decreaseVehicle();
                if (greenTime <= 0) {
                    state = TrafficLightState.YELLOW;
                }
                break;
            case YELLOW:
                if (yellowTime > 0) {
                    state = TrafficLightState.RED;
                }
                break;
        }
    }

    private void setGreenTime() {
        greenTime = Math.min(maxGreenTime, Math.max(minGreenTime, trafficQueue.getVehicles() * 2));
    }

    private void adaptGreenTime(int otherQueueLength) {
        if (otherQueueLength > trafficQueue.getVehicles() * 1.5) {
            greenTime = Math.min(maxGreenTime, greenTime + 10);
        } else if (otherQueueLength < trafficQueue.getVehicles() / 2) {
            greenTime = Math.max(minGreenTime, greenTime - 10);
        }
    }

    public void sendEvent(TrafficLight target) {
        TrafficEvent event = new TrafficEvent(id, trafficQueue.getVehicles(), state);
        target.receiveEvent(event);
    }

    public void receiveEvent(TrafficEvent event) {
        eventQueue.add(event);
    }


}