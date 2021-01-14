package app.dataModel

import molecule.core.data.model._


@InOut(0, 2)
object SampleDataModel {

  trait Person {
    val name = oneString
    val age  = oneInt
  }
}