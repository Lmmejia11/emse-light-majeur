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
        this.id = building.getId();
        this.name = building.getName();
        this.n_rooms = building.getRooms().size() ;
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
