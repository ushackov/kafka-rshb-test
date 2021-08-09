package ru.ushakov.consumer.model;

import lombok.*;
import lombok.EqualsAndHashCode.Include;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@ToString
@RequiredArgsConstructor
@Entity
//@Builder
@Setter(PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class Dog {

  //region id
  @Id
  @Include
  @GeneratedValue
  @Column(updatable = false, nullable = false)
  Long id;


  //endregion
  @NonNull
  @Column(nullable = false)
  String name;

  //region equals and hashCode
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Dog dog = (Dog) o;

    return Objects.equals(id, dog.id);
  }

  @Override
  public int hashCode() {
    return 1216366848;
  }


  //endregion
}
