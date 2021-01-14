package app

import app.dsl.sample._
import app.schema._
import molecule.datomic.api._
import molecule.datomic.client.facade.Datomic_PeerServer

// See project readme for more info...

object SampleApp extends App {

  // Connect and transact schema
  // Since the in-memory db is new each time, we need to transact the schema on each run
  implicit val conn = Datomic_PeerServer("k", "s", "localhost:8998")
    .transactSchema(SampleSchema, "sampledb")

  // Transact
  Person.name("John").age(27).save.eid

  // Query
  assert(Person.name.age.get.head == ("John", 27))

  println("SUCCESS!")
}
