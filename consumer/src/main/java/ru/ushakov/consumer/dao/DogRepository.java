package ru.ushakov.consumer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ushakov.consumer.model.Dog;

public interface DogRepository extends JpaRepository<Dog, Long> {
}
