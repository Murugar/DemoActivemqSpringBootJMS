package com.iqmsoft.boot;

import com.iqmsoft.consumer.ConsumerApp;
import com.iqmsoft.producer.ProducerApp;

public class AppStarter {

	public static void main(String args[]) throws Exception {
		if (args.length == 0) {
			System.out.println("Usage : ");
			System.out.println("java -jar target/DemoActivemqSpringBootJMS-1.0.jar [consumer|producer]");
			return;
		}

		if (args[0].equalsIgnoreCase("consumer")) {
			ConsumerApp.main(args);
			return;
		}

		if (args[0].equalsIgnoreCase("producer")) {
			ProducerApp.main(args);
			return;
		}
	}
}
