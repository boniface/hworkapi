package services.Training.institutions.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.training.institutions.TrainingInstitutionContacts
import repositories.training.institutions.{TrainingInstitutionContactsRepository, TrainingInstitutionContactsRepository$$}
import services.Service
import services.Training.institutions.TrainingInstitutionContactsService

import scala.concurrent.Future

/**
  * Created by Yusiry on 11/24/2016.
  */
class TrainingInstitutionContactsServiceImpl extends TrainingInstitutionContactsService with Service {
  override def createOrUpdate(trainingInstitutionContacts: TrainingInstitutionContacts): Future[ResultSet] = {
    TrainingInstitutionContactsRepository.save(trainingInstitutionContacts)
  }

  override def getTrainingInstitutionContactsById(id: String, trainingInstitutionContactId: String): Future[Option[TrainingInstitutionContacts]] = {
    TrainingInstitutionContactsRepository.findById(id, trainingInstitutionContactId)
  }

  override def getTrainingInstitutionContacts(organisationId: String): Future[Seq[TrainingInstitutionContacts]] = {
    TrainingInstitutionContactsRepository.getTraningInstitutionContacts(organisationId)
  }

  override def getAllTrainingInstitutionContacts(): Future[Seq[TrainingInstitutionContacts]] = {
    TrainingInstitutionContactsRepository.findAll
  }
}
