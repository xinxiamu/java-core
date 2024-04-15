`java.util.Timer` 类是 Java 中用于进行定时任务调度的工具类。它允许在指定的时间安排一个任务执行，可以一次或重复执行。

以下是关于 `Timer` 类的基本概念和使用方法：

### 1. 基本概念：

- **Timer 类：** 位于 `java.util` 包中，是一个定时器工具，用于计划在未来的某个时间执行任务。
- **TimerTask 类：** 位于 `java.util` 包中，是一个抽象类，表示一个可以由 `Timer` 定时调度的任务。

### 2. 使用方法：

#### 创建 Timer 实例：

```java
import java.util.Timer;

Timer timer = new Timer();
```

#### 创建 TimerTask 实例：

```java
import java.util.TimerTask;

class MyTask extends TimerTask {
    @Override
    public void run() {
        // 定时任务执行的逻辑
        System.out.println("Task is running...");
    }
}

TimerTask myTask = new MyTask();
```

#### 安排任务执行：

```java
// 单次执行任务
timer.schedule(myTask, 1000); // 在1秒后执行

// 重复执行任务，每隔一秒执行一次
timer.schedule(myTask, 1000, 1000);
```

#### 取消任务：

```java
myTask.cancel();
```

### 3. 注意事项：

- `Timer` 不适合在并发环境中使用，因为它的内部是单线程的，如果一个任务执行时间较长，会影响其他任务的执行。
- 如果需要在并发环境中进行定时任务调度，可以考虑使用 `ScheduledExecutorService`。

### 示例：

以下是一个简单的示例，演示了如何使用 `Timer` 和 `TimerTask` 进行定时任务调度：

```java
import java.util.Timer;
import java.util.TimerTask;

public class TimerExample {
    public static void main(String[] args) {
        Timer timer = new Timer();

        TimerTask myTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task is running...");
            }
        };

        // 单次执行任务，延迟1秒执行
        timer.schedule(myTask, 1000);

        // 重复执行任务，延迟1秒后每隔2秒执行一次
        timer.schedule(myTask, 1000, 2000);
    }
}
```

在这个示例中，创建了一个 `Timer` 实例和一个继承自 `TimerTask` 的任务。通过 `schedule` 方法安排任务的执行。在实际应用中，可以根据需求设置不同的延迟和周期。需要注意的是，`Timer` 在异常情况下可能会导致任务调度的不准确性，因此在 Java 5 之后，推荐使用 `ScheduledExecutorService` 代替 `Timer`。
