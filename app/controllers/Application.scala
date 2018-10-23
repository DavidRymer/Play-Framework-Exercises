package controllers

import javax.inject.Inject

import models.Person
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._

class Application @Inject()(val messagesApi: MessagesApi, environment: play.api.Environment) extends Controller with I18nSupport {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def redirect = Action { implicit request: Request[AnyContent] =>
    Redirect("https://www.bbc.co.uk")
  }

  def staticHelloWorld = Action { implicit request: Request[AnyContent] =>
    Ok("Static Hello World")
  }

  def dynamicHelloWorld(name: Any) = Action { implicit request: Request[AnyContent] =>
    Ok(s"Dynamic Hello $name")
  }

  def listPeople = Action { implicit request =>

    Ok(views.html.listPeople(Person.people, Person.createPersonForm))
  }

  def createPerson = Action { implicit request =>

    val formValidationResult = Person.createPersonForm.bindFromRequest

    formValidationResult.fold({ formWithErrors =>
      BadRequest(views.html.listPeople(Person.people, formWithErrors))
    }, { person =>

      Person.people.append(person)
      Redirect(routes.Application.listPeople())
    })
  }




}