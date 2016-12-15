package controllers.training.institution

import domain.training.institutions.TrainingInstitution
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.Training.institutions.TrainingInstitutionService

/**
  * Created by Yusiry on 12/15/2016.
  */
class TrainingInstitutionController extends Controller {

  def createOrUpdate = Action.async(parse.json){
    request =>
      val input = request.body
      val entity = Json.fromJson[TrainingInstitution](input).get
      val response =
        for {
          results <- TrainingInstitutionService.apply.createOrUpdate(entity)
        } yield results
      response.map(ans => Ok(Json.toJson(entity))).recover {
        case e: Exception => InternalServerError
      }
  }

  def getTrainingInstitution(organisationId: String) = Action.async {
    request =>
      val response = for {
        results <- TrainingInstitutionService.apply.getTrainingInstitutionsByOrganisationId(organisationId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
