package com.aldisued.iot.monitoring.service;


import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

@Service
public class MeasurementCalculatorService {

  public List<Double> filterByAverageDeviation(List<Double> values, Double deviation) {
    checkInputParametersForAverageDeviation(values, deviation);
    var nonNullValues = values.stream().filter(Objects::nonNull).toList();
    if (nonNullValues.isEmpty()) {
      // skip unnecessary calculation
      return List.of();
    }
    var average = nonNullValues.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    var margin = average * deviation;
    var lowerBound = average - margin;
    var upperBound = average + margin;
    return nonNullValues.stream().filter(v -> v > lowerBound && v < upperBound).toList();
  }

  public List<Double> getMovingAverage(List<Double> data, int windowSize) {
    // TODO: Task 10
    return List.of();
  }

  private void checkInputParametersForAverageDeviation(List<Double> values, Double deviation) {
    checkDeviationInput(deviation);
    checkListInput(values);
  }

  private void checkDeviationInput(Double deviation) {
    if (deviation == null || deviation < 0.0 || deviation > 1.0) {
      throw new IllegalArgumentException("Deviation must be between 0.0 and 1.0");
    }
  }

  private void checkListInput(List<Double> values) {
    if (values == null) {
      throw new IllegalArgumentException("List of values must not be null!");
    }
  }

}
