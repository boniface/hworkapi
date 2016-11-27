package services.Training.institutions.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.training.institutions.TrainingInstitutionAddress
import repositories.training.institutions.TrainingInstitutionAddressRepository
import services.Service
import services.Training.institutions.TrainingInstitutionAddressService

import scala.concurrent.Future

/**
  * Created by Yusiry on 11/24/2016.
  */
class TrainingInstitutionAddressServiceImpl extends TrainingInstitutionAddressService with Service{

  override def createOrUpdate(trainingInstitutionAddress: TrainingInstitutionAddress): Future[ResultSet] = {
    TrainingInstitutionAddressRepository.save(trainingInstitutionAddress)
  }

  override def getTrainingInstitutionById(id: String, trainingLocationId: String, organisationId: String): Future[Option[TrainingInstitutionAddress]] = {
    TrainingInstitutionAddressRepository.findById(organisationId, id, trainingLocationId)
  }

  override def getAllTrainingInstitutionsByOrganisationId(organisationId: String): Future[Seq[TrainingInstitutionAddress]] = {
    TrainingInstitutionAddressRepository.getTrainingInstitutionAddress(organisationId)
  }

  override def getAllTrainingInstitutions(): Future[Seq[TrainingInstitutionAddress]] = {
    TrainingInstitutionAddressRepository.findAll
  }
}
