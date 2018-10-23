package models

import play.api.data._
import play.api.data.Forms._

import scala.collection.mutable.ArrayBuffer

case class Person(name: String, age: Int, jobTitle: String)

object Person {

  val createPersonForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "age" -> number,
      "job title" -> nonEmptyText
    )(Person.apply)(Person.unapply)
  )

  val people = ArrayBuffer(
    Person("Gary Monk", 33, "Accountant"),
    Person("Phil Johnson", 41, "Grafter"),
    Person("Paul Johnson", 55, "DJ")
  )

}
