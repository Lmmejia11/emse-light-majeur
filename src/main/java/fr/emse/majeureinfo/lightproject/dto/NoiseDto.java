package fr.emse.majeureinfo.lightproject.dto;

import fr.emse.majeureinfo.lightproject.model.Light;

public class NoiseDto {

    private final Long id;
    private final Integer level;
    private final Light.Status status;

    public NoiseDto(Light light) {
        this.id = light.getId();
        this.level = light.getLevel();
        this.status = light.getStatus();
    }

    public Long getId() {
        return id;
    }

    public Integer getLevel() {
        return level;
    }

    public Light.Status getStatus() {
        return status;
    }
}
