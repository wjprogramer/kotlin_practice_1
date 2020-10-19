package design_pattern

/** "Product" */
class Pizza {
    private var dough: String = ""
    private var sauce: String = ""
    private var topping: String = ""

    fun setDough (dough: String)     { this.dough = dough; }
    fun setSauce (sauce: String)     { this.sauce = sauce; }
    fun setTopping (topping: String) { this.topping = topping; }

    override fun toString(): String  { return "dough: $dough, sauce: $sauce, topping: $topping"; }
}

/** "Abstract Builder" */
abstract class PizzaBuilder {
    lateinit var pizza: Pizza
        protected set

    fun createNewPizzaProduct() { pizza = Pizza(); }

    abstract fun buildDough()
    abstract fun buildSauce()
    abstract fun buildTopping()
}

/** "ConcreteBuilder" */
class HawaiianPizzaBuilder: PizzaBuilder() {
    override fun buildDough()   { pizza.setDough("cross"); }
    override fun buildSauce()   { pizza.setSauce("mild"); }
    override fun buildTopping() { pizza.setTopping("ham+pineapple"); }
}

/** "ConcreteBuilder" */
class SpicyPizzaBuilder: PizzaBuilder() {
    override fun buildDough()   { pizza.setDough("pan baked"); }
    override fun buildSauce()   { pizza.setSauce("hot"); }
    override fun buildTopping() { pizza.setTopping("pepperoni+salami"); }
}


/** "Director" */
class Waiter {
    lateinit var pizzaBuilder: PizzaBuilder

    fun getPizza(): Pizza { return pizzaBuilder.pizza }

    fun constructPizza() {
        pizzaBuilder.createNewPizzaProduct()
        pizzaBuilder.buildDough()
        pizzaBuilder.buildSauce()
        pizzaBuilder.buildTopping()
    }
}

/** A customer ordering a pizza. */
// ref: https://zh.wikipedia.org/wiki/%E7%94%9F%E6%88%90%E5%99%A8%E6%A8%A1%E5%BC%8F
fun main() {
    val waiter = Waiter()
    val hawaiianPizzaBuilder: PizzaBuilder = HawaiianPizzaBuilder()
    val spicyPizzaBuilder: PizzaBuilder = SpicyPizzaBuilder()

    waiter.pizzaBuilder = hawaiianPizzaBuilder
    waiter.constructPizza()

    val pizza: Pizza = waiter.getPizza()
    println(pizza)
}