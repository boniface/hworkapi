package services.Training.institutions.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.training.institutions.TrainingInstitution
import repositories.training.institutions.TrainingInstitutionRepository
import services.Service
import services.Training.institutions.TrainingInstitutionService

import scala.concurrent.Future

/**
  * Created by Yusiry on 11/24/2016.
  */
class TrainingInstitutionServiceImpl extends TrainingInstitutionService with Service{
  override def createOrUpdate(trainingInstitution: TrainingInstitution): Future[ResultSet] = {
    TrainingInstitutionRepository.save(trainingInstitution)
  }

  override def getTrainingInstitutionById(organisationId: String, trainingInstitutionId: String): Future[Option[TrainingInstitution]] = {
    TrainingInstitutionRepository.findById(organisationId, trainingInstitutionId)
  }

  override def getTrainingInstitutionsByOrganisationId(organisationId: String): Future[Seq[TrainingInstitution]] = {
    TrainingInstitutionRepository.getTrainingInstitution(organisationId)
  }

  override def getAllTrainingInstitutions(): Future[Seq[TrainingInstitution]] = {
    TrainingInstitutionRepository.findAll
  }
}
