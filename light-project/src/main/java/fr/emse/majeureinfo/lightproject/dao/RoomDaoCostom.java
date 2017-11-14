package fr.emse.majeureinfo.lightproject.dao;

import fr.emse.majeureinfo.lightproject.model.Room;

import java.util.List;

public interface RoomDaoCostom {

    List<Room> findWithOnLight();
}
