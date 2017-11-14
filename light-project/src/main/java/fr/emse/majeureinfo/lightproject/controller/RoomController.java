package fr.emse.majeureinfo.lightproject.controller;

import fr.emse.majeureinfo.lightproject.dao.springdao.RoomDao;
import fr.emse.majeureinfo.lightproject.dao.springdao.LightDao;
import fr.emse.majeureinfo.lightproject.dto.RoomDto;
import fr.emse.majeureinfo.lightproject.model.Room;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Transactional
public class RoomController {

    private final RoomDao roomDao;
    private final LightDao lightDao;

    public RoomController(RoomDao roomDao, LightDao lightDao) {
        this.roomDao = roomDao;
        this.lightDao = lightDao;
    }

    @RequestMapping(value="/api/rooms", method = RequestMethod.GET)
    public List<RoomDto> list() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @RequestMapping(value="/api/rooms/{id}", method =RequestMethod.GET)
    public Room get(@PathVariable Long id){
        return roomDao.getOne(id);
    }

    @RequestMapping(value="/api/rooms/{id}/switch", method =RequestMethod.GET)
    public Room switchLight(@PathVariable Long id){ return null; // TODO
    }

    @RequestMapping(value="/api/rooms/{id}/ringer", method =RequestMethod.GET)
    public Room switchRinger(Long id){
        return null; // TODO
    }

    @RequestMapping(value="/api/rooms/on", method =RequestMethod.GET)
    public List<RoomDto> listWithOnLight() {
        return roomDao.findWithOnLight().stream().map(RoomDto::new).collect(Collectors.toList());
    }

}
