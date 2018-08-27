package com.vq.jwt.masoud.api.activemq;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import com.vq.jwt.masoud.api.CustomConfigs;

@IntegrationComponentScan
@Configuration
/**
 * This class is responsible for configuring the Spring project to communicate with activeMq topics through MQTT protocol
 * and setting message handlers
 */
public class ActivemqMqttConfigs {

	private CustomConfigs customConfigs;
	@Autowired
	public void setCustomConfigs(CustomConfigs customConfigs) {
		this.customConfigs = customConfigs;
	}

	@Bean
	public MessageChannel mqttInputChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel mqttOutputChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageProducer mqttInbound() {
		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
				customConfigs.getActiveMqUrl(), customConfigs.getActiveMqInputClientId(),
				customConfigs.getActiveMqTopic());
		adapter.setQos(customConfigs.getActiveMqQos());
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setOutputChannel(mqttInputChannel());
		return adapter;
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttOutputChannel")
	public MessageHandler mqttOutbound() {
		MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(customConfigs.getActiveMqOutputClientId(),
				mqttClientFactory());
		messageHandler.setAsync(true);
		messageHandler.setDefaultTopic(customConfigs.getActiveMqTopic());
		return messageHandler;
	}

	@Bean
	public MqttPahoClientFactory mqttClientFactory() {
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		MqttConnectOptions options = new MqttConnectOptions();
		options.setServerURIs(new String[] { customConfigs.getActiveMqUrl() });
		options.setUserName(customConfigs.getActiveMqUser());
		options.setPassword(customConfigs.getActiveMqPass().toCharArray());
		factory.setConnectionOptions(options);
		return factory;
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttInputChannel")
	public MessageHandler handler() {
		return new ActiveMqAuthMasoudMsgHandler();
	}

	@MessagingGateway(defaultRequestChannel = "mqttOutputChannel")
	public interface AuthMasoudMsgGateway {
		void sendToMqtt(String data);
	}
}
