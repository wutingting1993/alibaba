# alibaba interview

- 题目一：

设计含最小函数min()、pop()、push()的栈AntMinStack

    要求：
    
    1.AntMinStack实现测试，满足栈特性
    2.要求min、push、pop、的时间复杂度都是O(1)

- 题目二：
  
JAVA中对文件读取的效率会受到文件大小本身的影响，本题目要求能够对于文本文件进行读取，在保证读取效率的同时，要求内存不能溢出。

    要求：
    
    1.输入一个本地文本文件地址，文本文件大小为2G,文本编码类型为utf-8。
    2.读取该文本文件中出现特定单词的数量
    3.把文本部分文件读取到内存中后，即可释放内存，并统计特定单词出现次数和总时间耗时
    4. 尽量减低字符统计耗时。

- 题目三：

设计数据结构与算法，计算算数表达式，需要支持基本计算，加减乘除，满足计算优先级例如输入 3*0+3+8+9*1 输出20。括号，支持括号，例如输入 3+（3-0）*2 输出 9假设所有的数字均为整数，无需考虑精度问题。

    要求：
    
    1.代码结构清晰
    2.数据结构选型合理。


- 题目四：

假设本地有一个文件夹，文件夹下面有若干文件（文件数大于50小于100），文件的存储格式是文本格式（后缀名是.txt)，文件的大小每个文件不会超过100k。
文件格式如下：

2000102，100，98.3

2000103，101，73.3

2000104，102，98.3

2000105，100，101.3

2000106，101，45.3......

文件格式说明：文件每行都由三列构成，第一列是一个id，第二列是分组groupId, 第三列是指标quota。id的数据类型是String, groupId的数据类型String, quota的数据类型float。

功能要求：

1.把所有文件里面的内容按照分组进行排序，输出所有文件按照分组升序排序之后，每个分组下面的最小指标值。比如上面的数据输出结果为：
   
100，2000102，98.3

101，2000106，45.3

102，2000104，98.3

非功能要求

1.文件读取要有线程池来执行，线程池的大小固定为10，文件内容需要存储到指定的内容数据结构当中。

2.查找要求有独立线程来执行，直接消费读取线程池产生的内存数据结构。

3.文件读取和排序要求并发作业，文件读取只要产生了数据，就可以把数据交给排序线程进行消费，计算最小值。

代码要求

1.重上面的要求语意里面抽象出合适的设计模式。

2.需要考虑多线程的并发控制，同步机制。

3.代码实现只能用JDK1.6或者1.8自带的工具类
