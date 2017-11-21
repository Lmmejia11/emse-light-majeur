package fr.emse.majeureinfo.lightproject.dto;

import fr.emse.majeureinfo.lightproject.model.Building;
import fr.emse.majeureinfo.lightproject.model.Light;
import fr.emse.majeureinfo.lightproject.model.Room;

import java.util.List;

public class BuildingDetailDto {

    private Long id;
    private String name;
    private int n_rooms;
    private int n_lightOn;
    private int n_noiseOn;

    public BuildingDetailDto(Building building){
        this.id = building.getId();
        this.name = building.getName();
        this.n_rooms = 0;
        this.n_lightOn = 0;
        this.n_noiseOn = 0;
        calculateDetails(building.getRooms());
    }
    private void calculateDetails(List<Room> rooms ){

        for (Room r:rooms){
            if (r.getLight().getStatus() == Light.Status.ON) {
                n_lightOn += 1;
            }
            if (r.getLight().getStatus() == Light.Status.ON) {
                n_noiseOn += 1;
            }
            n_rooms += 1;
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getN_rooms() {
        return n_rooms;
    }

    public int getN_lightOn() {
        return n_lightOn;
    }

    public int getN_noiseOn() {
        return n_noiseOn;
    }
}
