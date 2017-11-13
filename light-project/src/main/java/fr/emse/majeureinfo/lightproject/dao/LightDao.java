package fr.emse.majeureinfo.lightproject.dao;

import fr.emse.majeureinfo.lightproject.model.Light;
import org.springframework.data.jpa.repository.JpaRepository;

// Must be done for Spring to work. Leave empty
// extends JpaRepository: list methods that Spring will use
// extends CostumDAO: tell Spring it can also use methods you implement, defined in CostumDAO
public interface LightDao extends JpaRepository<Light, Long> /* TODO , LightDaoCustom*/{
}
