package fr.emse.majeureinfo.lightproject.dao;

import fr.emse.majeureinfo.lightproject.dto.BuildingDetailDto;
import fr.emse.majeureinfo.lightproject.model.Building;
import fr.emse.majeureinfo.lightproject.model.Light;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class BuildingDaoImpl implements BuildingDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public List<Building> listBuildingsLightOn() {

        String jpql = "select bd "+
                "from Building as bd left join bd.rooms as rm "+
                "where rm.light.status = :value";
        TypedQuery<Building> query = em.createQuery(jpql, Building.class);
        return query.setParameter("value", Light.Status.ON)
                .getResultList();
    }

    public List<BuildingDetailDto> listBuildingsDetails() {

        String jpql = "select new fr.emse.majeureinfo.lightproject.dto.BuildingDetailDto(bd.id, bd.name , count(rm)) "+
                "from Building as bd left join bd.rooms as rm group by bd.id";

        TypedQuery<BuildingDetailDto> query = em.createQuery(jpql, BuildingDetailDto.class);
        return query.getResultList();
    }

}
