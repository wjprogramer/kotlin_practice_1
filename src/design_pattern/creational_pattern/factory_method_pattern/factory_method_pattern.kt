// ref:
// https://zh.wikipedia.org/wiki/%E5%B7%A5%E5%8E%82%E6%96%B9%E6%B3%95
package design_pattern.creational_pattern.factory_method_pattern

// 幾個Button類
open class Button {  }

class WinButton: Button() {  }
class MacButton: Button() {  }

// 他們的工廠類
interface ButtonFactory {
    fun createButton(): Button?
}

class WinButtonFactory : ButtonFactory {
    override fun createButton(): Button {
        return WinButton()
    }
}

class MacButtonFactory : ButtonFactory {
    override fun createButton(): Button {
        return MacButton()
    }
}
