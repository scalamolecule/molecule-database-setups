import sbt.Keys._


lazy val demo = project.in(file("."))
  .aggregate(app)
  .settings(name := "molecule-sample-datomic-free")

lazy val app = project.in(file("app"))
  .enablePlugins(MoleculePlugin)
  .settings(
    scalaVersion := "2.13.4",
    resolvers ++= Seq(
      Resolver.sonatypeRepo("releases"),
      "clojars" at "https://clojars.org/repo",
    ),

    libraryDependencies ++= Seq(
      "org.scalamolecule" %% "molecule" % "0.23.2",
      "com.datomic" % "datomic-free" % "0.9.5697",
      "org.specs2" %% "specs2-core" % "4.10.5"
    ),

    // Generate Molecule boilerplate code with `sbt clean compile -Dmolecule=true`
    moleculePluginActive := sys.props.get("molecule") == Some("true"),
    moleculeDataModelPaths := Seq("app"), // path to domain model directory

    // Let IDE detect created jars in unmanaged lib directory
    exportJars := true
  )
