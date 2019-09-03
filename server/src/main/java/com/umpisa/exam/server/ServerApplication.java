package com.umpisa.exam.server;

import com.umpisa.exam.server.jpa.JPAUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.*;
import java.io.*;
import java.util.*;

@SpringBootApplication
public class ServerApplication {
	private static final Logger log = LoggerFactory.getLogger(ServerApplication.class);

	public static void main(String[] args) {
		try {
			Properties props = new Properties();
			props.load(new FileReader("conf/config.properties"));

			Config config = new Config(props);

			JPAUtil.init(config);
			SpringApplication.run(ServerApplication.class, args);
		}
		catch(IOException ex) {
			log.error("Error on AdminServer", ex);
		}
	}

}
