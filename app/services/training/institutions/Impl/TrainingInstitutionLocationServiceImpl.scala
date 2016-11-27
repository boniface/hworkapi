package services.Training.institutions.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.training.institutions.TrainingInstitutionLocation
import repositories.training.institutions.TrainingInstitutionLocationRepository
import services.Service
import services.Training.institutions.TrainingInstitutionLocationService

import scala.concurrent.Future

/**
  * Created by Yusiry on 11/24/2016.
  */
class TrainingInstitutionLocationServiceImpl extends TrainingInstitutionLocationService with Service{
  override def createOrUpdate(trainingInstitutionLocation: TrainingInstitutionLocation): Future[ResultSet] = {
    TrainingInstitutionLocationRepository.save(trainingInstitutionLocation)
  }

  override def getTrainingInstitutionLocationById(id: String, organisationId: String, locationTypeId: String): Future[Option[TrainingInstitutionLocation]] = {
    TrainingInstitutionLocationRepository.findById(organisationId, id, locationTypeId)
  }

  override def getTrainingInstitutionLocationByOrganisationId(organisationId: String): Future[Seq[TrainingInstitutionLocation]] = {
    TrainingInstitutionLocationRepository.getTrainingInstitutionLocation(organisationId)
  }

  override def getAllTrainingInstitutionLocations(): Future[Seq[TrainingInstitutionLocation]] = {
    TrainingInstitutionLocationRepository.findAll
  }
}
