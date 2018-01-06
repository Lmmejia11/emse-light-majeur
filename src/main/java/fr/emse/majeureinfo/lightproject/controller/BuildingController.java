package fr.emse.majeureinfo.lightproject.controller;

import fr.emse.majeureinfo.lightproject.LightProjectApplication;
import fr.emse.majeureinfo.lightproject.dao.springdao.BuildingDao;
import fr.emse.majeureinfo.lightproject.dao.springdao.RoomDao;
import fr.emse.majeureinfo.lightproject.dto.BuildingDetailDto;
import fr.emse.majeureinfo.lightproject.dto.BuildingDto;
import fr.emse.majeureinfo.lightproject.dto.RoomDto;
import fr.emse.majeureinfo.lightproject.model.Building;
import fr.emse.majeureinfo.lightproject.model.Room;
import fr.emse.majeureinfo.lightproject.mqtt_client.Subscriber;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping
    public List<BuildingDetailDto> listBuildings(){
        List<Building> buildings = buildingDao.findAll();
        return buildings.stream().map(BuildingDetailDto::new).collect(Collectors.toList());
    }

    @GetMapping(value="/{id}")
    public BuildingDto get(@PathVariable("id") Long id){
        return new BuildingDto(buildingDao.getOne(id));
    }

    @GetMapping(value="/ids")
    public List<Long> getIds(){
        return buildingDao.listBuildingsIds();
    }
}
