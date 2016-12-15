package controllers.training.institution

import domain.training.institutions.TrainingInstructor
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.Training.institutions.TrainingInstructorService

/**
  * Created by Yusiry on 12/15/2016.
  */
class TrainingInstructorController extends Controller{
  def createOrUpdate = Action.async(parse.json){
    request =>
      val input = request.body
      val entity = Json.fromJson[TrainingInstructor](input).get
      val response =
        for {
          results <- TrainingInstructorService.apply.createOrUpdate(entity)
        } yield results
      response.map(ans => Ok(Json.toJson(entity))).recover {
        case e: Exception => InternalServerError
      }
  }

  def getTrainingInstuctor(organisationId: String) = Action.async {
    request =>
      val response = for {
        results <- TrainingInstructorService.apply.getTrainingInstructorsByOrganisationId(organisationId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover { case e: Exception => InternalServerError }
  }
}
