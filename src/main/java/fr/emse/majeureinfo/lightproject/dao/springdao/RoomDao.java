package fr.emse.majeureinfo.lightproject.dao.springdao;

import fr.emse.majeureinfo.lightproject.dao.RoomDaoCostom;
import fr.emse.majeureinfo.lightproject.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDao extends JpaRepository<Room, Long>, RoomDaoCostom {

}
