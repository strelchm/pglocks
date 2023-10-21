package ru.strelchm.pglocks.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Counter {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long counter;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "counter")
  private List<TestChild> children = new ArrayList<>();

  @Version
  private int version;
}
