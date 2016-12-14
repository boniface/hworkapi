package controllers.organisations

import domain.organisations.OrganisationContact
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.organisations.OrganisationContactService

import scala.concurrent.ExecutionContext.Implicits.global                                                                                                                                       //Xolela Masebeni(213160447) xmasebeni1@gmail.com

/**
  * Created by Isiphile on 2016/12/09.
  */
class OrganisationContactController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[OrganisationContact](input).get
      val response = for {
        results <- OrganisationContactService.apply.createOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getOrganisationContact(organisationContactId: String) = Action.async {
    request =>
      val response = for {
        results <- OrganisationContactService.apply.getOrganisationContact(organisationContactId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
