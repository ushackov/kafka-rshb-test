package ru.ushakov.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import ru.ushakov.consumer.dao.DogRepository;
import ru.ushakov.consumer.model.Dog;

import java.util.Arrays;


@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@ConfigurationPropertiesScan
public class KafkaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApplication.class, args);
    }

    @Bean
    NewTopic topic(@Value("${kafka-demo.topic-name:db-response}") String topicName) {
        return TopicBuilder
                .name(topicName)
                .partitions(10)
                .replicas(1).build();
    }

    @Bean
    CommandLineRunner runner(DogRepository repository) {
        return __ -> Arrays.stream("Adolf, Bublik, Garik, Dido, Tyler, Funtik, Graf, Ares, Archi, Juchka"
                        .split(", "))
                .map(Dog::new)
                .forEach(repository::save);
    }
}
