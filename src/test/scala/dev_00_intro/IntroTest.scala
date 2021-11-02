package dev_00_intro

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class IntroTest extends AnyFlatSpec with Matchers {

  it should "run examples with F context" in {
    import _10_F_context.*

    println("\n")

    runImpureValues

    println("\n")

    runPureValues

    println("\n")

    runPureValuesWrapF
  }

  it should "run examples with F Monad" in {
    import _20_F_Monad.*

    println("\n")

    runPureValuesWrapFMonad
  }

}
