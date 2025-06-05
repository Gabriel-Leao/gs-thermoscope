package br.com.fiap.thermoscope.models.enums;

public enum ConfirmedHeatSource {
    Y("y"),
    N("n");

    private final String confirmedHeatSource;

    ConfirmedHeatSource(String confirmedHeatSource) {
        this.confirmedHeatSource = confirmedHeatSource;
    }
}
