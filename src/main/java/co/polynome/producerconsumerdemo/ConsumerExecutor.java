package co.polynome.producerconsumerdemo;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * A very simple executor subclass that only allows one consumer thread to run at a time. It might
 * be possible to implement this with configuration alone, need to dig in to the Spring docs.
 */
@Component("ConsumerExecutor")
public class ConsumerExecutor extends SimpleAsyncTaskExecutor {
  // Change this number to determine how many consumers are allowed to simultaneously run
  private static final int CONSUMER_CONCURRENCY = 1;

  public ConsumerExecutor() {
    super();
    this.setConcurrencyLimit(CONSUMER_CONCURRENCY);
  }
}
