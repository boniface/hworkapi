package controllers.training.institution

import domain.training.institutions.TrainingInstitutionLocation
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.Training.institutions.TrainingInstitutionLocationService

/**
  * Created by Yusiry on 12/15/2016.
  */
class
TrainingInstitutionLocationController extends Controller {

  def createOrUpdate = Action.async(parse.json){
    request =>
      val input = request.body
      val entity = Json.fromJson[TrainingInstitutionLocation](input).get
      val response =
        for {
          results <- TrainingInstitutionLocationService.apply.createOrUpdate(entity)
        } yield results
      response.map(ans => Ok(Json.toJson(entity))).recover {
        case e: Exception => InternalServerError
      }
  }

  def getTrainingLocation(organisationId: String) = Action.async(parse.json){
    request =>
      val response = for {
        results <- TrainingInstitutionLocationService.apply.getTrainingInstitutionLocationByOrganisationId(organisationId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {case e: Exception => InternalServerError }
  }

}
