package br.com.fiap.thermoscope.models.enums;

public enum Level {
    LOW("low"),
    MEDIUM("medium"),
    HIGH("high");

    private final String level;

    Level(String level) {
        this.level = level;
    }
}
