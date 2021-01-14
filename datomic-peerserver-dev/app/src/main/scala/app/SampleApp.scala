package app

import app.dsl.sample._
import app.schema._
import molecule.datomic.api._
import molecule.datomic.client.facade.Datomic_PeerServer

// See project readme for more info...

object SampleApp extends App {

  // Connect to Peer Server
  val peerServer = Datomic_PeerServer("k", "s", "localhost:8998")

  // Transact schema and get database connection
  implicit val conn = peerServer.transactSchema(SampleSchema, "sampledb")

  // Once the schema is transacted, you can simply connect to the existing database.
  // Then comment the line above and un-comment the line below. This will also confirm that
  // the database is persisted.

  // Get database connection
  //  implicit val conn = peerServer.connect("sampledb")

  // Transact
  Person.name("John").age(27).save.eid

  // Query
  assert(Person.name.age.get.head == ("John", 27))

  println("SUCCESS!")
}
