package fr.emse.majeureinfo.lightproject.controller;

import fr.emse.majeureinfo.lightproject.dao.RoomDao;
import fr.emse.majeureinfo.lightproject.dto.RoomDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/rooms")
@Transactional
public class RoomController {


    public RoomController() {

    }

}
