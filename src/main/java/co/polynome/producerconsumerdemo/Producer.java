package co.polynome.producerconsumerdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Produces work items by getting data from an API and enqueuing it to a thread pool of consumers.
 */
@Component
public class Producer {
  // How quickly to restart the producer if it dies
  public static final int PRODUCER_RESTART_DELAY = 1;

  private static final Logger logger = LoggerFactory.getLogger(Producer.class);

  private final BlockingDataSource bds;
  private final Consumer consumer;

  public Producer(final BlockingDataSource bds, final Consumer consumer) {
    this.bds = bds;
    this.consumer = consumer;
  }

  /**
   * Loops forever, gathering data from the BlockingDataSource and handing it to the Consumer for
   * processing. If this, it should be restarted after 1 second.
   */
  @Scheduled(fixedDelay = PRODUCER_RESTART_DELAY * 1000)
  public void getDataFromDataSource() {
    logger.info("Starting main loop");

    while (true) {
      logger.info("Getting data from external service");
      final int data = bds.getData();
      logger.info("Got " + data + " from service");

      Utils.failRandomly("Producer");

      logger.info("Passing " + data + " to consumer");
      consumer.process(data);
    }
  }
}
