package dev_00_intro

import annotation.targetName

object _20_F_Monad {

  def runPureValuesWrapFMonad = {
    println(s"1. Example with PURE values wrapped in F _container_")
    println(s"====================================================")

    /**
      * Simplified view of abstract `F[_]` type used in cats or scalaz with basic _monadic_ methods.
      */
    final case class F[A](v: () => A) {

      /**
        * Functor: https://github.com/purescript/purescript-prelude/blob/v3.1.0/src/Data/Functor.purs#L24-L25
        *   map: (A => B) => F[A] => F[B]
        *   map = <$>
        *   res1 = (_ + 10) <$> aF
        */
      def map[B](f: A => B): F[B] = F(() => f(v()))

      /**
        * Applicative (Apply): https://github.com/purescript/purescript-prelude/blob/v3.1.0/src/Control/Apply.purs#L35-L36
        *   app: F[A => B] => F[A] => F[B] => F[C]
        *   app = <*>
        *   res1 = (+) <$> aF <*> bF
        *   res2 = (a b c => a + b + c) <$> aF <*> bF <*> cF
        */
      def app[B](fF: F[A => B]): F[B] = F(() => fF.v()(v()))

      /**
        * Monad (Bind): https://github.com/purescript/purescript-prelude/blob/v3.1.0/src/Control/Bind.purs#L48-L49
        *   flatMap: F[A] => (A => F[B]) => F[B]
        *   flatMap = bind = >>=
        *   res1 = aF >>= (a => pure(a + "b"))
        */
      def flatMap[B](f: A => F[B]): F[B] = f(v())
    }

    // Pure values with effects (printing to console)

    val aF: F[String] = F(() =>
      println(s"Get value a")
      "a"
    )

    val bF: F[String] = F(() =>
      println(s"Get value b")
      "b"
    )

    // Expressions assigned to a variable

    /**
      * `for` comprehension syntax can be used because F type defines `map` and `flatMap` functions.
      * https://www.baeldung.com/scala/for-comprehension#for-comprehension
      */
    val abF1: F[String] =
      for {
        a <- aF
        b <- bF
      } yield a + b

    /**
      * Composing operations not depending on the results of previous operations means
      *  we can use Applicative functor without Monad.
      */
    val abF2 = aF.app(bF.map(b => a => a + b))

    println(s"Result value `abF` twice = ${abF1.v() + abF2.v()}   // effects executed twice when used")
  }
}
