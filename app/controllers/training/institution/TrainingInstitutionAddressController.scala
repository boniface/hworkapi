package controllers.training.institution

import domain.training.institutions.TrainingInstitutionAddress
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.Training.institutions.TrainingInstitutionAddressService

/**
  * Created by Yusiry on 12/15/2016.
  */
class TrainingInstitutionAddressController extends Controller {

  def createOrUpdate = Action.async(parse.json){
    request =>
      val input = request.body
      val entity = Json.fromJson[TrainingInstitutionAddress](input).get
      val response =
        for {
          results <- TrainingInstitutionAddressService.apply.createOrUpdate(entity)
        } yield results
      response.map(ans => Ok(Json.toJson(entity))).recover {
        case e: Exception => InternalServerError
      }
  }

  def getTrainingAddress(organisationId: String) = Action.async {
    request =>
      val response = for {
        results <- TrainingInstitutionAddressService.apply.getAllTrainingInstitutionsByOrganisationId(organisationId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }

}
