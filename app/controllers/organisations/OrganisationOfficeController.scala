package controllers.organisations

import domain.organisations.OrganisationOffice
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.organisations.OrganisationOfficeService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Isiphile on 2016/12/09.
  */
class OrganisationOfficeController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[OrganisationOffice](input).get
      val response = for {
        results <- OrganisationOfficeService.apply.createOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getOrganisationOffice(organisationOfficeId: String) = Action.async {
    request =>
      val response = for {
        results <- OrganisationOfficeService.apply.getOrganisationOffice(organisationOfficeId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
