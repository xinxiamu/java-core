## 新的时间api设计的几个原则：	
1.明确的。需要明确的创建时间，否则抛空指针异常。
2.流畅的api。提供更加简单、流畅、可读的api接口。
3.不可变的。时间对象一旦创建，就是不可改变的，线程安全。
4.可扩展的。提供了良好的扩展性，甚至你都可以定义自己的日历系统。

## 概述

- Instant——它代表的是时间戳

- LocalDate——不包含具体时间的日期，比如2014-01-14。它可以用来存储生日，周年纪念日，入职日期等。

- LocalTime——它代表的是不含日期的时间

- LocalDateTime——它包含了日期及时间，不过还是没有偏移信息或者说时区。

- ZonedDateTime——这是一个包含时区的完整的日期时间，偏移量是以UTC/格林威治时间为基准的。

