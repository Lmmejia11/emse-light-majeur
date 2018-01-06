package fr.emse.majeureinfo.lightproject.dao;

import fr.emse.majeureinfo.lightproject.dto.BuildingDetailDto;
import fr.emse.majeureinfo.lightproject.model.Building;

import java.util.List;

public interface BuildingDaoCustom {
    public List<BuildingDetailDto> listBuildingsDetails();
    public List<Building> listBuildingsLightOn();
    public List<Long> listBuildingsIds();
}
