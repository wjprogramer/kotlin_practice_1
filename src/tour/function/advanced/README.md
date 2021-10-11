# Function

## inline × noinline × crossinline

[ref](https://iter01.com/194255.html)

Kotlin 針對函式提供了幾個關鍵字 inline noinline crossinline，其涉及 Kotlin 中行內函數和 `lambda` 相關的問題。

概覽

- `inline`: 宣告在編譯時，將函式的程式碼拷貝到呼叫的地方(內聯)
- `oninline`: 宣告 `inline` 函式的形參中，不希望內聯的 `lambda`
- `crossinline`: 表明 `inline` 函式的形參中的 `lambda` 不能有 `return`

### Demo

- [inline_1.kt](inline_1.kt)
- []()
- []()

### inline function

使用 `inline` 宣告的函式，在編譯時將會拷貝到呼叫的地方。

Ex: 

定義一個 `sum` 函式計算兩個數的和。

```kotlin
fun main(args: Array<String>) {
    println(sum(1, 2))
}

fun sum(a: Int, b: Int): Int {
    return a + b
}
```

反編譯為 Java 程式碼:

```java
public static final void main(@NotNull String[] args) {
   int var1 = sum(1, 2);
   System.out.println(var1);
}

public static final int sum(int a, int b) {
   return a + b;
}
```

---

然後為 `sum` 函式新增 `inline` 宣告:

```kotlin
inline fun sum(a: Int, b: Int): Int {
    return a + b
}
```

再反編譯為 Java 程式碼:

```java
public static final void main(@NotNull String[] args) {
   //...
   byte a$iv = 1;
   int b$iv = 2;
   int var4 = a$iv + b$iv;
   System.out.println(var4);
}

public static final int sum(int a, int b) {
   return a + b;
}
```

`sum` 函式的實現程式碼被直接拷貝到了呼叫的地方。

---

上面兩個使用例項並沒有體現出 `inline` 的優勢。當你的函式中有 `lambda` 形參時，`inline` 的優勢才會體現。

---

### inline function with lambda parameters

```kotlin
fun sum(a: Int, b: Int, lambda: (result: Int) -> Unit): Int {
    val r = a + b
    lambda.invoke(r)
    return r
}

fun main(args: Array<String>) {
    sum(1, 2) { println("Result is: $it") }
}
```

反編譯為 Java:

```java

public static final int sum(int a, int b, @NotNull Function1 lambda) {
   //...
   int r = a + b;
   lambda.invoke(r);
   return r;
}

public static final void main(@NotNull String[] args) {
   //...
   sum(1, 2, (Function1)null.INSTANCE);
}
```

> (Function1）null.INSTANCE，是由於反編譯器工具在找不到等效的 Java 類時的顯示的結果。

我傳遞的那個 `lambda` 被轉換為 `Function1` 型別，它是 Kotlin 函式（kotlin.jvm.functions包）的一部分，它以 1 結尾是因為我們在 `lambda` 函式中傳遞了一個引數（`result：Int`)。

再考慮如下程式碼:

```kotlin
fun main(args: Array<String>) {
    for (i in 0..10) {
        sum(1, 2) { println("Result is: $it") }
    }
}
```

我在迴圈中呼叫 `sum` 函式，每次傳遞一個 `lambda` 列印結果。反編譯為 Java:

```java
for(byte var2 = 10; var1 <= var2; ++var1) {
    sum(1, 2, (Function1)null.INSTANCE);
}
```

可見在每次迴圈裡都會建立一個 `Function1` 的例項物件。這裡就是效能的優化點所在，如何避免在迴圈裡建立新的物件？

---

1. 在迴圈外部建立 lambda 物件

    ```kotlin
    val l: (r: Int) -> Unit = { println(it) }
    
    for (i in 0..10) {
        sum(1, 2, l)
    }
    ```
   
    反編譯為 Java:

    ```java
    Function1 l = (Function1)null.INSTANCE;
    int var2 = 0;
    
    for(byte var3 = 10; var2 <= var3; ++var2) {
        sum(1, 2, l);
    }
    ```

    只會建立一個 Function 物件

2. 使用 inline:

    ```kotlin
    fun main(args: Array<String>) {
        for (i in 0..10) {
            sum(1, 2) { println("Result is: $it") }
        }
    }
    
    inline fun sum(a: Int, b: Int, lambda: (result: Int) -> Unit): Int {
        val r = a + b
        lambda.invoke(r)
        return r
    }
    ```
    
    反編譯為 Java:
    
    ```java
    public static final void main(@NotNull String[] args) {
       //...
       int var1 = 0;
    
      for(byte var2 = 10; var1 <= var2; ++var1) {
         byte a$iv = 1;
         int b$iv = 2;
         int r$iv = a$iv + b$iv;
         String var9 = "Result is: " + r$iv;
         System.out.println(var9);
      }
    }
    ```

    lambda 程式碼在編譯時被拷貝到呼叫的地方， 避免了建立 Function 物件。

### inline 注意事項

public inline 函式不能訪問私有屬性

```kotlin
class Demo(private val title: String) {

    inline fun test(l: () -> Unit) {
        println("Title: $title") // 編譯錯誤: Public-Api inline function cannot access non-Public-Api prive final val title
    }

    // 私有的沒問題
    private inline fun test(l: () -> Unit) {
        println("Title: $title")
    }
}
```

注意程式控制流

---

當使用 `inline` 時，如果傳遞給 `inline` 函式的 `lambda`，有 `return` 語句，那麼會導致閉包的呼叫者也返回。

例子:

```kotlin
inline fun sum(a: Int, b: Int, lambda: (result: Int) -> Unit): Int {
    val r = a + b
    lambda.invoke(r)
    return r
}

fun main(args: Array<String>) {
    println("Start")
    sum(1, 2) {
        println("Result is: $it")
        return // 這個會導致 main 函式 return
    }
    println("Done")
}
```

反編譯 Java:

```java
public static final void main(@NotNull String[] args) {
   String var1 = "Start";
   System.out.println(var1);
   byte a$iv = 1;
   int b$iv = 2;
   int r$iv = a$iv + b$iv;
   String var7 = "Result is: " + r$iv;
   System.out.println(var7);
}
```

反編譯之後也能看到，lambda return 之後的程式碼不會執行。

---

#### 如何避免?

可以使用 return@label 語法，返回到 lambda 被呼叫的地方。

```kotlin
fun main(args: Array<String>) {
    println("Start")
    sum(1, 2) {
        println("Result is: $it")
        return@sum
    }
    println("Done")
}
```

### noinline

當一個 `inline` 函式中，有多個 `lambda` 作為引數時，可以在不想內聯的 `lambda` 前使用 `noinline` 宣告.

```kotlin
inline fun sum(a: Int, b: Int, lambda: (result: Int) -> Unit, noinline lambda2: (result: Int) -> Unit): Int {
    val r = a + b
    lambda.invoke(r)
    lambda2.invoke(r)
    return r
}

fun main(args: Array<String>) {
    sum(1, 2,
            { println("Result is: $it") },
            { println("Invoke lambda2: $it") }
    )
}
```

反編譯 Java:

```java
public static final int sum(int a, int b, @NotNull Function1 lambda, @NotNull Function1 lambda2) {
   int r = a + b;
   lambda.invoke(r);
   lambda2.invoke(r);
   return r;
}

public static final void main(@NotNull String[] args) {
   byte a$iv = 1;
   byte b$iv = 2;
   Function1 lambda2$iv = (Function1)null.INSTANCE;
   int r$iv = a$iv + b$iv;
   String var8 = "Result is: " + r$iv;
   System.out.println(var8);
   lambda2$iv.invoke(r$iv);
}
```

第一個 `lambda` 內聯到了呼叫處，而第二個使用 `noinline` 宣告的 `lambda` 沒有。

### crossinline

宣告一個 `lambda` 不能有 `return` 語句(可以有 `return@label` 語句)。這樣可以避免使用 `inline` 時，`lambda` 中的 `return` 影響程式流程。

```kotlin
inline fun sum(a: Int, b: Int, crossinline lambda: (result: Int) -> Unit): Int {
    val r = a + b
    lambda.invoke(r)
    return r
}

fun main(args: Array<String>) {
    sum(1, 2) {
        println("Result is: $it")
        return  // 編譯錯誤: return is not allowed here
    }
}
```

總結

- 使用 `inline`，行內函數到呼叫的地方，能減少函式呼叫造成的額外開銷，在迴圈中尤其有效
- 使用 `inline` 能避免函式的 `lambda` 形參額外建立 `Function` 物件
- 使用 `noinline` 可以拒絕形參 `lambda` 內聯
- 使用 `crossinline` 顯示宣告 `inline` 函式的形參 `lambda` 不能有 `return` 語句，避免 `lambda` 中的 `return` 影響外部程式流程

