package ru.strelchm.pglocks.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import ru.strelchm.pglocks.entity.Counter;
import ru.strelchm.pglocks.entity.TestChild;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface TestChildRepo extends JpaRepository<TestChild, Long> {


  @Lock(LockModeType.OPTIMISTIC)
  Optional<TestChild> findById(Long id);
}
