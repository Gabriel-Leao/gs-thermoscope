package br.com.fiap.thermoscope.models.enums;

public enum IncidentStatus {
    PENDING("pending"),
    IN_PROGRESS("inProgress"),
    COMPLETED("completed"),
    CANCELLED("cancelled");

    private final String status;

    IncidentStatus(String incidentStatus) {
        this.status = incidentStatus;
    }
}
