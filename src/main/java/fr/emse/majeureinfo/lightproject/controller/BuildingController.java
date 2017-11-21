package fr.emse.majeureinfo.lightproject.controller;

import fr.emse.majeureinfo.lightproject.dao.springdao.BuildingDao;
import fr.emse.majeureinfo.lightproject.dao.springdao.RoomDao;
import fr.emse.majeureinfo.lightproject.dto.BuildingDetailDto;
import fr.emse.majeureinfo.lightproject.model.Building;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Transactional
@RequestMapping("/api/buildings")
public class BuildingController {

    private final BuildingDao buildingDao;

    public BuildingController(RoomDao roomDao, BuildingDao buildingDao) {
        this.buildingDao = buildingDao;
    }

    @PostMapping(value="/")
    public List<BuildingDetailDto> listBuildings(){
        List<Building> buildings = buildingDao.findAll();
        return buildings.stream().map(BuildingDetailDto::new).collect(Collectors.toList());
    }
}
