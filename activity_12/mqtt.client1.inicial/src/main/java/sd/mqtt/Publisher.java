package sd.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Publisher {
    public static void main(String[] args) throws MqttException {
        String clientId     = "JavaSample";            
        String broker       = "tcp://localhost:1883";  // URL de um Broker ativo
        String topic        = "MiguelNdeCarvalho";
        String content      = "Message from MqttPublishSample"; // mensagem a enviar
        int qos             = 2;
    
        MqttClient sampleClient = new MqttClient(broker, clientId, new MemoryPersistence());
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);


        System.out.println("Connecting to broker: "+broker);
        sampleClient.connect(connOpts);

        System.out.println("Connected");
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(qos);
        
        sampleClient.publish(topic, message); 
        System.out.println("Message published");
        
        sampleClient.disconnect();
        sampleClient.close();
    }
}
