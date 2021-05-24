package sd.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Subscriber {
    public static void main(String[] args) throws MqttException, InterruptedException {
        String clientId     = "myClient2";
        String topic        = "MiguelNdeCarvalho";                  // confirme que é o tópico certo
        String broker       = "tcp://localhost:1883";   // verifique se é o mesmo endereço
        MqttClient sampleClient = new MqttClient(broker, clientId, new MemoryPersistence());
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);


        sampleClient.setCallback(new MqttCallback() {
            public void connectionLost(Throwable cause) { //Called when the client lost the connection to the broker 
            }
            public void connectComplete(boolean reconnect, java.lang.String serverURI) {
            }
            public void messageArrived(String topic, MqttMessage message) throws Exception {   // método acionado ao receber uma msg
                System.out.println("messageArrived:");
                System.out.println(topic + ": " + new String(message.getPayload()) );
            }
            public void deliveryComplete(IMqttDeliveryToken token) {//Called when a outgoing publish is complete 
            }
        });
            
        System.out.println("Connecting to broker: "+broker);
        sampleClient.connect(connOpts);
        System.out.println("Connected");
        sampleClient.subscribe(topic);
        
        for (int i=1; i<30; i++) {
            System.out.println("\t ... "+i);
            Thread.sleep(1000);
        }

        sampleClient.disconnect();
        sampleClient.close();
    }
}
