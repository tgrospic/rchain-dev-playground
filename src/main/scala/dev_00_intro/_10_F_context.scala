package dev_00_intro

object _10_F_context {

  def runImpureValues = {
    println(s"1. Example with IMPURE values (effects are ONLY executed once WHEN value is defined)")
    println(s"====================================================================================")

    // Pure values with effects (printing to console)

    val a = {
      println(s"Get value a")
      "a"
    }

    val b = {
      println(s"Get value b")
      "b"
    }

    // Expressions assigned to a variable

    val ab1 = a + b

    val ab2 = a + b

    // Any new access to `a` and `b` cannot produce effects
    val ab3 = a + b

    println(s"Result value `ab` twice = ${ab1 + ab2}   // effects executed only once when value is defined and assigned")
  }

  // Value is wrapped in a function, so getting the value each time produces its effects

  def runPureValues = {
    println(s"2. Example with PURE values")
    println(s"=========================================================================================")
    println("  - behave algebraically, on definition do nothing and each access produce the same value")
    println("  - every time ACCESSED/EVALUATED will be the same and execute the same effects")

    // Pure values with effects (printing to console)

    val aF = () => {
      println(s"Get value a")
      "a"
    }

    val bF = () => {
      println(s"Get value b")
      "b"
    }

    // Expressions assigned to a variable

    val abF1 = () => aF() + bF()

    val abF2 = () => aF() + bF()

    // Pure values if not used will NOT produce effects
    val abF3 = () => aF() + bF()

    println(s"Result value `abF` twice = ${abF1() + abF2()}   // effects executed twice when used")
  }

  def runPureValuesWrapF = {
    println(s"3. Example with PURE values wrapped in F _container_")
    println("  - behave the same as previous example but now types looks mre familiar")
    println(s"====================================================")

    // Simple wrapper for the same anonymous function (conveniently named F)
    final case class F[A](v: () => A)

    val aF: F[String] = F(() =>
      println(s"Get value a")
      "a"
    )

    val bF: F[String] = F(() =>
      println(s"Get value b")
      "b"
    )

    // Expressions assigned to a variable

    val abF1: F[String] = F(() => aF.v() + bF.v())

    val abF2: F[String] = F(() => aF.v() + bF.v())

    println(s"Result value `abF` twice = ${abF1.v() + abF2.v()}   // effects executed twice when used")
  }
}
