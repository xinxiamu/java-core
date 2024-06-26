在 Java 中，锁分为公平锁（Fair Lock）和非公平锁（Unfair Lock）。这两种锁的主要区别在于获取锁的顺序上。

### 1. 公平锁（Fair Lock）：

- **定义：** 公平锁是指多个线程按照请求锁的顺序依次获得锁。先到先得，保证了锁的获取是按照先来先得的原则。
- **特点：** 等待锁的线程会按照请求的顺序加入到队列中，当锁释放时，队列头部的线程会获得锁。
- **优点：** 公平锁的优点在于公平性，所有线程都有公平竞争的机会，不会出现饥饿现象。
- **缺点：** 实现上相对复杂，性能相对非公平锁较低，因为每个线程都需要维护一个队列。

在 Java 中，`ReentrantLock` 可以通过构造函数选择创建公平锁：

```java
ReentrantLock fairLock = new ReentrantLock(true); // true 表示创建公平锁
```

### 2. 非公平锁（Unfair Lock）：

- **定义：** 非公平锁是指多个线程获取锁的顺序是不确定的，不保证按照请求的顺序获取锁。允许一个新请求的线程在等待队列为空时插队，直接争夺锁。
- **特点：** 等待锁的线程可能随时被插队，导致某些线程可能一直获取不到锁。
- **优点：** 实现相对简单，性能相对较高，因为线程可以直接尝试获取锁，不需要进入等待队列。
- **缺点：** 可能会出现饥饿现象，某些线程一直获取不到锁。

在 Java 中，`ReentrantLock` 默认创建的是非公平锁：

```java
ReentrantLock unfairLock = new ReentrantLock(); // 默认是非公平锁
```

### 3. 如何选择：

- 如果对锁的公平性要求比较高，希望所有线程都有平等的竞争机会，可以选择公平锁。
- 如果对性能要求比较高，可以选择非公平锁，因为非公平锁的性能通常比公平锁更好。

在实际应用中，选择使用公平锁还是非公平锁要根据具体的业务需求和性能要求进行权衡。
