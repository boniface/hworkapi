import com.typesafe.sbt.packager.archetypes.ServerLoader

name := """hworkapi"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala, DebianPlugin, JavaServerAppPackaging)
  .disablePlugins(PlayLogback)

scalaVersion := "2.11.8"

val PhantomVersion = "1.28.14"
val ScalaZVersion = "7.2.6"

maintainer := "Douglas Namafente <douglas@hashcode.zm>"
packageSummary in Linux := "Hash Work REST API"
packageDescription := "Hash Work API Backend "
serverLoading in Debian := ServerLoader.SystemV

bashScriptExtraDefines ++= Seq(
  """addJava "-Xms1024m"""",
  """addJava "-Xmx2048m""""
)

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "com.websudos" % "phantom-dsl_2.11" % PhantomVersion,
  "com.websudos" % "phantom-reactivestreams_2.11" % PhantomVersion,
  "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.4.1",
  "org.apache.logging.log4j" % "log4j-api" % "2.4.1",
  "org.apache.logging.log4j" % "log4j-core" % "2.4.1",
  "com.chuusai" %% "shapeless" % "2.2.5"
)

// https://mvnrepository.com/artifact/io.getclump/clump_2.11
libraryDependencies += "io.getclump" % "clump_2.11" % "0.0.11"
libraryDependencies += "io.monix" %% "monix" % "2.0.0"
libraryDependencies += "me.lessis" %% "courier" % "0.1.3"
libraryDependencies += "com.jason-goodwin" % "authentikat-jwt_2.11" % "0.4.3"
libraryDependencies += "me.lessis" % "base64_2.11" % "0.2.0"
libraryDependencies += "junit" % "junit" % "4.12"
libraryDependencies += "com.github.t3hnar" % "scala-bcrypt_2.11" % "2.5"
libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.4"
libraryDependencies += "org.scalaz" %% "scalaz-core" % ScalaZVersion
// https://mvnrepository.com/artifact/org.scalatest/scalatest_2.11
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "3.0.0"
// https://mvnrepository.com/artifact/com.google.guava/guava
libraryDependencies += "com.google.guava" % "guava" % "19.0"

// redis-server cache
libraryDependencies += "com.github.karelcemus" %% "play-redis" % "1.2.0"
libraryDependencies += "com.github.romix.akka" %% "akka-kryo-serialization" % "0.4.1"
// https://mvnrepository.com/artifact/com.esotericsoftware.kryo/kryo
libraryDependencies += "com.esotericsoftware.kryo" % "kryo" % "2.24.0"
libraryDependencies += "org.typelevel" %% "cats" % "0.7.2"
// https://mvnrepository.com/artifact/com.github.nscala-time/nscala-time_2.11
libraryDependencies += "com.github.nscala-time" % "nscala-time_2.11" % "2.12.0"

routesGenerator := InjectedRoutesGenerator
libraryDependencies += filters

resolvers ++= Seq(
  "Typesafe repository snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
  "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/",
  "softprops-maven" at "http://dl.bintray.com/content/softprops/maven",
  "Brando Repository" at "http://chrisdinn.github.io/releases/",
  "Sonatype repo" at "https://oss.sonatype.org/content/groups/scala-tools/",
  "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases",
  "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype staging" at "http://oss.sonatype.org/content/repositories/staging",
  "Java.net Maven2 Repository" at "http://download.java.net/maven/2/",
  "Twitter Repository" at "http://maven.twttr.com",
  "Websudos releases" at "https://dl.bintray.com/websudos/oss-releases/",
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)


