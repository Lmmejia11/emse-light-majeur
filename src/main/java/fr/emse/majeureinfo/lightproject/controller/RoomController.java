package fr.emse.majeureinfo.lightproject.controller;

import fr.emse.majeureinfo.lightproject.LightProjectApplication;
import fr.emse.majeureinfo.lightproject.dao.springdao.RoomDao;
import fr.emse.majeureinfo.lightproject.dto.RoomDto;
import fr.emse.majeureinfo.lightproject.model.Light;
import fr.emse.majeureinfo.lightproject.model.Room;
import fr.emse.majeureinfo.lightproject.mqtt_client.Subscriber;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @PostMapping(value="/{id}/switch-light/all")
    public List<RoomDto> switchLightReturnAll(@PathVariable("id") Long id) throws MqttException {
        List<Room> rooms = roomDao.findAll();
        findRoomWithId(rooms,id).switchLight();
        return rooms.stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @PostMapping(value="/{id}/switch-light")
    public RoomDto switchLight(@PathVariable("id") Long id) throws MqttException {
        Room room = roomDao.findOne(id);
        room.switchLight();
        return new RoomDto(room);
    }

    @PostMapping(value="/{id}/switch-ringer/all")
    public List<RoomDto> switchRingerReturnAll(@PathVariable("id") Long id) throws MqttException {
        List<Room> rooms = roomDao.findAll();
        Room room = findRoomWithId(rooms,id);
        room.switchRinger();
        return rooms.stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @PostMapping(value="/{id}/switch-ringer")
    public RoomDto switchRinger(@PathVariable("id") Long id) throws MqttException {
        Room room = roomDao.findOne(id);
        room.switchRinger();
        return new RoomDto(room);
    }

    @GetMapping(value="/on")
    public List<RoomDto> listWithOnLight() {
        return roomDao.findWithOnLight().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    private Room findRoomWithId(List<Room> rooms, Long id){
        for (Room r: rooms){
            if ( r.getId() == id) {return r;}
        }
        return null;
    }

}
