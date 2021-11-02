val scala3Version = "3.1.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "RChain dev playground",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    scalafmtOnCompile := true,
    
    libraryDependencies += "org.typelevel" %% "cats-core" % "2.6.1",
    libraryDependencies += "org.typelevel" %% "cats-effect" % "2.5.1",
    
    libraryDependencies += "io.monix" %% "monix" % "3.4.0",

    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.10",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test",

    scalacOptions ++= Seq(
      // To silence warning when defining Scala 2 extensions - implicit def ...
      "-language:implicitConversions"
    )
  )
