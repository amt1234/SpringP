package workQueue;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Worker {
	private final static String QUEUE_NAME = "hello";
	
	public static void main(String[] args) throws Exception {
		
		
		ConnectionFactory factory = new ConnectionFactory();
		System.out.println("reciver started");
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		
		final Consumer consumer = new DefaultConsumer(channel) {
			  @Override
			  public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
			    String message = new String(body, "UTF-8");

			    System.out.println(" [x] Received '" + message + "'");
			    try {
			      doWork(message);
			    } catch (InterruptedException e) {
				
					System.out.println(e);
				} finally {
			      System.out.println(" [x] Done");
			    }
			  }

			  private void doWork(String task) throws InterruptedException {
				    for (char ch: task.toCharArray()) {
				        if (ch == '.') Thread.sleep(5000);
				    }
				}
			};
			boolean autoAck = true; // acknowledgment is covered below
			channel.basicConsume(QUEUE_NAME, autoAck, consumer);
	}

}
