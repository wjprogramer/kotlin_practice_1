# 範式 Paradigm

from [wiki](https://zh.wikipedia.org/wiki/%E8%8C%83%E5%BC%8F):

> 範式（Paradigm），或典範，又可稱為世界觀（world view），由托馬斯·庫恩《科學革命的結構》提出。在1960年之後是指在科學領域和知識論行文中的思維的方式。
> 
> 範式過去被用來描述科學上截然不同的概念。現在經常用於描述在科學上或者認識論中的的思維方式。

## Dynamic Programming

> Dynamic Programming
>
> = Divide and Conquer + Memoization

動態規劃是分治法的延伸。當遞迴分割出來的問題，
一而再、再而三出現，就運用記憶法儲存這些問題的答案，
避免重複求解，以空間換取時間。

動態規劃的過程，就是反覆地讀取數據、計算數據、儲存數據。

![](../_static/images/DPRecurrence1.png)

1. 把原問題遞迴分割成許多更小的問題。（recurrence）

   1-1. 子問題與原問題的求解方式皆類似。（optimal sub-structure）
   
   1-2. 子問題會一而再、再而三的出現。（overlapping sub-problems）
   
2. 設計計算過程：
   2-1. 確認每個問題需要哪些子問題來計算答案。（recurrence）
   
   2-2. 確認總共有哪些問題。（state space）
   
   2-3. 把問題一一對應到表格。（lookup table）
   
   2-4. 決定問題的計算順序。（computational sequence）
   
   2-5. 確認初始值、計算範圍。（initial states / boundary）
   
3. 實作，主要有兩種方式：

   3-1. Top-down
   
   3-2. Bottom-up
   
## 1. recurrence
 

