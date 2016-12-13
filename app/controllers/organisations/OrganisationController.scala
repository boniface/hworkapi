package controllers.organisations

import domain.organisations.Organisation
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.organisations.OrganisationService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Isiphile on 2016/12/09.
  */
class OrganisationController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[Organisation](input).get
      val response = for {
        results <- OrganisationService.apply.createOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getOrganisation(organisationId: String) = Action.async {
    request =>
      val response = for {
        results <- OrganisationService.apply.getOrganisation(organisationId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
