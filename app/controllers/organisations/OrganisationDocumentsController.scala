package controllers.organisations

import domain.organisations.OrganisationDocuments
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.organisations.OrganisationDocumentsService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Isiphile on 2016/12/09.
  */
class OrganisationDocumentsController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[OrganisationDocuments](input).get
      val response = for {
        results <- OrganisationDocumentsService.apply.createOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getOrganisationDocuments(organisationDocumentsId: String) = Action.async {
    request =>
      val response = for {
        results <- OrganisationDocumentsService.apply.getOrganisationDocuments(organisationDocumentsId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
