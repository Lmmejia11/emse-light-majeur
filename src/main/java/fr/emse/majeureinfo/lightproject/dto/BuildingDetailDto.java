package fr.emse.majeureinfo.lightproject.dto;

import fr.emse.majeureinfo.lightproject.model.Building;
import fr.emse.majeureinfo.lightproject.model.Light;
import fr.emse.majeureinfo.lightproject.model.Room;

import java.util.List;

public class BuildingDetailDto {

    private Long id;
    private String name;
    private int n_rooms;

    public BuildingDetailDto(Building building){
        this(building.getId(),building.getName(),building.getRooms().size()) ; }
    public BuildingDetailDto(Long id, String name, Long n_rooms) {
        this(id,name,n_rooms.intValue());   }
    public BuildingDetailDto(Long id, String name, int n_rooms) {
        this.id = id;
        this.name = name;
        this.n_rooms = n_rooms;
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
}
