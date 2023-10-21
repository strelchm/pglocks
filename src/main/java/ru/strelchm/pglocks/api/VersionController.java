package ru.strelchm.pglocks.api;

import org.springframework.web.bind.annotation.*;
import ru.strelchm.pglocks.entity.Counter;
import ru.strelchm.pglocks.service.CounterService;
import ru.strelchm.pglocks.service.VersionService;

@RestController
@RequestMapping("/")
public class VersionController {

  private final VersionService versionService;

  public VersionController(VersionService versionService) {
    this.versionService = versionService;
  }

  @GetMapping("/version")
  public Counter checkVersion() {
    Counter counter = versionService.checkVersion();
    System.out.println(counter.getVersion());
    return counter;
  }

  @GetMapping("/version/{id}")
  public Counter incrementCounter(@PathVariable Long id) throws InterruptedException {
    Counter counter = versionService.getAndChangeCollection(id);
    System.out.println(counter.getVersion());
    return counter;
  }

  @GetMapping("/version/{id}/em")
  public Counter emCheck(@PathVariable Long id) throws InterruptedException {
    Counter counter = versionService.emCheck(id);
    System.out.println(counter.getVersion());
    return counter;
  }

  @GetMapping("/version/{id}/inner")
  public Counter changeInnerCollectionElement(@PathVariable Long id) throws InterruptedException {
    return versionService.changeInnerCollectionElement(id);
  }
}
