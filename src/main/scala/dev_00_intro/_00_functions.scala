package dev_00_intro

object _00_functions {
  // The same function can be defined in multiple ways
  val f: Int => Int => Int   = a => b => a + b
  def g(a: Int)(b: Int): Int = a + b
  def h(a: Int, b: Int): Int = a + b

  // Calling depends how arguments are treated
  val fr = f(1)(2) // 2 functions with 1 argument
  val gr = g(1)(2) //   called one after another
  val hr = h(1, 2) // 1 function with 2 args

  val f2  = Function.uncurried(f) // convert to 2 args func
  val f2r = f2(1, 2)

  val h2  = (h _).curried // convert 2 args to func returning func
  val h2r = h2(1)(2)

  // Function with tuple as one argument
  def k(t: (Int, Int)) = t._1 + t._2

  val kr1 = k((1, 2))
  val kr2 = k(1, 2) // WTF? this also works

  val k1  = Function.untupled(k _) // convert from tuple argument to multiple arguments
  val k1r = k1(1, 2)               // 2 args function

  // The same function with => (arrow) notation
  val k2: ((Int, Int)) => Int = { case (a, b) => a + b }

  val k3  = Function.untupled(k2) // convert to 2 args function, (_) is not needed!
  val k3r = k3(1, 2)              // accepts 2 args

  // Function with multiple arguments can be converted to accepting on tuple
  val m   = (h _).tupled
  val mr1 = m((1, 2)) // accepts 1 arg tuple

  val n  = (f(_: Int)(_: Int)).tupled // requires types for arguments
  val nr = n((1, 2))                  // accepts 1 arg tuple

  // Case classes are like a functions
  case class MyClass(a: Int, b: Int)

  // Supplied only first argument, returns a function with second argument
  // - `apply` is special keyword to access underlying function of the object created by Scala
  //    https://stackoverflow.com/a/9738862
  val p: Int => MyClass = MyClass.apply.curried(1)
  val pr: MyClass       = p(2)

  // Tuple can be partially applied to get the function accepting second argument
  val r: Int => (Int, Int) = ((_: Int, _: Int)).curried(1)
  val rr: (Int, Int)       = r(2)
}
