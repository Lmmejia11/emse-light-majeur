package fr.emse.majeureinfo.lightproject.dao;

import fr.emse.majeureinfo.lightproject.model.Light;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class BuildingDaoImpl {

    @PersistenceContext
    private EntityManager em;

    public List<Light> listBuildings() {

        String jpql = "select bd from Building bd where lt.status = :value";
        TypedQuery<Light> query = em.createQuery(jpql, Light.class);
        return query.setParameter("value", Light.Status.ON)
                .getResultList();
    }

}
