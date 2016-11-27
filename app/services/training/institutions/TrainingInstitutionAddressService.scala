package services.Training.institutions

import com.websudos.phantom.dsl.ResultSet
import domain.training.institutions.TrainingInstitutionAddress
import services.Training.institutions.Impl.TrainingInstitutionAddressServiceImpl

import scala.concurrent.Future

/**
  * Created by Yusiry on 11/24/2016.
  */
trait TrainingInstitutionAddressService {
  def createOrUpdate(trainingInstitutionAddress: TrainingInstitutionAddress) : Future[ResultSet]

 def getTrainingInstitutionById(id: String, trainingLocationId: String, organisationId: String): Future[Option[TrainingInstitutionAddress]]

  def getAllTrainingInstitutionsByOrganisationId(organisationId: String) : Future[Seq[TrainingInstitutionAddress]]

  def getAllTrainingInstitutions() : Future[Seq[TrainingInstitutionAddress]]
}

object TrainingInstitutionAddressService{
  def apply: TrainingInstitutionAddressService = new TrainingInstitutionAddressServiceImpl();

}