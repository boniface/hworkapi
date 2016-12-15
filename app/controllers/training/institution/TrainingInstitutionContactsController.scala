package controllers.training.institution

import domain.training.institutions.TrainingInstitutionContacts
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.Training.institutions.TrainingInstitutionContactsService

/**
  * Created by Yusiry on 12/15/2016.
  */
class TrainingInstitutionContactsController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[TrainingInstitutionContacts](input).get
      val response =
        for {
          results <- TrainingInstitutionContactsService.apply.createOrUpdate(entity)
        } yield results
      response.map(ans => Ok(Json.toJson(entity))).recover{
        case e: Exception => InternalServerError
      }
  }

  def getTrainingContacts(organisationId: String) = Action.async{
    requests =>
      val response = for {
        results <- TrainingInstitutionContactsService.apply.getTrainingInstitutionContacts(organisationId)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
          .recover { case e: Exception => InternalServerError }
  }
}
