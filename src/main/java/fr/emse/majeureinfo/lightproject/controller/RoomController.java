package fr.emse.majeureinfo.lightproject.controller;

import fr.emse.majeureinfo.lightproject.LightProjectApplication;
import fr.emse.majeureinfo.lightproject.dao.springdao.RoomDao;
import fr.emse.majeureinfo.lightproject.dto.RoomDto;
import fr.emse.majeureinfo.lightproject.model.Light;
import fr.emse.majeureinfo.lightproject.model.Noise;
import fr.emse.majeureinfo.lightproject.model.Room;
import fr.emse.majeureinfo.lightproject.mqtt_client.Subscriber;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping(value="/{id}/switch-light/all", method = RequestMethod.POST)
    public List<RoomDto> switchLightReturnAll(@PathVariable("id") Long id, @RequestBody(required=false) String s) throws MqttException {
        List<Room> rooms = roomDao.findAll();
        Room room = findRoomWithId(rooms,id);
        if (s == null) room.switchLight();
        else room.getLight().setStatus(Light.Status.valueOf(s));
        return rooms.stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}/switch-light", method = RequestMethod.POST)
    public RoomDto switchLight(@PathVariable("id") Long id, @RequestBody(required=false) String s) throws MqttException {
        Room room = roomDao.findOne(id);
        if (s == null) room.switchLight();
        else room.getLight().setStatus(Light.Status.valueOf(s));
        return new RoomDto(room);
    }

    @RequestMapping(value="/{id}/switch-ringer/all", method = RequestMethod.POST)
    public List<RoomDto> switchRingerReturnAll(@PathVariable("id") Long id, @RequestBody(required=false) String s) throws MqttException {
        List<Room> rooms = roomDao.findAll();
        Room room = findRoomWithId(rooms,id);
        if (s == null) room.switchRinger();
        else room.getNoise().setStatus(Light.Status.valueOf(s));
        return rooms.stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @RequestMapping(value="/{id}/switch-ringer", method = RequestMethod.POST)
    public RoomDto switchRinger(@PathVariable("id") Long id, @RequestBody(required=false) String s) throws MqttException {
        Room room = roomDao.findOne(id);
        if (s == null) room.switchRinger();
        else room.getNoise().setStatus(Light.Status.valueOf(s));
        return new RoomDto(room);
    }

    @PostMapping(value="/{id}/update-lightlevel/{light}")
    public RoomDto updateLightLevel(@PathVariable("id") Long id , @PathVariable("light") Integer lightLevel) throws MqttException {
        Room room = roomDao.findOne(id);
        room.setLightLevel(lightLevel);
        return new RoomDto(room);
    }

    @PostMapping(value="/{id}/update-lightlevel/{light}/all")
    public List<RoomDto> updateLightLevelReturnAll(@PathVariable("id") Long id , @PathVariable("light") Integer lightLevel ) throws MqttException {
        List<Room> rooms = roomDao.findAll();
        Room room = findRoomWithId(rooms,id);
        room.setLightLevel(lightLevel);
        return rooms.stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @PostMapping(value="/{id}/update-noiselevel/{light}")
    public RoomDto updateNoiseLevel(@PathVariable("id") Long id , @PathVariable("noise") Integer noiseLevel) throws MqttException {
        Room room = roomDao.findOne(id);
        room.setNoiseLevel(noiseLevel);
        return new RoomDto(room);
    }

    @PostMapping(value="/{id}/update-noiselevel/{light}/all")
    public List<RoomDto> updateNoiseLevelReturnAll(@PathVariable("id") Long id , @PathVariable("noise") Integer noiseLevel) throws MqttException {
        List<Room> rooms = roomDao.findAll();
        Room room = findRoomWithId(rooms,id);
        room.setNoiseLevel(noiseLevel);
        return rooms.stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(value="/Ringers/on")
    public List<RoomDto> listWithOnRinger() {
        return roomDao.findWithOnRinger().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(value="Lights/on")
    public List<RoomDto> listWithOnLight() {
        return roomDao.findWithOnLight().stream().map(RoomDto::new).collect(Collectors.toList());
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

    // TODO For web page, get it refresh every 10 seconds

}
