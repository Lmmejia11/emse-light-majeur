package fr.emse.majeureinfo.lightproject.dao;

import fr.emse.majeureinfo.lightproject.model.Light;

import java.util.List;

// List of all sql methods
public interface LightDaoCustom {

  List<Light> findOnLights();

}
