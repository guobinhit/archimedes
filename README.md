# archimedes

![author](https://img.shields.io/badge/author-chariesgavin-blueviolet.svg)![last commit](https://img.shields.io/github/last-commit/guobinhit/archimedes.svg)![issues](https://img.shields.io/github/issues/guobinhit/archimedes.svg)![stars](https://img.shields.io/github/stars/guobinhit/archimedes.svg)![forks](	https://img.shields.io/github/forks/guobinhit/archimedes.svg)![license](https://img.shields.io/github/license/guobinhit/archimedes.svg)

> 语言：中文 | [英文](https://github.com/guobinhit/archimedes/blob/master/README_ENGLISH.md)

## 索引

- [算法介绍](#算法介绍)
- [使用方法](#使用方法)

### 算法介绍

#### 四分位法
#### 3-SIGMA 法
#### Z-SCORE
#### DBSCAN
#### LOF
#### 短期环比

### 使用方法

首先，引入本项目 jar 包，创建`OutlierDetectionService`示例：

```java
OutlierDetectionService odService = new OutlierDetectionService();
```

然后，调用`OutlierDetectionService`的`outlierDetect()`方法。当然，在调用`outlierDetect()`方法之前，我们需要先创建该方法的参数对象`OutlierDetectionParam`，其含有三个参数，分别为：

- `algorithmType`，表示算法类型，为`AlgorithmTypeEnum`枚举类型，必填项；
- `orderType`，表示排序方法，为`OrderTypeEnum`枚举类型，选填项；
- `dataMapList`，表示数据集，非空且容量必须大于0，必填项。

```java
// 格式化数据集
List<Map<String, Object>> dataMapList = new ArrayList<>();

// 组装参数
OutlierDetectionParam odParam = new OutlierDetectionParam();
odParam.setAlgorithmType(AlgorithmTypeEnum.QUARTILE);
odParam.setOrderType(OrderTypeEnum.DESC);
odParam.setDataMapList(dataMapList);

// 实例化异常点探测服务类，调用探测方法
OutlierDetectionService odService = new OutlierDetectionService();
OutlierDetectionResult odResult = odService.outlierDetect(odParam);
```

其中，我们需要特别注意`dataMapList`参数，其格式为`List<Map<String, Object>>`，且`Map<String, Object>`的键只能为：

- `value`，表示数值，必填项；
- `date`，表示数值对应的时间，当`orderType`参数为空时，此项参数也可以不填。

探测的结果为`OutlierDetectionResult`，其包含两个字段，分别为：

- `dataMapList`，表示数据集，如果没有传`orderType`参数，则为原数据集，否则为排序后的数据集；
- `outlierIndexList`，表示异常点的索引列表，即异常点的位置。