# Stack

- empty()
- push
- top
- pop

> attention that popped from stack is Object, not int;
>
> ```java
> int x = (int)stack.pop();
> // 
> ```

## 典型应用场合

- 逆序输出 conversion
- 递归嵌套 stack permutatio（栈混洗） + parenthesis
- 延迟缓冲 evaluation
- 栈式计算 RPN（reverse Polish notation）等

### 栈与递归

#### **函数调用栈**

1. 调用栈和执行栈

### 递归消除

- tabulation / memoization strategy
- DP 动态规划

### 逆序输出

进制转换算法！

```java
public int convert(long ,int[] bases){
    // for hexadecimal, as [1,2,3,4,5,6,...,a,b,c,d,e,f]
    int base = bases.length;
    Stack s = new Stack();
    while(n>0){
        s.push(bases[n % base]);
        n /= base;
    }
}
```

#### 栈混洗问题 stack permutation

<img src='https://i.loli.net/2019/09/05/XI9cgndpDQ2AFZw.png' width='50%'>

利用**卡特兰数**表示栈混洗的可能性
$$
\text{Catalan}(n) = \text{SP}(n) = \sum_{k = 1}^{n} \text{SP}(n-k)*\text{SP}(k-1) = \frac{1}{1+n}\binom{2n}{n}
\\
\text{SP}(1) = 1
$$
其含义为：考察k号元素被推入S中的情况，此时之前已经有 (k-1)个元素参与了栈混洗，顺序已经确定。再其进入之后，之后的n-k 个元素的栈混洗还未进行，且二者**相互独立**，符合**乘法原理**

### 延迟缓冲

#### infix 表达式求值

<a href="https://sm.ms/image/4GUiDfwaAN21Tsl" target="_blank"><img src="https://i.loli.net/2019/09/09/4GUiDfwaAN21Tsl.png" width='50%'></a>

#### RPN - parenthesis-free 

# Queue

FIFO

社会资源和计算资源的分配当中广泛应用。
