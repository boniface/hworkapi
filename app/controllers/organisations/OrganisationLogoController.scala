package controllers.organisations

import domain.organisations.OrganisationLogo
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.organisations.OrganisationLogoService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Isiphile on 2016/12/09.
  */
class OrganisationLogoController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[OrganisationLogo](input).get
      val response = for {
        results <- OrganisationLogoService.apply.createOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getOrganisationLogo(organisationLogoId: String) = Action.async {
    request =>
      val response = for {
        results <- OrganisationLogoService.apply.getOrganisationLogo(organisationLogoId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
