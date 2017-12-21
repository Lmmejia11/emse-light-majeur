package fr.emse.majeureinfo.lightproject;

import fr.emse.majeureinfo.lightproject.mqtt_client.Subscriber;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URISyntaxException;

@SpringBootApplication
public class LightProjectApplication {


	public static void main(String[] args) throws MqttException, URISyntaxException {
		SpringApplication.run(LightProjectApplication.class, args);
	}

}
