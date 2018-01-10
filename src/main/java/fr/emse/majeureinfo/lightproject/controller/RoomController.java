package fr.emse.majeureinfo.lightproject.controller;

import fr.emse.majeureinfo.lightproject.dao.springdao.RoomDao;
import fr.emse.majeureinfo.lightproject.dto.RoomDto;
import fr.emse.majeureinfo.lightproject.model.Light;
import fr.emse.majeureinfo.lightproject.model.Noise;
import fr.emse.majeureinfo.lightproject.model.Room;
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
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomDao roomDao;

    public RoomController(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @GetMapping
    public List<RoomDto> list() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(value="/{id}")
    public RoomDto get(@PathVariable("id") Long id){
        return new RoomDto(roomDao.getOne(id));
    }

    @PostMapping(value="/{id}/switch-light")
    public List<RoomDto> switchLight(@PathVariable("id") Long id){
        List<Room> rooms = roomDao.findAll();
        findRoomWithId(rooms,id).switchLight();
        return rooms.stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @PostMapping(value="/{id}/switch-ringer")
    public List<RoomDto> switchRinger(@PathVariable("id") Long id){
        List<Room> rooms = roomDao.findAll();
        findRoomWithId(rooms,id).switchRinger();
        return rooms.stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(value="Lights/on")
    public List<RoomDto> listWithOnLight() {
        return roomDao.findWithOnLight().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(value="/Ringers/on")
    public List<RoomDto> listWithOnRinger() {
        return roomDao.findWithOnRinger().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(value="Lights/off")
    public List<RoomDto> listWithOffLight() {
        return roomDao.findWithOffLight().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(value="/Ringers/off")
    public List<RoomDto> listWithOffRinger() {
        return roomDao.findWithOffRinger().stream().map(RoomDto::new).collect(Collectors.toList());
    }


    @PostMapping(value="/switch-all-lights/OFF")
    public List<RoomDto> switchOffAllLights(){
        List<Room> rooms = roomDao.findAll();
        Light.Status on = Light.Status.ON;
        for (Room r: rooms){
            if( r.getLight().getStatus().equals(on)){
                r.switchLight();
            }
        }
        return rooms.stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @PostMapping(value="/switch-all-lights/ON")
    public List<RoomDto> switchOnAllLights(){
        List<Room> rooms = roomDao.findAll();
        Light.Status off = Light.Status.OFF;
        for (Room r: rooms){
            if( r.getLight().getStatus().equals(off)){
                r.switchLight();
            }
        }
        return rooms.stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @PostMapping(value="/switch-all-ringers/OFF")
    public List<RoomDto> switchOffAllRingers(){
        List<Room> rooms = roomDao.findAll();
        Light.Status on = Light.Status.ON;
        for (Room r: rooms){
            if( r.getNoise().getStatus().equals(on)){
                r.switchRinger();
            }
        }
        return rooms.stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @PostMapping(value="/switch-all-ringers/ON")
    public List<RoomDto> switchOnAllRingers(){
        List<Room> rooms = roomDao.findAll();
        Light.Status off = Light.Status.OFF;
        for (Room r: rooms){
            if( r.getNoise().getStatus().equals(off)){
                r.switchRinger();
            }
        }
        return rooms.stream().map(RoomDto::new).collect(Collectors.toList());
    }



    private Room findRoomWithId(List<Room> rooms, Long id){
        for (Room r: rooms){
            if ( r.getId() == id) {return r;}
        }
        return null;
    }

}
