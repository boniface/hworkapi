package controllers.organisations

import domain.organisations.OrganisationDepartment
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.organisations.OrganisationDepartmentService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Isiphile on 2016/12/09.
  */
class OrganisationDepartmentController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[OrganisationDepartment](input).get
      val response = for {
        results <- OrganisationDepartmentService.apply.createOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getOrganisationDepartment(organisationDepartmentId: String) = Action.async {
    request =>
      val response = for {
        results <- OrganisationDepartmentService.apply.getOrganisationDepartment(organisationDepartmentId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
