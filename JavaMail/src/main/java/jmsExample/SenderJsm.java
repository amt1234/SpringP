package jmsExample;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SenderJsm {
	private static final String QUEUE_NAME = "poonamgadugale2017@gmail.com";
	// private static final Logger LOGGER = LoggerFactory.getLogger(Send.class);

	public static void main(String[] argv) throws java.io.IOException, Exception {

		// create a connection to the server:
		ConnectionFactory factory = new ConnectionFactory();
		System.out.println("sender started");
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		// declare a queue for us to send to; then we can publish a message to the queue
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		String message = getMessage(argv);
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");

		channel.close();
		connection.close();

	}

	private static String getMessage(String[] strings) {
		if (strings.length < 1)
			return "Hello World";
		return joinString(strings," ");

	}

	private static String joinString(String[] strings,String delimiter) {
	
		int strlenght=strings.length;
		if(strlenght==0)
			return "";
		StringBuilder stringBuilder=new StringBuilder(strings[0]);
		for(int i=1;i<strlenght;i++)
		{
			stringBuilder.append(delimiter).append(strings[i]);
		}		
		return stringBuilder.toString();
	}
}
