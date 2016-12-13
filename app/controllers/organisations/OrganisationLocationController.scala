package controllers.organisations

import domain.organisations.OrganisationLocation
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.organisations.OrganisationLocationService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Isiphile on 2016/12/09.
  */
class OrganisationLocationController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[OrganisationLocation](input).get
      val response = for {
        results <- OrganisationLocationService.apply.createOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getOrganisationLocation(organisationLocationId: String) = Action.async {
    request =>
      val response = for {
        results <- OrganisationLocationService.apply.getOrganisationLocation(organisationLocationId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
