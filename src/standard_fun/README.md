# Standard Function

Kotlin 的 Standard Library 提供了幾種 Function ，

有些可以處理可為空變數。老實說如果使用到最後，

會發現其實它們大多數是可以互相替換的。它們並沒有什麼特殊的特性，

目的只是提升程式的語意，增加閱讀性而生。

## `T.run` 與 `let` (`T.let`)

需求像是希望能自訂 `identifier` 時，或是希望 `this` 可以存取到上層內容時，建議使用 `let` 。

### `T.run`

`this` 為型別 `T`，要使用外部的 `this`，加上 `@XXX`

```
T.run { // this: T 
    this@XXX
}
```

### `let`

`let` 使用的是 `it`

```
let {
    it.xxx()
}
```

或是自己定義名稱

```
let { xxx ->
    !@#$%^
    #$%^&*
}
```

## `also` (`T.also`) 與 `apply`

#### 相似

`also` 和 `apply` 決大部分也是使用於初始化物件。

前面提到：這幾種 Standard Library Function 其實可以互相替換，

選擇合適的場景使用即可。

#### 相異

- 不同的地方是 `apply` 在 scope 內 `T` 的存取方式是 `this` ，其他都與 `also` 一樣。

## 各種差異

- `run` 與 `let` 會將最後一行傳給下個 Chain 或是回傳，物件類型依最後一行而定； 
- `also` 和 `apply` **將「自己 (this)」回傳或傳入下個 chain。**

    (有點像是 builder pattern ，做完一次設定後又將自己回傳回去。
    
    另外， also在 scope 內可以透過 `it` 來存取 `T` 本身。)

## 結論

這些 Standard Library 提供的 Function 其實大同小異。用的時候除了語意以外，還有什麼選擇方式？
    
1. 要傳遞最後一行，還是傳遞自己？
2. 是否需要 extension function 先判斷可為空的變數？
3. Scope 內想透過 this 或 it 存取 T？
    考量可以是：是否需要存取外層的變數，identifier 是否可以依需求自由命名

---

先判斷 1.，假設情境需要傳遞自己，即有 `apply` 或 `also` 可以選擇。

再用 3. 判斷目前情境適合哪一個： `apply` 透過 `this` 而 `also` 透過 `it` 來存取傳入變數。

---

如果需要傳遞最後一行，有四個選項： `run`、 `T.run`、 `with` 和 `let` 。

先用 2. 判斷是否需要預先判斷可為空變數。

- 不需要先判斷，那麼就剩： `run` 和 `with` 。
    - `with` 可以在 scope 內透過 `this` 存取傳入變數； 
    - `run` 沒有任何傳入變數，但可以將最後一行傳遞出去。
- 需要先判斷，即為 `T.run` 和 `let` 。
    再用 3. 判斷哪個適合目前的情境： 
    - T.run 透過 this ，
    - let 則是透過 it 來存取傳入的變數。

## ref

- [簡介 Kotlin: run, let, with, also 和 apply](https://medium.com/@louis383/%E7%B0%A1%E4%BB%8B-kotlin-run-let-with-also-%E5%92%8C-apply-f83860207a0c)




