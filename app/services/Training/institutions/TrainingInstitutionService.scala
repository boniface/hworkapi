package services.Training.institutions

import com.websudos.phantom.dsl.ResultSet
import domain.training.institutions.TrainingInstitution
import services.Training.institutions.Impl.TrainingInstructorServiceImpl

import scala.concurrent.Future

/**
  * Created by Yusiry on 11/24/2016.
  */
trait TrainingInstitutionService {

  def createOrUpdate(trainingInstitution: TrainingInstitution) : Future[ResultSet]

  def getTrainingInstitutionById(organisationId: String, trainingInstitutionId: String) : Future[Option[TrainingInstitution]]

  def getTrainingInstitutionsByOrganisationId(organisationId: String ) : Future[Seq[TrainingInstitution]]

  def getAllTrainingInstitutions() : Future[Seq[TrainingInstitution]]

}

object TrainingInstitutionService{
  def apply: TrainingInstructorService = new TrainingInstructorServiceImpl()
}
