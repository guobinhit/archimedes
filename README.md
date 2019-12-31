# archimedes

![author](https://img.shields.io/badge/author-chariesgavin-blueviolet.svg)![last commit](https://img.shields.io/github/last-commit/guobinhit/archimedes.svg)![issues](https://img.shields.io/github/issues/guobinhit/archimedes.svg)![stars](https://img.shields.io/github/stars/guobinhit/archimedes.svg)![forks](	https://img.shields.io/github/forks/guobinhit/archimedes.svg)![license](https://img.shields.io/github/license/guobinhit/archimedes.svg)

> **语言**：中文 | [英文](https://github.com/guobinhit/archimedes/blob/master/README_ENGLISH.md)

## 索引

- [算法概述](#算法概述)
  - [DBSCAN](#DBSCAN)
  - [LOF](#LOF)
  - [STCR](#STCR)
  - [3-SIGMA](#3-SIGMA)
  - [Z-SCORE](#Z-SCORE)
  - [QUARTILE](#QUARTILE)
- [使用示例](#使用示例)

### 算法概述

#### DBSCAN

DBSCAN 是 Density-Based Spatial Clustering of Applications with Noise 的简写，是一个比较有代表性的基于密度的聚类算法。与划分和层次聚类方法不同，它将簇定义为密度相连的点的最大集合，能够把具有足够高密度的区域划分为簇，并可在噪声的空间数据库中发现任意形状的聚类。如果某个点不属于任意簇，则被视为噪声点，也就是异常点。

#### LOF

LOF 是 Local Outlier Factor 的简写，也称为局部异常因子算法，LOF 通过计算一个数值分来反映一个样本的异常程度。这个数值的大致意思是，一个样本点周围的样本点所处位置的平均密度比上该样本点所在位置的密度，比值越大于 1，则该点所在位置的密度越小于其周围样本所在位置的密度，这个点就越有可能是异常点。

#### STCR

STCR 是 Short Term Chain Ratio 的缩写，也称为短期环比法，对于时间序列（是指将同一统计指标的数值按其发生的时间先后顺序排列而成的数列）来说，T 时刻的数值对于 T−1 时刻有很强的依赖性，比如流量在 8:00 很多，在 8:01 时刻的概率是很大的，但是如果 07:01 时刻对于 8:01 时刻影响不是很大。

#### 3-SIGMA

3-SIGMA法，也称为拉依达准则，它是先假设一组检测数据只含有随机误差，对其进行计算处理得到标准偏差，按一定概率确定一个区间，认为凡超过这个区间的误差，就不属于随机误差而是粗大误差。一般认为，数值分布几乎全部集中在 (μ-3σ,μ+3σ) 区间内，超出这个范围的可能性仅占不到 0.3%，其中 μ 是均值，σ 是标准差。进而认为，当数据分布区间超过这个区间时，即为异常数据，也就是异常点。

#### Z-SCORE

Z-SCORE 法，即 Z 分数法，也称为标准分数法，是指一个数与平均数的差再除以标准差的过程。在统计学中，标准分数是一个观测或数据点的值高于被观测值或测量值的平均值的标准偏差的符号数。该方法假定数据是高斯分布的，异常点是分布尾部的数据点，因此远离数据的平均值。距离的远近取决于使用公式计算的归一化数据点设定的阈值，根据经验，阈值一般设置为 2.5、3.0 和 3.5，本项目使用的 Z 分数法设置 3.5 为阈值。

#### QUARTILE

QUARTILE 法，即四分位法，也称为箱型图，是指在统计学中把所有数值由小到大排列并分成四等份，其中每部分包含 25% 的数据，处在 25% 位置上的数值又称为上四分位数，处在 75% 位置上的数值又称为下四分位数，凡处于上下四分数之外的点，即视为异常点。

### 使用示例

首先，引入本项目 jar 包，创建`OutlierDetectionService`实例：

```java
OutlierDetectionService odService = new OutlierDetectionService();
```

然后，调用`OutlierDetectionService`的`outlierDetect()`方法。当然，在调用`outlierDetect()`方法之前，我们需要先创建该方法的参数对象`OutlierDetectionParam`，该对象含有三个参数，分别为：

- `algorithmType`，表示算法类型，为`AlgorithmTypeEnum`枚举类型，必填项；
- `orderType`，表示排序类型，为`OrderTypeEnum`枚举类型，用于对原始数据按时间排序，选填项；
- `dataMapList`，表示数据集，即待处理的数据列表，非空且数量必须大于0，必填项。

```java
// 格式化数据集
List<Map<String, Object>> dataMapList = new ArrayList<>();

// 组装参数
OutlierDetectionParam odParam = new OutlierDetectionParam();
// 使用四分位法
odParam.setAlgorithmType(AlgorithmTypeEnum.QUARTILE);
// 将数据集按时间升序排序
odParam.setOrderType(OrderTypeEnum.ASC);
// 填充数据集
odParam.setDataMapList(dataMapList);

// 实例化异常点探测服务类，调用探测方法
OutlierDetectionService odService = new OutlierDetectionService();
OutlierDetectionResult odResult = odService.outlierDetect(odParam);
```

其中，我们需要特别注意`dataMapList`参数，其数据格式为`List<Map<String, Object>>`，且`Map<String, Object>`的键只能为下面两个字符串：

- `value`，表示数值，必填项；
- `date`，表示数值对应的时间，当`orderType`参数为空时，此项参数可不填，即表示不按时间排序。

探测的结果对象为`OutlierDetectionResult`，其包含两个字段，分别为：

- `dataMapList`，表示数据集，如果没有传`orderType`参数，则为原数据集，否则为排序后的数据集；
- `outlierIndexList`，表示异常点的索引列表，即异常点的位置。