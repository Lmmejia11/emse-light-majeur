package fr.emse.majeureinfo.lightproject.dao;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.DeleteAll;
import com.ninja_squad.dbsetup.operation.Insert;
import com.ninja_squad.dbsetup.operation.Operation;
import fr.emse.majeureinfo.lightproject.dao.springdao.LightDao;
import fr.emse.majeureinfo.lightproject.dao.springdao.RoomDao;
import fr.emse.majeureinfo.lightproject.model.Light;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("/test.properties")
public class RoomDaoTest {

    @Autowired
    private RoomDao roomDao;

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    protected static final DbSetupTracker TRACKER = new DbSetupTracker();

    private static final Operation DELETE_ALL = DeleteAll.from("ROOM","LIGHT","NOISE");

    protected void dbSetup(Operation light, Operation noise, Operation room) {
        DbSetup setup = new DbSetup(new DataSourceDestination(dataSource),
                Operations.sequenceOf(DELETE_ALL, light, noise, room));
        TRACKER.launchIfNecessary(setup);
    }

    @Before
    public void prepare() {
        Operation light =
                Insert.into("LIGHT")
                        .withDefaultValue("status", Light.Status.ON)
                        .columns("id", "level")
                        .values(1L, 22)
                        .build();
        Operation noise =
                Insert.into("NOISE")
                        .withDefaultValue("status", Light.Status.ON)
                        .columns("id", "level")
                        .values(1L, 22)
                        .build();
        Operation room =
                Insert.into("ROOM")
                        .columns("id", "light_id", "noise_id")
                        .values(1L, 1L, 1L)
                        .build();
        dbSetup(light, noise, room);
    }

    @Test
    public void shouldFindOnLights() {
        TRACKER.skipNextLaunch();
        assertEquals(1,roomDao.findWithOnLight().size());
    }


}
