package com.hit.cggb.archimedes.outlier.impl;

import com.hit.cggb.archimedes.entity.OutlierDetectionResult;
import com.hit.cggb.archimedes.outlier.OutlierDetection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Charies Gavin
 * @github https:github.com/guobinhit
 * @date 2019/12/26,上午11:20
 * @description DBSCAN 异常点探测算法
 */
public class DbscanDetection implements OutlierDetection {
    @Override
    public OutlierDetectionResult detect(List<Map<String, Object>> dateMapList) {
        // 初始化返回参数
        OutlierDetectionResult detectionResult = new OutlierDetectionResult();
        List<Integer> outlierIndexList = new ArrayList<>();

        // 将数据存入数组以便计算
        double[] dataArray = new double[dateMapList.size()];
        for (int i = 0; i < dateMapList.size(); i++) {
            dataArray[i] = (Double) dateMapList.get(i).get("value");
        }

        // 将一维数据点转换为二位数据点，默认 y 维值均为 0
        List<Point> pointList = new ArrayList<>();
        for (int i = 0; i < dataArray.length; i++) {
            pointList.add(new Point(dataArray[i], 0, i));
        }

        Dbscan dbscan = new Dbscan(5, 5);
        dbscan.process(pointList);

        for (Point point : pointList) {
            if (point.isNoised()) {
                outlierIndexList.add(point.getIndex());
            }
        }

        detectionResult.setDataMapList(dateMapList);
        detectionResult.setOutlierIndexList(outlierIndexList);
        return detectionResult;
    }


    /**
     * DBSCAN 类
     */
    private static class Dbscan {
        // 检测半径
        private double radius;
        // 最小点数
        private int minPts;

        public Dbscan(double radius, int minPts) {
            this.radius = radius;
            this.minPts = minPts;
        }

        /**
         * 核心算法
         *
         * @param points 数据点列表
         */
        public void process(List<Point> points) {
            int size = points.size();
            int idx = 0;
            int cluster = 1;
            while (idx < size) {
                Point point = points.get(idx++);
                // 处理未被访问过的点
                if (!point.isVisit()) {
                    // 设置访问标志
                    point.setVisit(true);
                    // 获取临近点列表
                    List<Point> adjacentPoints = getAdjacentPoints(point, points);
                    // 如果临近点列表的数量小于 minPts，则将其设置为噪音点
                    if (adjacentPoints.size() < minPts) {
                        point.setNoised(true);
                    } else {
                        point.setCluster(cluster);
                        for (int i = 0; i < adjacentPoints.size(); i++) {
                            Point adjacentPoint = adjacentPoints.get(i);
                            // only check unvisited point, cause only unvisited have the chance to add new adjacent points
                            if (!adjacentPoint.isVisit()) {
                                adjacentPoint.setVisit(true);
                                List<Point> adjacentAdjacentPoints = getAdjacentPoints(adjacentPoint, points);
                                // add point which adjacent points not less than minPts noised
                                if (adjacentAdjacentPoints.size() >= minPts) {
                                    // adjacentPoints.addAll(adjacentAdjacentPoints);
                                    for (Point pp : adjacentAdjacentPoints) {
                                        if (!adjacentPoints.contains(pp)) {
                                            adjacentPoints.add(pp);
                                        }
                                    }
                                }
                            }
                            // add point which doest not belong to any cluster
                            if (adjacentPoint.getCluster() == 0) {
                                adjacentPoint.setCluster(cluster);
                                // set point which marked noised before non-noised
                                if (adjacentPoint.isNoised()) {
                                    adjacentPoint.setNoised(false);
                                }
                            }
                        }
                        cluster++;
                    }
                }
            }
        }

        /**
         * 获取临近点列表
         *
         * @param centerPoint 中心点
         * @param points      全部点列表
         * @return 临近点列表
         */
        private List<Point> getAdjacentPoints(Point centerPoint, List<Point> points) {
            List<Point> adjacentPoints = new ArrayList<>();
            for (Point point : points) {
                // 包括中心点自身
                double distance = centerPoint.getDistance(point);
                if (distance <= radius) {
                    adjacentPoints.add(point);
                }
            }
            return adjacentPoints;
        }
    }

    /**
     * 二维点类
     */
    private static class Point {
        private double x;
        private double y;
        private boolean isVisit;
        private int cluster;
        // 噪音点标志
        private boolean isNoised;
        // 位置索引
        private int index;

        public Point(double x, double y, int index) {
            this.x = x;
            this.y = y;
            this.isVisit = false;
            this.cluster = 0;
            this.isNoised = false;
            this.index = index;
        }

        public double getDistance(Point point) {
            return Math.sqrt((x - point.x) * (x - point.x) + (y - point.y) * (y - point.y));
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getX() {
            return x;
        }

        public void setY(double y) {
            this.y = y;
        }

        public double getY() {
            return y;
        }

        public void setVisit(boolean isVisit) {
            this.isVisit = isVisit;
        }

        public boolean isVisit() {
            return isVisit;
        }

        public int getCluster() {
            return cluster;
        }

        public void setNoised(boolean isNoised) {
            this.isNoised = isNoised;
        }

        public void setCluster(int cluster) {
            this.cluster = cluster;
        }

        public boolean isNoised() {
            return isNoised;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}
