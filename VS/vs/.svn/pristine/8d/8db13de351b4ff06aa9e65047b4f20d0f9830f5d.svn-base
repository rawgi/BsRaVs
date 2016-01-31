package de.hsrm.cs.wwwvs.primzahlen;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class PrimzahlenPublisher{
	long primCount = 0;
	long curPrim = 0;
	long before;
	long after;
	boolean dividerFound;

	Session session;
	Topic topic;
	MessageProducer producer;
	
	public PrimzahlenPublisher() throws JMSException{
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);

		Connection connection = factory.createConnection();
		connection.start();
		
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		topic = session.createTopic("Primzahlen");
		producer = session.createProducer(topic);
//		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	}
	
	private void increasePrim(){
		before = System.currentTimeMillis();
		dividerFound = true;
		while(dividerFound){
			curPrim++;
			dividerFound = false;
			for(int i = 2; i<curPrim; i++){
				if(curPrim%i == 0){
					dividerFound = true; 
					break;
				}
			}
		}
		primCount++;
		after = System.currentTimeMillis();
	}
	
	public void send() throws JMSException{
		increasePrim();
		MapMessage message = session.createMapMessage();
		message.setJMSTimestamp(System.currentTimeMillis());
		message.setLongProperty("PrimzahlNr", primCount);
		message.setLongProperty("Primzahl", curPrim);
		message.setLongProperty("Rechenzeit", after-before);
		producer.send(message);
	}
	
	public static void main(String[] args) throws JMSException, InterruptedException {
		if(args.length < 1){
			System.out.println("bitte Zeit zum Warten mit übergeben");
			return;
		}
		PrimzahlenPublisher pp = new PrimzahlenPublisher();
		while(true){
			pp.send();
			Thread.sleep(Integer.parseInt(args[0]));
		}
	}

}
