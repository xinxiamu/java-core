`ScheduledExecutorService` 是 Java 中用于进行定时任务调度的接口，它是 ExecutorService 的一个子接口，提供了一些方法用于按照计划执行任务。相对于早期的 `Timer` 类，`ScheduledExecutorService` 更加灵活、可靠，更适用于并发环境。

以下是关于 `ScheduledExecutorService` 的基本概念和使用方法：

### 1. 基本概念：

- **ScheduledExecutorService 接口：** 位于 `java.util.concurrent` 包中，是一个用于周期性执行任务的接口。
- **ScheduledThreadPoolExecutor 类：** 为 `ScheduledExecutorService` 接口的一个具体实现，是一个带有调度功能的线程池。

### 2. 使用方法：

#### 创建 ScheduledExecutorService 实例：

```java
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
```

#### 创建定时任务：

```java
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

Runnable myTask = () -> {
    System.out.println("Task is running...");
};

// 延迟1秒后执行任务
ScheduledFuture<?> future = scheduler.schedule(myTask, 1, TimeUnit.SECONDS);

// 延迟1秒后每隔2秒执行一次任务
ScheduledFuture<?> recurringFuture = scheduler.scheduleAtFixedRate(myTask, 1, 2, TimeUnit.SECONDS);
```

#### 关闭 ScheduledExecutorService：

```java
scheduler.shutdown();
```

### 3. 注意事项：

- `ScheduledExecutorService` 支持多线程并发执行任务，适用于高并发环境。
- 任务的执行时间长短不会影响下一次任务的执行时间。
- 使用 `scheduleAtFixedRate` 方法可以实现周期性执行任务，而 `scheduleWithFixedDelay` 方法可以实现任务执行结束后延迟一段时间再执行下一次任务。

### 示例：

以下是一个简单的示例，演示了如何使用 `ScheduledExecutorService` 进行定时任务调度：

```java
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable myTask = () -> {
            System.out.println("Task is running...");
        };

        // 延迟1秒后执行任务
        ScheduledFuture<?> future = scheduler.schedule(myTask, 1, TimeUnit.SECONDS);

        // 延迟1秒后每隔2秒执行一次任务
        ScheduledFuture<?> recurringFuture = scheduler.scheduleAtFixedRate(myTask, 1, 2, TimeUnit.SECONDS);

        // 主线程等待一段时间后关闭 scheduler
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 关闭 scheduler
        scheduler.shutdown();
    }
}
```

在这个示例中，创建了一个 `ScheduledExecutorService` 实例，并使用 `schedule` 方法和 `scheduleAtFixedRate` 方法安排了两个不同的定时任务。主线程等待一段时间后关闭了 `ScheduledExecutorService`。在实际应用中，可以根据需求设置不同的延迟和周期。
