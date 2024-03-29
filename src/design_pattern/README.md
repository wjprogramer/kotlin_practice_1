# Design Pattern

https://zh.wikipedia.org/wiki/%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F_(%E8%AE%A1%E7%AE%97%E6%9C%BA)

## 建立型模式 Creational pattern

| 完成 | 模式名稱 | 敘述 |
| :--- | :--- | :--- |
| ✔️ | 抽象工廠模式 | 	為一個產品族提供了統一的建立介面。當需要這個產品族的某一系列的時候，可以從抽象工廠中選出相應的系列建立一個具體的工廠類。 |
| ✔️ | 工廠方法模式 | 	定義一個介面用於建立物件，但是讓子類決定初始化哪個類。工廠方法把一個類的初始化下放到子類。 |
| ✔️ | 生成器模式 | 	將一個複雜物件的構建與它的表示分離，使得同樣的構建過程可以建立不同的表示。 |
| ✔️ | 惰性初始模式 | 	推遲物件的建立、資料的計算等需要耗費較多資源的操作，只有在第一次存取的時候才執行。 |
| ✔️ | 物件池模式 | 	通過回收利用物件避免取得和釋放資源所需的昂貴成本。 |
| ✔️ | 原型模式 | 	用原型實例指定建立物件的種類，並且通過拷貝這些原型,建立新的物件。 |
| ✔️ | 單例模式 | 	確保一個類只有一個實例，並提供對該實例的全域存取。 |
| ✔️ | 多例模式 | 	確保一個類只有命名的實例，並提供對這些實例的全域存取。 |
|   | 資源取得為初始化 | 	通過繫結到合適物件的生命周期來確保資源被適當地釋放。 |

## 結構型模式 Structural pattern

| 完成 | 模式名稱 | 敘述 |
| :--- | :--- | :--- |
|  | 配接器模式 | 	將某個類的介面轉換成客戶端期望的另一個介面表示。配接器模式可以消除由於介面不匹配所造成的類相容性問題。 |
|  | 橋接模式 | 	將一個抽象與實現解耦，以便兩者可以獨立的變化。 |
|  | 組合模式 | 	把多個物件組成樹狀結構來表示局部與整體，這樣用戶可以一樣的對待單個物件和物件的組合。 |
|  | 修飾模式 | 	向某個物件動態地添加更多的功能。修飾模式是除類繼承外另一種擴充功能的方法。 |
|  | 外觀模式 | 	為子系統中的一組介面提供一個一致的介面， 外觀模式定義了一個高層介面，這個介面使得這一子系統更加容易使用。 |
|  | 享元 | 	通過共享以便有效的支援大量小顆粒物件。 |
|  | 代理 | 	為其他物件提供一個代理以控制對這個物件的存取。 | 

## 行為型模式 Behavioral pattern

| 完成 | 模式名稱 | 敘述 |
| :--- | :--- | :--- |
|  | 黑板 | 	廣義的觀察者在系統範圍內交流資訊，允許多位讀者和寫者。 | 
|  | 責任鏈 | 	為解除請求的傳送者和接收者之間耦合，而使多個物件都有機會處理這個請求。將這些物件連成一條鏈，並沿著這條鏈傳遞該請求，直到有一個物件處理它。 |
|  | 命令 | 	將一個請求封裝為一個物件，從而使你可用不同的請求對客戶進行參數化；對請求排隊或記錄請求紀錄檔，以及支援可取消的操作。 |
|  | 直譯器 | 	給定一個語言, 定義它的文法的一種表示，並定義一個直譯器, 該直譯器使用該表示來解釋語言中的句子。 |
|  | 疊代器 | 	提供一種方法順序存取一個聚合物件中各個元素, 而又不需暴露該物件的內部表示。 |
|  | 中介者 | 	包裝了一系列物件相互作用的方式，使得這些物件不必相互明顯作用，從而使它們可以鬆散偶合。當某些物件之間的作用發生改變時，不會立即影響其他的一些物件之間的作用，保證這些作用可以彼此獨立的變化。 |
|  | 備忘錄 | 	備忘錄物件是一個用來儲存另外一個物件內部狀態的快照的物件。備忘錄模式的用意是在不破壞封裝的條件下，將一個物件的狀態捉住，並外部化，儲存起來，從而可以在將來合適的時候把這個物件還原到儲存起來的狀態。 |
|  | 空物件 | 	通過提供預設物件來避免空參照。 |
|  | 觀察者模式 | 	在物件間定義一個一對多的聯絡性，由此當一個物件改變了狀態，所有其他相關的物件會被通知並且自動重新整理。 |
|  | 規格 | 	以布林形式表示的可重繫結的商業邏輯。 | 
|  | 狀態 | 	讓一個物件在其內部狀態改變的時候，其行為也隨之改變。狀態模式需要對每一個系統可能取得的狀態創立一個狀態類的子類。當系統的狀態變化時，系統便改變所選的子類。 |
|  | 策略 | 	定義一個演算法的系列，將其各個分裝，並且使他們有互動性。策略模式使得演算法在用戶使用的時候能獨立的改變。 |
|  | 模板方法 | 	模板方法模式準備一個抽象類，將部分邏輯以具體方法及具體構造子類的形式實現，然後聲明一些抽象方法來迫使子類實現剩餘的邏輯。不同的子類可以以不同的方式實現這些抽象方法，從而對剩餘的邏輯有不同的實現。先構建一個頂級邏輯框架，而將邏輯的細節留給具體的子類去實現。 |
|  | 存取者 | 	封裝一些施加於某種資料結構元素之上的操作。一旦這些操作需要修改，接受這個操作的資料結構可以保持不變。存取者模式適用於資料結構相對未定的系統，它把資料結構和作用於結構上的操作之間的耦合解脫開，使得操作集合可以相對自由的演化。 | 

## 併發型模式 Concurrency pattern

| 完成 | 模式名稱 | 敘述 |
| :--- | :--- | :--- |
| | 併發型模式 | |
| | 主動物件 | | 	
| | 阻礙 | | 	
| | 雙重檢查鎖定 | | 	
| | 守衛 | | 	
| | 領導者/追隨者 | | 	
| | 監測物件模式 | | 	
| | 讀寫鎖 | | 	
| | 排程 | | 	
| | 執行緒池模式 | | 	
| | 執行緒特定儲存 | | 	
| | 反應器 | | 	 