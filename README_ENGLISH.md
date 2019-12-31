# archimedes

![author](https://img.shields.io/badge/author-chariesgavin-blueviolet.svg)![last commit](https://img.shields.io/github/last-commit/guobinhit/archimedes.svg)![issues](https://img.shields.io/github/issues/guobinhit/archimedes.svg)![stars](https://img.shields.io/github/stars/guobinhit/archimedes.svg)![forks](	https://img.shields.io/github/forks/guobinhit/archimedes.svg)![license](https://img.shields.io/github/license/guobinhit/archimedes.svg)

> **Language**: [Chinese](https://github.com/guobinhit/archimedes/blob/master/README.md) | English

## INDEX

- [Algorithm Overview](#algorithm-overview)
  - [DBSCAN](#DBSCAN)
  - [LOF](#LOF)
  - [STCR](#STCR)
  - [3-SIGMA](#3-SIGMA)
  - [Z-SCORE](#Z-SCORE)
  - [QUARTILE](#QUARTILE)
- [Use Example](#use-example)

### Algorithm Overview

#### DBSCAN

DBSCAN is the abbreviation of Density-Based Spatial Clustering of Applications with Noise. It is a representative density based clustering algorithm. Different from the methods of partition and hierarchical clustering, it defines clusters as the largest set of points connected by density, which can divide regions with enough high density into clusters, and can find clusters of any shape in noisy spatial database. If a point does not belong to any cluster, it is regarded as a noise point, that is, an abnormal point.

#### LOF

LOF is the abbreviation of Local Outlier Factor, also known as local outlier factor algorithm. Lof reflects the abnormal degree of a sample by calculating a numerical score. The general meaning of this value is that the average density of the sample point around a sample point is higher than the density of the sample point. The greater the ratio is, the less the density of the point is, the more abnormal the point can be.

#### STCR

STCR is the abbreviation of Short Term Chain Ratio, also known as the short-term round comparison method. For the time series (which refers to the sequence of the values of the same statistical index according to the time sequence of occurrence), the value of time t has a strong dependence on time t − 1, for example, the probability of traffic at 8:00 is very high, but if at 07:01 It doesn't have a big impact at 8:01.

#### 3-SIGMA

The 3-sigma method, also known as the Pauta Criterion, first assumes that a set of test data only contains random errors, calculates and processes them to get the standard deviation, determines an interval according to a certain probability, and considers that any error beyond this interval is not a random error but a gross error. It is generally believed that the numerical distribution is almost all concentrated in the (μ - 3 σ, μ + 3 σ) range, and the probability of exceeding this range is less than 0.3%, where μ is the mean value and σ is the standard deviation. Furthermore, when the data distribution interval exceeds this interval, it is considered as abnormal data, that is, abnormal points.

#### Z-SCORE

The z-score method, also known as the standard score method, refers to the process of dividing the difference between a number and an average by the standard deviation. In statistics, the standard score is the sign number of the standard deviation of the value of an observation or data point higher than the average of the observed value or measured value. This method assumes that the data is Gaussian distribution and the outliers are the data points at the tail of the distribution, so it is far away from the average value of the data. The distance depends on the threshold value set by the normalized data point calculated by the formula. According to experience, the threshold value is generally set to 2.5, 3.0 and 3.5. The Z-score method used in this project is set to 3.5 as the threshold value.

#### QUARTILE

The quartile method, also known as box chart, is to arrange all values in statistics from small to large and divide them into four equal parts, each part of which contains 25% of the data, the value at 25% position is also known as the upper quartile, and the value at 75% position is also known as the lower quartile. Any point beyond the upper and lower quartile is regarded as an abnormal point.

### Use Example

First, import the jar package of this project，create`OutlierDetectionService`instance：

```java
OutlierDetectionService odService = new OutlierDetectionService();
```

Then，call the method `outlierDetect()` of `OutlierDetectionService`. Of course, before calling the `outlierDetect()` method, we need to create the parameter object `OutlierDetectionParam` of the method, which contains three parameters, namely:

- `algorithmType`, represent algorithm type，it's `AlgorithmTypeEnum` enumeration type，`required`.
- `orderType`, represent order type，it's `OrderTypeEnum` enumeration type，used to sort raw data by time, `optional`.
- `dataMapList`, represent data set, that is, the list of data to be processed, it's not empty and the quantity must be greater than 0, `required`.

```java
// Format data set
List<Map<String, Object>> dataMapList = new ArrayList<>();

// Assembly parameters
OutlierDetectionParam odParam = new OutlierDetectionParam();
// Using the quartile method
odParam.setAlgorithmType(AlgorithmTypeEnum.QUARTILE);
// Sort datasets in ascending time order
odParam.setOrderType(OrderTypeEnum.ASC);
// Fill data set
odParam.setDataMapList(dataMapList);

// Instantiate the outlier detection service class and call the detection method
OutlierDetectionService odService = new OutlierDetectionService();
OutlierDetectionResult odResult = odService.outlierDetect(odParam);
```

Among them, we need to pay special attention to the `dataMapList` parameter, whose data format is `List<Map<String, Object>>`, and the key of `Map<String, Object>` can only be the following two strings:

- `value`, represent value key, `required`.
- `date`, Indicates the time corresponding to the value. When the `OrderType` parameter is empty, this parameter can be left blank, that is to say, it is not sorted by time.

The result object of the probe is `OutlierDetectionResult`, which contains two fields, namely:

- `dataMapList`, represent a dataset, if the `OrderType` parameter is not passed, it is the original dataset. Otherwise, it is the sorted dataset.
- `outlierIndexList`, represents the index list of outliers, that is, the location of outliers.

