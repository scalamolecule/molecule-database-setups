## Molecule sample project using Datomic dev-local client database

Minimal project setup to test a [Cloud setup locally](https://docs.datomic.com/cloud/dev-local.html) without connecting to a server.


### 1. Create local client

    implicit val conn = Datomic_DevLocal("datomic-samples").recreateDbFrom(SampleSchema, "sampledb")

Or, when the database has been created, only connect to it:

    implicit val conn = Datomic_DevLocal("datomic-samples").connect("sampledb")


### 2. Make molecules

Having an implicit connection in scope, we can start transacting and querying `sampledb` with molecules:

    // Transact
    Person.name("John").age(27).save.eid
    
    // Query
    assert(Person.name.age.get.head == ("John", 27))


Add/change definitions in the SampleDataModel and run `sbt clean compile -Dmolecule=true` in your project root to have Molecule re-generate boilerplate code. Then you can try out using your new attributes in new molecules in `SampleApp`.

For more info, visit the [Molecule website](http://scalamolecule.org)