package services.Training.institutions

import com.websudos.phantom.dsl.ResultSet
import domain.training.institutions.TrainingInstitutionContacts
import services.Training.institutions.Impl.TrainingInstitutionContactsServiceImpl

import scala.concurrent.Future

/**
  * Created by Yusiry on 11/24/2016.
  */
trait TrainingInstitutionContactsService {
  def createOrUpdate(trainingInstitutionContacts: TrainingInstitutionContacts): Future[ResultSet]

  def getTrainingInstitutionContactsById(id: String, trainingInstitutionContactId: String): Future[Seq[TrainingInstitutionContacts]]

  def getTrainingInstitutionContacts(companyId: String): Future[Seq[TrainingInstitutionContacts]]

  def getAllTrainingInstitutionContacts(): Future[Seq[TrainingInstitutionContacts]]
}

object TrainingInstitutionContactsService{
  def apply: TrainingInstitutionContactsService = new TrainingInstitutionContactsServiceImpl();
}
