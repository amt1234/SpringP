package com.bridgeit.fundoonote.userservice.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

@Component
public class RedisConfigration {
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfigration.class);
	private static final RedisCommands<String, String> sysCommand = redisUtility();

	private static RedisCommands<String, String> redisUtility() {
		// redis to store token
		LOGGER.info("Redis configration establishing..");
		RedisClient redisClient = RedisClient.create("redis://@localhost:6379/");
		StatefulRedisConnection<String, String> connection = redisClient.connect();
		RedisCommands<String, String> syncCommands = connection.sync();

		return syncCommands;
	}

	public static RedisCommands<String, String> redisUtil() {
		return sysCommand;
	}
}
