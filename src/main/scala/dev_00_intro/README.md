# Intro

Scala is a language which supports Object-Oriented Programming (OOP) and Functional Programming (FP) style.

The goal of these examples is to provide basic insights how to use these two models of programming together with the introduction of useful patterns when using libraries like [cats](https://typelevel.org/cats/) or [scalaz](https://scalaz.github.io/).

## Functions

Source: [_00_functions.scala](_00_functions.scala)

Most modern programming languages are based on [Lambda Calculus](http://dev.stephendiehl.com/fun/lambda_calculus.html) ([in Scala](https://madusudanan.com/blog/scala-tutorials-part-19-lambda-calculus/)) which is the basis for the notion of a **function**.

So the starting point of this intro is to introduce how to work with functions in Scala and what kind of _flavors_ functions can be. Scala supports many FP concepts but this doesn't mean they are always idiomatic especially with more complex types like [higer-kinded types (HKT)](https://typelevel.org/blog/2016/08/21/hkts-moving-forward.html). This [intro to tagless-final and HKT](https://gist.github.com/tgrospic/661f6504c4940ac6b15e13c06abbdffe) compare how types are represented from _dynamically_ typed to _dependently_ typed languages.

## Pure functions (values) - _infamous_ `F` type

Source: [_10_F_context.scala](_10_F_context.scala)

Functional programming is usually directly connected with the notion of **pure functions** or **referential transparency**.
For functions to have this property means they need to return the same output giving the same input.

This is not a big deal if we are not dealing with side effects. For example, if we construct some expression in a language and store it in a variable, we know that using the value in a variable has the same meaning as the expression from which is constructed, so using the variable multiple times or running the whole expression multiple times will not change the meaning of the program. Maybe performance will be different, but the result will be the same.

The problem arises when the expression has **side effects** along with the result. And side effects are in many cases the main reason why we are running the program in the first place. So just to say that _referentially transparent_ or _pure_ functions don't have side effects does not offer the solution. 

Instead of using only the value of type `A` we also have wrapper of type `F[_]` so when applied gives `F[A]`.

`F[_]` represents _type constructor_ or _higher-kinded type_. It's like a function but on the type level `type_def F(a: A): F[A]`, from input type returns a type. With argument `A` we can imagine compiler calling it `F(A)` to get the type `F[A]`.
`F` and `A` are generic parameters and can represent any concrete type. `F` of course must be **type constructor** like `Option[_]`, `List[_]` or `Task[_]`.

The example in this section shows simplified view on how to think about `F[_]` type from FP perspective. Represents expressions with _referentially transparent_ properties. This is done just by wrapping the value and effects inside anonymous function and in that way _delay_ the execution on the place where is used and not where is assigned to a variable. Or more correctly where is named, it's immutable reference which cannot change.

## `F` type with Monad effect

Source: [_20_F_Monad.scala](_20_F_Monad.scala)

In the previous section, creating expressions with `F` type meant to just call the wrapped function. So now the question is how to combine these operations into more complex expressions.

For that purpose important enhancement to `F` type is **Monad** (**Applicative** functor and **Functor**) effects which gives the ability to put operations in a sequence. Adding effects just means to create functions operating on `F` type.

The great importance of these effects is shown by the syntactic support they have in Scala, but also in other languages such as Haskell, OCaml, F#, Purescript, etc.

In Scala `for {...} yield ...` comprehension syntax is enabled by implementing `map` and `flatMap` functions where is _hidden_ access to wrapped function created in the example from previous section. Further examples will show how in the same spirit this works in _cats_ library with `F` type as completely abstract type.
