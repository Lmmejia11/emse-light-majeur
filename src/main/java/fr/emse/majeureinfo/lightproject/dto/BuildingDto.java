package fr.emse.majeureinfo.lightproject.dto;

import fr.emse.majeureinfo.lightproject.model.Building;
import fr.emse.majeureinfo.lightproject.model.Light;
import fr.emse.majeureinfo.lightproject.model.Noise;
import fr.emse.majeureinfo.lightproject.model.Room;

import java.util.List;

public class BuildingDto {

    private Long id;
    private String name;
    private List<Room> rooms;

    public BuildingDto(Building building){
        this.id = building.getId();
        this.rooms = building.getRooms();
        this.name = building.getName();
    }

    public Long getId() {     return id;  }
    public List<Room> getRooms() {
        return rooms;
    }
    public String getName() {    return name;    }
}
