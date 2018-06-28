package rabbitMq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

	private static final String QUEUE_NAME = "hello";
//	private static final Logger LOGGER = LoggerFactory.getLogger(Send.class);
	
	public static void main(String[] args) throws java.io.IOException, Exception {
		
		//create a connection to the server:
		ConnectionFactory factory = new ConnectionFactory();
		System.out.println("sender started");
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		//declare a queue for us to send to; then we can publish a message to the queue
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "Hello World!";
		System.out.println("message in byte"+message.getBytes());
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");
		
		channel.close();
		connection.close();

	}

}
