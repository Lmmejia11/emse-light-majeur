package fr.emse.majeureinfo.lightproject.dao;

import fr.emse.majeureinfo.lightproject.model.Light;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class LightDaoImpl implements LightDaoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Light> findOnLights() {
        String jpql = "select lt from Light lt where lt.status = :value";
        TypedQuery<Light> query = em.createQuery(jpql, Light.class);
        return query.setParameter("value", Light.Status.ON)
                .getResultList();
    }

    @Override
    public void switchLight(Long id) {
        String jpql = "select lt from Light lt where lt.id = :value";
        TypedQuery<Light> query = em.createQuery(jpql, Light.class);
        query.setParameter("value", id).getResultList();
        // TODO
    }
}
