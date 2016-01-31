package de.hsrm.cs.wwwvs.primzahlen;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class PrimzahlzwillingPublisher {

	Session session;
	Topic topicCon;
	Topic topicProd;
	MessageConsumer consumer;
	MessageProducer producer;
	
	int twinCount = 0;
	
	Prim lastPrim;
	
	
	public PrimzahlzwillingPublisher(String publishIP) throws JMSException{
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://"+publishIP+":61616");
		
		Connection connection = factory.createConnection();
		connection.start();
		
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		topicCon = session.createTopic("Primzahlen");
		topicProd = session.createTopic("Primzahlzwillinge");
		consumer = session.createConsumer(topicCon);
		producer = session.createProducer(topicProd);
	}
	
	private class Prim{
		Long number;
		Long calcTime;
		
		Prim(Long number, Long calcTime){
			this.number = number;
			this.calcTime = calcTime;
		}
	}
	
	
	public static void main(String[] args) throws JMSException {
		if(args.length < 1){
			System.out.println("bitte Ziel-IP mit übergeben");
		}
		
		PrimzahlzwillingPublisher ps = new PrimzahlzwillingPublisher(args[0]);
		ps.listen();
	}

	public void listen() throws JMSException{
		while(true){
			Message message = consumer.receive(1000);
			if(message != null){
				Prim curPrim = new Prim(message.getLongProperty("Primzahl"), message.getLongProperty("Rechenzeit"));
				if(lastPrim != null && curPrim.number-lastPrim.number == 2){
					twinCount++;
					MapMessage out = session.createMapMessage();
					out.setJMSTimestamp(System.currentTimeMillis());
					out.setLongProperty("PrimzahlzwillingNr", twinCount);
					out.setLongProperty("Primzahl1", lastPrim.number);
					out.setLongProperty("Primzahl2", curPrim.number);
					out.setLongProperty("Rechenzeit", curPrim.calcTime+lastPrim.calcTime);
					producer.send(out);
				}
				lastPrim = curPrim;
			}
		}
	}
}

