package base.syntax.access

/**
    public    : access all (default)
    private   : access class or kt file
    protected : access sub class
    internal  : access same module (in kt files == module)
*/
open class Outer {
    private val a = 1
    protected open val b = 2
    internal open val c = 3
    public val d = 4  // public by default

    protected class Nested {
        val e: Int = 5
    }
}

class Subclass : Outer() {
    // a is not visible
    // b, c and d are visible
    // Nested and e are visible
    override val b = 5 // 'b' is protected
    override val c = 3 // 'c' is internal
}

class Unrelated(o: Outer) {
    // a, b are not visible
    // c and d are visible (same module)
    // Outer.Nested is not visible, and Nested::e is not visible either
}