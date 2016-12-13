package services.Training.institutions

import com.websudos.phantom.dsl.ResultSet
import domain.training.institutions.TrainingInstructor
import services.Training.institutions.Impl.TrainingInstructorServiceImpl

import scala.concurrent.Future

/**
  * Created by Yusiry on 11/24/2016.
  */
trait TrainingInstructorService {
  def createOrUpdate(trainingInstructor: TrainingInstructor) : Future[ResultSet]

  def getTrainingInstructorById(organisationId: String, trainingInstructorId: String) : Future[Option[TrainingInstructor]]

  def getTrainingInstructorsByOrganisationId(organisationId: String) : Future[Seq[TrainingInstructor]]

  def getAllTrainingInstructors() : Future[Seq[TrainingInstructor]]

}

object TrainingInstructorService{
  def apply: TrainingInstructorService = new TrainingInstructorServiceImpl()
}
