package app

import molecule.datomic.peer.facade.Datomic_Peer

// Script to create a persisting database (using a Peer) that we can then
// connect to with Peer Server.
// See more info in the project readme.

object CreateSampleDb extends App {

  // 1. Start transactor first as described in the project readme
  // 2. Run this app (can take about a minute to complete...)

  Datomic_Peer.createDatabase("localhost:4334/sampledb", "dev")


  // Un-comment and run additional commands if needed

  //  Datomic_Peer.deleteDatabase("localhost:4334/sampledb", "dev")
  //  Datomic_Peer.getDatabaseNames("dev").sorted foreach println
}
