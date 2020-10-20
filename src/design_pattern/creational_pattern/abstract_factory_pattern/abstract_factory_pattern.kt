// ref:
// https://zh.wikipedia.org/wiki/%E6%8A%BD%E8%B1%A1%E5%B7%A5%E5%8E%82
package design_pattern.creational_pattern.abstract_factory_pattern

interface Button
interface Border

class MacButton: Button
class MacBorder: Border

class WinButton: Button
class WinBorder: Border

class MacFactory {
    companion object {
        fun createButton(): Button {
            return MacButton()
        }
        fun createBorder(): Border {
            return MacBorder()
        }
    }
}

class WinFactory {
    companion object {
        fun createButton(): Button {
            return WinButton()
        }
        fun createBorder(): Border {
            return WinBorder()
        }
    }
}


