package controllers.organisations

import domain.organisations.OrganisationAddress
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.organisations.OrganisationAddressService

/**
  * Created by Isiphile on 2016/12/09.
  */
class OrganisationAddressController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(" THE INPUT IS ", input)
      val entity = Json.fromJson[OrganisationAddress](input).get
      val response = for {
        results <- OrganisationAddressService.apply.createOrUpdate(entity)
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => InternalServerError
        }
  }

  def getOrganisationAddress(organisationAddressId: String) = Action.async {
    request =>
      val response = for {
        results <- OrganisationAddressService.apply.getOrganisationAddress(organisationAddressId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
