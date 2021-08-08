package ru.vlapin.kafkademo2;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@SpringBootApplication
public class KafkaDemo2ProducerApplication {

  public static void main(String[] args) {
    SpringApplication.run(KafkaDemo2ProducerApplication.class, args);
  }

  @Bean
  NewTopic topic(@Value("${kafka-demo.topic-name:topic1}") String topicName) {
    return TopicBuilder
        .name(topicName)
        .partitions(10)
        .replicas(1).build();
  }

  @Bean
  ApplicationRunner runner(@Value("${kafka-demo.topic-name:topic1}") String topicName,
                           KafkaTemplate<String, String> template) {

    return __ -> template.send(topicName, "Producer starts!");
  }
}
