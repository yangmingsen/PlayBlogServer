name := """PlayBlogServer"""
organization := "top.yms"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.1"

libraryDependencies += guice

libraryDependencies ++= Seq(
  "com.typesafe.play"       %% "play-json"                        % "2.8.0",
  "org.scalikejdbc" %% "scalikejdbc"                  % "3.4.0",
  "org.scalikejdbc" %% "scalikejdbc-config"           % "3.4.0",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.8.0-scalikejdbc-3.4",
  "mysql"                   %  "mysql-connector-java"             % "5.1.38",
  "org.scalatestplus.play"  %% "scalatestplus-play"               % "5.0.0"                     % Test
)


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "top.yms.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "top.yms.binders._"
