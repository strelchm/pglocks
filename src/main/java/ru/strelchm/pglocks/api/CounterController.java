package ru.strelchm.pglocks.api;

import org.springframework.web.bind.annotation.*;
import ru.strelchm.pglocks.entity.Counter;
import ru.strelchm.pglocks.service.CounterService;

@RestController
@RequestMapping("/")
public class CounterController {

  private final CounterService counterService;

  public CounterController(CounterService counterService) {
    this.counterService = counterService;
  }

  @GetMapping("/counter/{id}")
  public Counter createCounter(@PathVariable Long id) throws InterruptedException {
    return counterService.getCounterWithTimeout(id);
  }

  @PostMapping("/counter")
  public Counter createCounter() {
    return counterService.createCounter();
  }

  @PostMapping("/counter/{id}/increment")
  public Counter incrementCounter(@PathVariable Long id) throws InterruptedException {
    return counterService.incrementCounter(id);
  }

  @PostMapping("/counter/{id}/decrement")
  public Counter decrementCounter(@PathVariable Long id) throws InterruptedException {
    return counterService.decrementCounter(id);
  }
}
