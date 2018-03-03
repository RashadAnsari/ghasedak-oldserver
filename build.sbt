import ir.sndu.Dependencies

name := "elitem"

scalaSource in ProtocPlugin.ProtobufConfig := sourceManaged.value

lazy val commonSettings = Seq(
  organization := "ir.elitem",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.12.3",
  mainClass in Compile := Some("ir.sndu.server.Main"),
  PB.targets in Compile := {
    println((sourceManaged  in Compile).value)
    println((sourceManaged).value)
    Seq(
      scalapb.gen() -> (sourceManaged  in Compile).value
    )
  },
  {
    println(scalaSource)
    println(scalaSource in ProtocPlugin.ProtobufConfig)
    scalaSource in ProtocPlugin.ProtobufConfig := sourceManaged.value
  }

)


lazy val root = (project in file("."))
  .settings(
    commonSettings
  )
  .dependsOn(commons, core, rpc)
    .aggregate(core,persist,rpc, commons)

lazy val core = (project in file("elitem-core"))
  .settings(
    commonSettings,
    libraryDependencies ++= Dependencies.core
  ).dependsOn(model)

lazy val persist = (project in file("elitem-persist"))
  .settings(
    commonSettings,
    libraryDependencies ++= Dependencies.persist
  ).dependsOn(model)

lazy val rpc = (project in file("elitem-rpc"))
  .settings(
    commonSettings,
    libraryDependencies ++= Dependencies.rpc
  ).dependsOn(core, persist)

lazy val model = (project in file("elitem-model"))
  .settings(
    commonSettings,
    libraryDependencies ++= Dependencies.model
  )

lazy val commons = (project in file("elitem-commons"))
  .settings(
    commonSettings,
    libraryDependencies ++= Dependencies.commons
  )

lazy val test = (project in file("elitem-test"))
  .settings(
    commonSettings,
    libraryDependencies ++= Dependencies.test
  )
  .dependsOn(commons, core, rpc, persist)
