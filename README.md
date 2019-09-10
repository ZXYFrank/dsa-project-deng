# dsa-project-deng

​	Implementation and optimization of the projects given or raised in DengJunhui‘s MOOC(DSA / THU.CS). Appreciate criticism and all kinds of advice!
​	清华大学邓俊辉教授的MOOC《数据结构与算法》当中提出了很多实用的，规范的小工程，可以很精确地理解各种数据结构。

- 尽可能遵循软件工程的规范。
- 尽可能注释清晰
- 尽可能实现可读性和可移植性强的算法。
- 尽可能实现抽象和泛化

> 语言只是实现目标的工具，而不是目标本身。
>
> <p align='right'>—<cite>James Gosling</cite></p>

## 1. Calculator and RPN generator using java —— Chap 4 Stack & Queue

<a href="https://sm.ms/image/xmcMRgoiKvJsIXb" target="_blank" ><img src="https://i.loli.net/2019/09/10/xmcMRgoiKvJsIXb.png" ></a>

<a href="https://sm.ms/image/BHuFXqpgTvRAa4M" target="_blank"><img src="https://i.loli.net/2019/09/10/BHuFXqpgTvRAa4M.png" ></a>

1. 实现了基于 Stack 和 Queue 队列的计算器，所有功能封装为 `Calculator`类，部分方法封装成为静态方法。
   1. Stack 实现延迟缓冲
   2. Queue 实现小数读取（好像必要不大）
2. 可以支持小数的读取以及乘方和阶乘运算
3. 支持逆波兰表达式RPN的生成
4. *`formatCheck(String notation)`*有待补充。



大一的时候还不懂这些，刚刚学编程的时候被括号这些东西吓得不轻，曾经想过遍历所有的运算符组合可能性。哈哈那叫什么编程啊。

还是要慢慢进阶的，虽然很慢~

It’s the bottom of the Dynamic Programming Table actually~

还是半抄半写的状态，希望慢慢可以学会 formalize the general thinking pattern for certain problems.

​	这次最大的感想应该是特殊情况的处理，我现在认为这是工程抽象当中很关键的步骤。

​	比如 `(` 出现的时候，左右的运算符都会暂时压入运算符栈中。

这段优先级代码

邓老师的方法采用了优先级矩阵，但是存在明显冗余，其实只要考虑优先级和特殊情况就好。

```java
 public static String orderBetween(Character pre, Character current) {
        System.out.println("***************COMPARING: [" + pre + "] AND [" + current + "]*****************");
        if (pre == '\0' & current == '\0') {
            return done;
        }

        if (pre == '(' || current == '(') {
            // left branket
            if (pre == '(' && current == ')') {
                // when parenthesis pair
                return eliminatePatrenthesis;
            } else {
                // wait for the right bracket
                return wait;
            }
        }

        // tell the correct order between the operators
        int preop = pri.get(pre);
        int nextop = pri.get(current);

        int r = nextop - preop;

        if (r >= 0) {
            // the last opreator needs to wait.
            // coz current opreator has higher priority
            return wait;
        } else {
            // (r < 0)
            // previous opreator can perform
            return calRightNow;
        }
    }

```

>  **延迟缓冲：**对输入的操作需要考虑到之前的输入状态
>
> previous input needs considering.



