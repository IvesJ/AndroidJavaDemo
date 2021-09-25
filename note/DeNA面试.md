## 1、引用有几种类型？有什么区别？

```java
public class User {
    public String name;
    public int age;

    public User() {

    }
}
```

> 引用有四种类型：强引用、软引用、弱引用、虚引用

```java
//强引用
User user = new User();

System.out.println("-------GC前--------");
System.out.println("强引用：" + user);
System.gc();
System.out.println("-------GC后--------");
System.out.println("强引用：" + user);

运行结果：
-------GC前--------
强引用：review.testclass.User@1b6d3586
-------GC后--------
强引用：review.testclass.User@1b6d3586
```

> **强引用** 就是 平时开发中最常用的引用，new一个实例对象，并通过 “赋值” 将其地址 赋予对应的引用。
>
> 一个对象若**存在强引用**，发生**GC时**：
> 1、对象GC Root可达，它就不会被回收，若此时JVM内存不够，则直接抛出OOM；
> 2、对象GC Root不可达，它就会被回收。

```java
//强引用
User user = new User();
//软引用
SoftReference softReference = new SoftReference(user);
user = null;
System.out.println("-------GC前--------");
System.out.println("软引用：" + softReference.get());
System.gc();
System.out.println("-------GC后--------");
System.out.println("软引用：" + softReference.get());

运行结果：
-------GC前--------
软引用：review.testclass.User@1b6d3586
-------GC后--------
软引用：review.testclass.User@1b6d3586
```

> **软引用**
> 一个对象若**存在软引用**（**不存在强引用**），发生**GC时**：
> 1、对象GC Root可达，
> 			如果内存足够，就不会被回收；
> 			如果内存不够用，会被回收；   【代码中未展示这种情况，可自行验证】；
> 2、对象GC Root不可达，它就会被回收。
>
> 软引用使用场景：
> 1、**创建缓存**：在内存足够的情况下进行缓存，提升速度，内存不足时JVM自动回收

```java
//强引用
User user = new User();
//弱引用
WeakReference weakReference = new WeakReference(user);
user = null;
System.out.println("-------GC前--------");
System.out.println("弱引用：" + weakReference.get());
System.gc();
System.out.println("-------GC后--------");
System.out.println("弱引用：" + weakReference.get());

运行结果：
-------GC前--------
弱引用：review.testclass.User@1b6d3586
-------GC后--------
弱引用：null
```

> **弱引用**
> 一个对象**存在弱引用**（**不存在强引用、软引用**），发生**GC时**：被**回收**。
>
> 弱引用使用场景：
> 1、**创建临时缓存**：生命周期 比 软引用创建的缓存 短
> 2、**ThreadLocalMap 防止内存泄漏**
> 3、**WeakHashMap** 内部管理 Entry

```java
//虚引用
ReferenceQueue referenceQueue = new ReferenceQueue();
PhantomReference phantomReference = new PhantomReference(user, referenceQueue);
user = null;
System.out.println("-------GC前--------");
System.out.println("虚引用：" + phantomReference.get());
System.out.println("引用队列：" + referenceQueue.poll());
System.gc();
try {
   hread.sleep(2000);
} catch (InterruptedException e) {
   e.printStackTrace();
}
System.out.println("-------GC后--------");
System.out.println("虚引用：" + phantomReference.get());
System.out.println("引用队列：" + referenceQueue.poll());

运行结果：
-------GC前--------
虚引用：null
引用队列：null
-------GC后--------
虚引用：null
引用队列：java.lang.ref.PhantomReference@1b6d3586
```

> **虚引用**：虚引用需要配合 **引用队列** 使用才有意义
> 1、一个对象**存在虚引用（不存在强引用、软引用、弱引用）**，**并不影响GC，而是为了在对象被GC时，能够收到一个系统通知**；
> 2、虚引用必须要配合 **ReferenceQueue** ，当GC准备回收一个对象，如果发现它还有虚引用，就会在回收之前，把这个虚引用加入到与之关联的`ReferenceQueue`中。
>
> 虚引用的使用场景：
> 1、**NIO利用虚引用来管理内存**
> 2、**对象销毁前的一些操作，比如说资源释放等**

## 2、HashMap的原理是什么？如果HashMap存储大量数据，会有什么不足吗？

> 1、HashMap的数据结构：
>
> 2、HashMap的put方法：
>
> 3、HashMap的get方法：
>
> 4、HashMap的扩容机制：
>
> 5、HashMap的使用场景、优点和缺点：

## 3、equal 和 == 有什么区别？

> A、讨论 **equal**  和 **==** 的区别，则要根据 **变量类型** 做区分：
> 1、当变量为 **基本数据类型【整数类型、浮点类型、字符类型、布尔类型】** 时，**变量存储的是 基本数据的值**：
> 		【 **==** 比较的便是变量存储的 **值** 】
> 		【 基本数据类型  **没有 equal ** 方法 】
>
> 2、当变量为 **引用数据类型【类、接口、数组】** 时，**变量存储的是 地址，该地址为实际对象的存储地址**：
> 		【 **==** 比较的便是变量存储的 **地址 （比较两个引用是否引用内存中的同一个对象）】
> 		【 **equal** 默认比较的是 **实际对象的存储地址（等同于 ==）** ，但是若equal方法被重写，则**按照重写的逻辑进行比较**】 
>
> B、【拓展】**hashCode** 与 **equal** 的区别
>
> 【**hashCode() 的作用是获取哈希码，也称为散列码；它实际上是返回一个 int 整数。这个哈希码的作用是确定该对象在哈希表中的索引位置。**】
>
> 1. 如果两个对象相等，则 **hashcode** 一定也是**相同**的
> 2. 两个**对象相等,对两个对象分别调用hashCode、equals 方法都返回 true**
> 3. 两个对象**有相同的 hashcode 值，它们也不一定是相等的**
> 4. 因此，**equals 方法被覆盖过，则 hashCode 方法也必须被覆盖**
> 5. hashCode() 的默认行为是对堆上的对象产生独特值。如果没有重写hashCode()，则该 class 的两个对象无论如何都不会相等（即使这两个对象指向相同的数据）

## 4、说一下Java的类加载过程



## 5、说一下Android中的主线程是如何工作的



## 6、说一下你阅读过的开源框架，有什么收获？



## 7、Android中存储数据的方式有哪些，有什么区别？



## 8、谈一下组件化和插件化

