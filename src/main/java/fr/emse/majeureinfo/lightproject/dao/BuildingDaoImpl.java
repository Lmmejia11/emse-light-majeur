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

public class BuildingDaoImpl {

    @PersistenceContext
    private EntityManager em;

    public List<Building> listBuildingsLightOn() {

        String jpql = "select bd from Building bd where lt.status = :value";
        TypedQuery<Building> query = em.createQuery(jpql, Building.class);
        return query.setParameter("value", Light.Status.ON)
                .getResultList();
    }

    public List<BuildingDetailDto> listBuildingsDetails() {

        String jpql = "select db.id as id, db.name as name, count(db.rooms) as n_rooms from Building bd group by db.id";
        TypedQuery<BuildingDetailDto> query = em.createQuery(jpql, BuildingDetailDto.class);
        return query.getResultList();
    }

    /*public List<BuildingDetailDto> listBuildingsDetails() {

        DetachedCriteria queryLightOn = DetachedCriteria.forClass(Building.class)
                .setProjection( Projections.rowCount() )
                .add( Restrictions.eq("rooms.light.status", Light.Status.ON) );
        DetachedCriteria queryNoiseOn = DetachedCriteria.forClass(Building.class)
                .setProjection( Projections.rowCount() )
                .add( Restrictions.eq("rooms.noise.status", Light.Status.ON) );

        TypedQuery<BuildingDetailDto> query = session.createCriteria(Building.class)
                .setProjection( Projections.projectionList()
                        .add( Projections.rowCount() )
                        .add(queryLightOn)
                        .add(queryNoiseOn)
                        .add( Projections.groupProperty("rooms.id") )
                )
                .setProjection( Projections.alias( Projections.groupProperty(""), "colr" ) )
        return query.setParameter("value", Light.Status.ON)
                .getResultList();
    }*/

}
