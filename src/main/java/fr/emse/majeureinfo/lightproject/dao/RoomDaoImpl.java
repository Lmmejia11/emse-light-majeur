package fr.emse.majeureinfo.lightproject.dao;

import fr.emse.majeureinfo.lightproject.model.Light;
import fr.emse.majeureinfo.lightproject.model.Room;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class RoomDaoImpl implements RoomDaoCostom {

    @PersistenceContext
    private EntityManager em;

    @PersistenceContext
    private EntityManager em1;

    @Override
    public List<Room> findWithOnLight() {
        //String jpql = "select rm from Room rm where rm.Light.status = :value";
        String jpql = "select rm from Room rm where rm.light.status = :value";
        TypedQuery<Room> query = em.createQuery(jpql, Room.class);
        return query.setParameter("value", Light.Status.ON)
                .getResultList();
    }

    @Override
    public List<Room> findWithOnRinger() {
        String jpql = "select rm from Room rm where rm.noise.status = :value";
        TypedQuery<Room> query = em.createQuery(jpql, Room.class);
        return query.setParameter("value", Light.Status.ON)
                .getResultList();
    }

    @Override
    public List<Room> findWithOffLight() {
        String jpql1 = "select rm from Room rm where rm.light.status = :value";
        TypedQuery<Room> query = em1.createQuery(jpql1, Room.class);
        return query.setParameter("value", Light.Status.OFF)
                .getResultList();
    }

    @Override
    public List<Room> findWithOffRinger() {
        String jpql1 = "select rm from Room rm where rm.noise.status = :value";
        TypedQuery<Room> query = em1.createQuery(jpql1, Room.class);
        return query.setParameter("value", Light.Status.OFF)
                .getResultList();
    }
}
