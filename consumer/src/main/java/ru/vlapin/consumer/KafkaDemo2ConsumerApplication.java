package ru.vlapin.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

@Slf4j
@SpringBootApplication
public class KafkaDemo2ConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(KafkaDemo2ConsumerApplication.class, args);
  }

  @Bean
  NewTopic topic(@Value("${kafka-demo.topic-name:topic1}") String topicName) {
    return TopicBuilder
        .name(topicName)
        .partitions(10)
        .replicas(1).build();
  }

  @KafkaListener(
      id = "myId",
      topics = "${kafka-demo.topic-name:topic1}")
  void listen(String message) {
    log.info("message: {}", message);
  }

}
