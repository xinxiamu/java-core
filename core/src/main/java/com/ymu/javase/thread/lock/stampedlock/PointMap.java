package com.ymu.javase.thread.lock.stampedlock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;

public class PointMap {
    private final Map<String, Point> points = new HashMap<>();

    private final StampedLock lock = new StampedLock();

        /**
     * 更新点映射
     *
     * @param pointId 点的ID
     * @param newX 新的X坐标
     * @param newY 新的Y坐标
     */
    public void updatePoint(String pointId, double newX, double newY) {
        long stamp = lock.writeLock();
        try {
            // 获取指定ID的点
            Point point = points.get(pointId);
            if (point == null) {
                // 如果点不存在，则创建新的点并添加到点映射中
                point = new Point(newX, newY);
                points.put(pointId, point);
            } else {
                // 如果点存在，则更新点的坐标
                point.setX(newX);
                point.setY(newY);
            }
            System.out.println("Point " + pointId + " updated to (" + newX + ", " + newY + ")");
        } finally {
            // 解锁写操作
            lock.unlockWrite(stamp);
        }
    }


    public Point getPoint(String pointId) {
        // 尝试使用乐观锁读取锁
        long stamp = lock.tryOptimisticRead();
        // 从points中获取指定pointId的点
        Point point = points.get(pointId);

        // 如果乐观锁尝试失败
        if (!lock.validate(stamp)) {
            System.out.println(">>>>获取乐观锁失败，直接获取悲观锁");
            // 使用读取锁
            stamp = lock.readLock();
            try {
                // 重新获取指定pointId的点
                point = points.get(pointId);
            } finally {
                // 解锁读取操作
                lock.unlockRead(stamp);
            }
        }

        // 返回点对象
        return point;
    }

}

class Point {
    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
