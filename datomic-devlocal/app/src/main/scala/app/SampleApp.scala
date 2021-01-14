package app

import app.dsl.sample._
import app.schema._
import molecule.datomic.api._
import molecule.datomic.client.facade.Datomic_DevLocal

// See project readme for more info...

object SampleApp extends App {

  // Connect, recreate database and get database connection
  implicit val conn = Datomic_DevLocal("datomic-samples").recreateDbFrom(SampleSchema, "sampledb")

  // Once the schema is transacted, you can simply connect to the existing database.
  // Then comment the line above and un-comment the line below. This will also confirm that
  // the database is persisted.

  // Get database connection
  //  implicit val conn = Datomic_DevLocal("datomic-samples").connect("sampledb")

  // Transact
  Person.name("John").age(27).save.eid

  // Query
  assert(Person.name.age.get.head == ("John", 27))

  println("SUCCESS!")
}
