package de.hsrm.cs.wwwvs.primzahlen;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class PrimzahlzwillingSubscriber {

	Session session;
	Topic topic;
	MessageConsumer consumer;
	
	public PrimzahlzwillingSubscriber(String publishIP) throws JMSException{
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://"+publishIP+":61616");
		
		Connection connection = factory.createConnection();
		connection.start();
		
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		topic = session.createTopic("Primzahlzwillinge");
		consumer = session.createConsumer(topic);
	}
	
	public static void main(String[] args) throws JMSException {
		if(args.length < 1){
			System.out.println("bitte Ziel-IP mit übergeben");
		}
		
		PrimzahlzwillingSubscriber ps = new PrimzahlzwillingSubscriber(args[0]);
		ps.listen();
	}

	public void listen() throws JMSException{
		while(true){
			Message message = consumer.receive(1000);
			if(message != null){
				System.out.println("Timestamp: "+message.getJMSTimestamp());
				System.out.println("Primzahlzwilling-Nummer: "+message.getLongProperty("PrimzahlzwillingNr"));
				System.out.println("Primzahl 1: "+message.getLongProperty("Primzahl1"));
				System.out.println("Primzahl 2: "+message.getLongProperty("Primzahl2"));
				System.out.println("Rechenzeit: "+message.getLongProperty("Rechenzeit"));
			}
		}
	}
}
