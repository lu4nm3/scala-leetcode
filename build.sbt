val Http4sVersion = "0.20.11"

lazy val scalaleetcode = (project in file("."))
  .settings(
    name := "scalaleetcode",
    scalacOptions ++= Seq(
      "-feature", // Emit warning and location for usages of features that should be imported explicitly.
      "-Ypartial-unification" // Enable partial unification in type constructor inference
    ),
    scalaVersion := "2.12.10",
    version := "0.1.0"
  )
  .settings(libraryDependencies ++= Seq(
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
    "org.typelevel" %% "cats-core" % "1.1.0" withSources() withJavadoc(),
    "com.github.pathikrit" %% "better-files" % "3.6.0",
    "io.circe" %% "circe-generic" % "0.11.1",
    "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
    "org.http4s" %% "http4s-blaze-client" % Http4sVersion,
    "org.http4s" %% "http4s-circe" % Http4sVersion,
    "org.http4s" %% "http4s-dsl" % Http4sVersion,
    "io.monix" %% "monix" % "3.0.0-RC2",
    "org.scalactic" %% "scalactic" % "3.0.4",
    "org.scalatest" %% "scalatest" % "3.0.4" % "test",
    "org.typelevel" %% "cats-core" % "1.1.0" withSources() withJavadoc(),
    //    "org.typelevel" %% "cats-effect" % "1.0.0-RC2" withSources() withJavadoc(),
    "org.typelevel" %% "cats-mtl-core" % "0.2.1" withSources() withJavadoc()
  ))
  .settings(resolvers += Resolver.sonatypeRepo("snapshots"))
//  .aggregate(utils, singleNumber)

lazy val docs = project // new documentation project
//  .in(file("myproject-rawdocs")) // important: it must not be rawdocs/
  .dependsOn(scalaleetcode)
  .enablePlugins(MdocPlugin)
  .settings(
    mdocIn := new java.io.File("rawdocs"),
    mdocOut := new java.io.File("gendocs"),
    mdocVariables := Map(
      "VERSION" -> version.value
    )
  )

//lazy val utils = project
//lazy val singleNumber = project.dependsOn(utils)//.aggregate(utils)