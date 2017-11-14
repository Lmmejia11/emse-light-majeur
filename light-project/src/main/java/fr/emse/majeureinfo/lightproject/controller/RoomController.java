package fr.emse.majeureinfo.lightproject.controller;

import fr.emse.majeureinfo.lightproject.dao.springdao.RoomDao;
import fr.emse.majeureinfo.lightproject.dto.RoomDto;
import fr.emse.majeureinfo.lightproject.model.Room;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Transactional
public class RoomController {

    private final RoomDao roomDao;

    public RoomController(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @RequestMapping(value="/api/rooms", method = RequestMethod.GET)
    public List<RoomDto> list() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @RequestMapping(value="/api/rooms/{id}", method =RequestMethod.GET)
    public RoomDto get(@PathVariable("id") Long id){
        return new RoomDto(roomDao.getOne(id));
    }

    @RequestMapping(value="/api/rooms/{id}/switch-light", method =RequestMethod.GET)
    public RoomDto switchLight(@PathVariable("id") Long id){
        Room room = roomDao.getOne(id);
        room.switchLight();
        return new RoomDto(room);
    }

    @RequestMapping(value="/api/rooms/{id}/switch-ringer", method =RequestMethod.GET)
    public RoomDto switchRinger(@PathVariable("id") Long id){
        Room room = roomDao.getOne(id);
        room.switchRinger();
        return new RoomDto(room);
    }

    @RequestMapping(value="/api/rooms/on", method =RequestMethod.GET)
    public List<RoomDto> listWithOnLight() {
        return roomDao.findWithOnLight().stream().map(RoomDto::new).collect(Collectors.toList());
    }

}
