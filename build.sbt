organization := "com.url"

version := "0.1"

scalaVersion := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers ++= Seq(
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots"),
    "Typesafe repository snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
    "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/",
    "Sonatype repo" at "https://oss.sonatype.org/content/groups/scala-tools/",
    "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases",
    "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
    "Sonatype staging" at "http://oss.sonatype.org/content/repositories/staging",
    "Spray Repository" at "http://repo.spray.io",
    "Java.net Maven2 Repository" at "http://download.java.net/maven/2/",
    "Twitter Repository" at "http://maven.twttr.com",
    Resolver.bintrayRepo("websudos", "oss-releases")
)

libraryDependencies ++= {
    val akkaV = "2.3.9"
    val sprayV = "1.3.3"
    val Json4sVersion = "3.2.11"
    val PhantomVersion = "1.12.2"

    Seq(
        "io.spray" %% "spray-can" % sprayV,
        "io.spray" %% "spray-routing" % sprayV,
        "io.spray" %% "spray-testkit" % sprayV % "test",
        "io.spray" %% "spray-json" % "1.3.1",
        "io.spray" % "spray-routing-shapeless2_2.11" % "1.3.3",
        "com.websudos" %% "phantom-dsl" % PhantomVersion,
        "com.websudos" %% "phantom-testkit" % PhantomVersion % "test, provided",
        "org.json4s" %% "json4s-native" % Json4sVersion,
        "org.json4s" %% "json4s-ext" % Json4sVersion,
        "com.typesafe.akka" %% "akka-actor" % akkaV,
        "com.typesafe.akka" %% "akka-testkit" % akkaV % "test",
        "org.specs2" %% "specs2-core" % "2.3.11" % "test",
        "org.scalaj" %% "scalaj-http" % "2.2.0",
        "org.scalatest" %% "scalatest" % "2.2.4" % "test",
        "com.typesafe" % "config" % "1.3.0"
    )
}
