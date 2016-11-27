package services.Training.institutions

import com.websudos.phantom.dsl.ResultSet
import domain.training.institutions.TrainingInstitutionLocation
import services.Training.institutions.Impl.TrainingInstitutionLocationServiceImpl

import scala.concurrent.Future

/**
  * Created by Yusiry on 11/24/2016.
  */
trait TrainingInstitutionLocationService {
    def createOrUpdate(trainingInstitutionLocation: TrainingInstitutionLocation): Future[ResultSet]

    def getTrainingInstitutionLocationById(id: String, organisationId: String, locationTypeId: String) : Future[Option[TrainingInstitutionLocation]]

    def getTrainingInstitutionLocationByOrganisationId(organisationId: String) : Future[Seq[TrainingInstitutionLocation]]

    def getAllTrainingInstitutionLocations() : Future[Seq[TrainingInstitutionLocation]]
}

object TrainingInstitutionLocationService{
  def apply: TrainingInstitutionLocationService = new TrainingInstitutionLocationServiceImpl()
}