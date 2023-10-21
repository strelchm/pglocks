package ru.strelchm.pglocks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.strelchm.pglocks.entity.Counter;
import ru.strelchm.pglocks.entity.TestChild;
import ru.strelchm.pglocks.repo.CounterRepo;
import ru.strelchm.pglocks.repo.TestChildRepo;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.text.MessageFormat;

@Service
public class VersionService {
  private final CounterRepo repo;
  private final TestChildRepo testChildRepo;
  @Autowired
  private EntityManager entityManager;

  public VersionService(CounterRepo repo, TestChildRepo testChildRepo) {
    this.repo = repo;
    this.testChildRepo = testChildRepo;
  }

  @Transactional
  public Counter emCheck(Long id) {
    Counter counter = repo.findById(id).orElseThrow();
//    counter.setCounter(0L);
//    counter = repo.save(counter);
//    System.out.println(MessageFormat.format("{0} ||| {1}", counter.getCounter(), counter.getVersion()));
//
//    entityManager.lock(counter, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
//
//    counter.setCounter(1L);
//    counter = repo.save(counter);
//    System.out.println(MessageFormat.format("{0} ||| {1}", counter.getCounter(), counter.getVersion()));
//
//    counter.setCounter(2L);
//    counter = repo.save(counter);
//    System.out.println(MessageFormat.format("{0} ||| {1}", counter.getCounter(), counter.getVersion()));

    return counter;
  }

  @Transactional
  public Counter checkVersion() {
    Counter counter = new Counter();
    counter.setCounter(0L);
    counter = repo.save(counter);
    System.out.println(MessageFormat.format("{0} ||| {1}", counter.getCounter(), counter.getVersion()));

    entityManager.lock(counter, LockModeType.OPTIMISTIC_FORCE_INCREMENT);

    counter.setCounter(1L);
    counter = repo.save(counter);
    System.out.println(MessageFormat.format("{0} ||| {1}", counter.getCounter(), counter.getVersion()));

    counter.setCounter(2L);
    counter = repo.save(counter);
    System.out.println(MessageFormat.format("{0} ||| {1}", counter.getCounter(), counter.getVersion()));

    return counter;
  }

  @Transactional
  public Counter getAndChangeCollection(Long id) {
    Counter counter = repo.findById(id).orElseThrow();
    TestChild child = testChildRepo.findById(1L).orElseThrow();
    counter.getChildren().add(child);
    counter = repo.save(counter);
    System.out.println(counter.getVersion());
    return counter;
  }

  @Transactional
  public Counter changeInnerCollectionElement(Long id) {
    Counter counter = repo.findById(id).orElseThrow();
    TestChild child = testChildRepo.findById(1L).orElseThrow();
    child.setTestText("sdf" + Math.random());
    testChildRepo.save(child);
    return repo.save(counter);
  }
}
