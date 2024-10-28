package org.example;

class TrafficEvent {
    private final int senderId;
    private final int queueLength;
    private final TrafficLightState state;

    public TrafficEvent(int senderId, int queueLength, TrafficLightState state) {
        this.senderId = senderId;
        this.queueLength = queueLength;
        this.state = state;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getQueueLength() {
        return queueLength;
    }

    public TrafficLightState getState() {
        return state;
    }
}