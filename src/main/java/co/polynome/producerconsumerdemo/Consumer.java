package co.polynome.producerconsumerdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Gets work items (integers) one at a time from the Producer and squares them, logging the output.
 *
 * This consumer does not return anything; if it did, we would need to return a Future<T> because
 * the process method is annotated as @Async
 */
@Component
public class Consumer {
  private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

  /**
   * Processes data received from the producer. Data items are internally queued and maximum
   * concurrency is enforced by Spring; see Application.ConsumerExecutor
   */
  @Async("ConsumerExecutor")
  public void process(int data) {
    logger.info("Received " + data + " from producer");

    // Simulate random delays and failures
    Utils.randomSleep();
    Utils.failRandomly("Consumer");

    final StringBuilder sb = new StringBuilder();
    sb.append("The square of ");
    sb.append(data);
    sb.append(" is ");
    sb.append(data * data);

    logger.info(sb.toString());
  }
}
