package co.polynome.producerconsumerdemo;

import org.springframework.stereotype.Component;

/**
 * Simulates an API that returns successive integers, but can block
 */
@Component
public class BlockingDataSource {
  private int nextValue = 0;

  public int getData() {
    nextValue++;

    // Simulate a random API delay
    Utils.randomSleep();

    return nextValue;
  }
}
