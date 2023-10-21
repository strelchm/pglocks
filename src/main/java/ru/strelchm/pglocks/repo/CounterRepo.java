package ru.strelchm.pglocks.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import ru.strelchm.pglocks.entity.Counter;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface CounterRepo extends JpaRepository<Counter, Long> {


  @Lock(LockModeType.OPTIMISTIC)
  Optional<Counter> findById(Long id);
}
