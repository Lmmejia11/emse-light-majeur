package fr.emse.majeureinfo.lightproject.dao.springdao;

import fr.emse.majeureinfo.lightproject.model.Noise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoiseDao extends JpaRepository<Noise, Long> {
}
