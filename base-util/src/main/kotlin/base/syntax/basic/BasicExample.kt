package base.syntax.basic

fun main() {
    println("Hello world!")
    hello("!")
}

fun hello(word: String) {
    println("Hello world!$word")
}

fun valvar() {
    val a: Int = 0 // immutable
    var b: Int = 0 // mutable
}

class User(val name: String) {
    //...
}

class Member constructor(name: String = "default name") {
    private val nickName: String = name
    private val user: User

    init {
        //초기화 작업
        user = User(name = "myname")
    }
    //...
}

open class View {
    constructor(ctx: String) {
        //...
    }

    constructor(ctx: String, attr: String) {
        //...
    }
}

class button : View {
    constructor(ctx: String) : this(ctx, "attr") { //밑에 생성자를 호출
        //...
    }

    constructor(ctx: String, attr: String) : super(ctx, attr) { //부모 클래스의 생성자를 호출
        //...
    }
}