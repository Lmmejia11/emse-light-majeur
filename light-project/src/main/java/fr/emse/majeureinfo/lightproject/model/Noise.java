package fr.emse.majeureinfo.lightproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Noise {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer level;

    @Enumerated(EnumType.STRING)
    private Light.Status status;

    @SuppressWarnings("unused")
    private Noise() {
    }

    public Noise(Integer level, Light.Status status) {
        this.level = level;
        this.status = status;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Light.Status getStatus() {
        return status;
    }

    public void setStatus(Light.Status status) {
        this.status = status;
    }


}
