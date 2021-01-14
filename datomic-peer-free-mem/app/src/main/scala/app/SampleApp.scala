package app

import app.dsl.sample._
import app.schema._
import molecule.datomic.api._
import molecule.datomic.peer.facade.Datomic_Peer

// See project readme for more info...

object SampleApp extends App {

  // Connect and recreate database
  // Since the in-memory db is new each time, we need to transact the schema on each run
  implicit val conn = Datomic_Peer.recreateDbFrom(SampleSchema)

  // Transact
  Person.name("John").age(27).save.eid

  // Query
  assert(Person.name.age.get.head == ("John", 27))

  println("SUCCESS!")
}
