package fr.emse.majeureinfo.lightproject.dto;

import fr.emse.majeureinfo.lightproject.model.Light;
import fr.emse.majeureinfo.lightproject.model.Noise;
import fr.emse.majeureinfo.lightproject.model.Room;

public class RoomDto {

    private Long id;
    private Light light;
    private Noise noise;

    public RoomDto(Room room ){
        this.id = room.getId();
        this.light = room.getLight();
        this.noise = room.getNoise();
    }

    public Long getId() {
        return id;
    }

    public Light getLight() {
        return light;
    }

    public Noise getNoise() {
        return noise;
    }
}
