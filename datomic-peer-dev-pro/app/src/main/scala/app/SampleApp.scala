package app

import app.dsl.sample._
import app.schema._
import molecule.datomic.api._
import molecule.datomic.peer.facade.Datomic_Peer

// See project readme for more info...

object SampleApp extends App {

  // Connect, recreate database and get database connection with a "dev" protocol
  implicit val conn = Datomic_Peer.recreateDbFrom(SampleSchema, "localhost:4334/sampledb", "dev")

  // Once the schema is transacted, you can simply connect to the existing database.
  // Then comment the line above and un-comment the line below. This will also confirm that
  // the database is persisted.

  // Get database connection
//    implicit val conn = Datomic_Peer.connect("localhost:4334/sampledb", "dev")

  // Transact
  Person.name("John").age(27).save.eid

  // Query
  assert(Person.name.age.get.head == ("John", 27))

  println("SUCCESS!")
}
