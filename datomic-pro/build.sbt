import sbt.Keys._

lazy val demo = project.in(file("."))
  .aggregate(app)
  .settings(name := "molecule-sample-datomic-pro")

lazy val app = project.in(file("app"))
  .enablePlugins(MoleculePlugin)
  .settings(
    scalaVersion := "2.13.4",
    resolvers ++= Seq(
      Resolver.sonatypeRepo("releases"),
      "clojars" at "https://clojars.org/repo",
      "my.datomic.com" at "https://my.datomic.com/repo",
    ),
    /*
      Downloading Datomic Starter/Pro requires authentication of your license:
      Create a ~/.sbt/.credentials file with the following content:

        realm=Datomic Maven Repo
        host=my.datomic.com
        id=my.datomic.com
        user=<your-username>
        pass=<your-password>

      Then let sbt provide your secret credentials:
    */
    credentials += Credentials(Path.userHome / ".sbt" / ".credentials"),

    libraryDependencies ++= Seq(
      "org.scalamolecule" %% "molecule" % "0.23.2",
      "com.datomic" % "datomic-pro" % "1.0.6222",
      "org.specs2" %% "specs2-core" % "4.10.5"
    ),

    // Generate Molecule boilerplate code with `sbt clean compile -Dmolecule=true`
    moleculePluginActive := sys.props.get("molecule") == Some("true"),
    moleculeDataModelPaths := Seq("app"), // path to domain model directory

    // Let IDE detect created jars in unmanaged lib directory
    exportJars := true
  )
