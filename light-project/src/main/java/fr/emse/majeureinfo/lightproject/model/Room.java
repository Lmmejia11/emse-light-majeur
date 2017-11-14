package fr.emse.majeureinfo.lightproject.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Light light;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Noise noise;

    public Room(){}

    public Room(Light light, Noise noise) {
        this.light = light;
        this.noise = noise;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLight(Light light) {
        this.light = light;
    }

    public void setNoise(Noise noise) {
        this.noise = noise;
    }

    public Long getId() {
        return id;
    }

    public Light getLight() {
        return light;
    }

    public Noise getNoise() {
        return noise;
    }

    public void switchLight(){
        Light.Status state = light.getStatus();
        switch (state){
            case ON:
                light.setStatus(Light.Status.OFF);
                break;
            case OFF:
                light.setStatus(Light.Status.ON);
                break;
        }
    }

    public void switchRinger(){
        Light.Status state = noise.getStatus();
        switch (state){
            case ON:
                noise.setStatus(Light.Status.OFF);
                break;
            case OFF:
                noise.setStatus(Light.Status.ON);
                break;
        }
    }
}
