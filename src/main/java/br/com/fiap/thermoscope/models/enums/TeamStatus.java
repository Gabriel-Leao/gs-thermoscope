package br.com.fiap.thermoscope.models.enums;

public enum TeamStatus {
    AVAILABLE("available"),
    ON_MISSION("onMission"),
    INACTIVE("inactive");

    private final String status;

    TeamStatus(String status) {
        this.status = status;
    }
}
