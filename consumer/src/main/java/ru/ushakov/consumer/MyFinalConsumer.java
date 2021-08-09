package ru.ushakov.consumer;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import ru.ushakov.consumer.dao.DogRepository;
import ru.ushakov.consumer.model.Dog;

@Slf4j
//@Service
@RequiredArgsConstructor
@ConfigurationProperties("kafka-demo2")
public class MyFinalConsumer {

    DogRepository dogRepository;
    KafkaTemplate<String, String> kafkaTemplate2;

    @Setter
    @NonFinal
    String responseTopicName = "db-response";

    @KafkaListener(
            id = "myId",
            topics = "${kafka-demo.topic-name:db-index}")
    void listen(String message) {
        log.info("message: {}", message);

        dogRepository
                .findById(Long.parseLong(message))
                .map(Dog::getName)
                .map(name -> new ProducerRecord<String, String>(responseTopicName, name))
                .ifPresent(producerRecord -> kafkaTemplate2.send(producerRecord)
                        .addCallback(
                                result -> log.info("SUCCESS!!! This is the result: {}", result),
                                ex -> log.error("ERROR Kafka error happened: {}", ex.toString())));
    }
}
