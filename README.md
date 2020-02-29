# java8-learn
- 学习张龙老师java8所作的相关笔记，仅供参考，如果有错的地方希望帮忙改进，感谢！


## Stream特性

特性：惰性计算（中间操作都是返回Stream对象），及时求值（终止操作不返回Stream对象或不返回对象）

- 外部迭代-Java8之前
- 你不迭代-Java8+

集合关注的是数据与数据存储本身
流关注的是对数据的计算

流与迭代器类似的一点：无法重复使用或者消费。

## collect 和 Collector
 
- collect: 收集器。
- Collector: collect的参数。
- Collector: 他是一个接口，也是一个可变的汇聚操作，将输入元素累积到一个可变的容器当中；它会在所有元素都处理完毕后，将累积的结果转换为一个最终结果表示（这是一个可选操作）；他支持串行于并行两种操作。
- Collectors: 本身实际上是一个工厂，提供了关于Collector的常见汇聚实现。可以复合，组合使用。
- 为了确保串行与并行操作结果的等价性，Collector函数需要满足2个条件：identity（统一性）与associativity（结合性）。
- a == combiner.apply(a, supplier.get()) —— 举例 (list1, list2) -> {list1.addAll(list2); return list1} 因为list2是空的，所以执行完成后还是相等


## 自定义Collector
- 注意`Characteristics.IDENTITY_FINISH`告诉底层JDK实现，combiner不需要调用`finisher`方法，直接将容器值返回。


## 对于Collectors静态工厂类来说，其实现一共分为两种情况

+ 通过CollectorImpl来实现
+ 通过reducing方法来实现，reducing方法本身又是通过CollectorImpl来实现的。