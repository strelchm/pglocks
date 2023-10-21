package ru.strelchm.pglocks.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.strelchm.pglocks.entity.Counter;
import ru.strelchm.pglocks.repo.CounterRepo;

@Service
public class CounterService {
  private final CounterRepo repo;

  public CounterService(CounterRepo repo) {
    this.repo = repo;
  }

  @Transactional
  public Counter createCounter() {
    Counter counter = new Counter();
    counter.setCounter(0L);
    return repo.save(counter);
  }

  @Transactional
  public Counter incrementCounter(Long id) throws InterruptedException {
    Counter counter = getCounter(id);
    Thread.sleep(5000L);
    counter.setCounter(counter.getCounter() + 1);
    return repo.save(counter);
  }

  @Transactional(readOnly = true)
  public Counter getCounter(Long id) {
    return repo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
  }


  @Transactional
  public Counter getCounterWithTimeout(Long id) throws InterruptedException {
    Counter counter = getCounter(id);
    Thread.sleep(10000L);
    return counter;
  }

  @Transactional
  public Counter decrementCounter(Long id) throws InterruptedException {
    Counter counter = getCounter(id);
    Thread.sleep(5000L);
    counter.setCounter(counter.getCounter() - 1);
    return repo.save(counter);
  }
}
