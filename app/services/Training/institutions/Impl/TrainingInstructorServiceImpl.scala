package services.Training.institutions.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.training.institutions.TrainingInstructor
import repositories.training.institutions.TrainingInstructorRepository
import services.Service
import services.Training.institutions.TrainingInstructorService

import scala.concurrent.Future

/**
  * Created by Yusiry on 11/24/2016.
  */
class TrainingInstructorServiceImpl extends TrainingInstructorService with Service{
  override def createOrUpdate(trainingInstructor: TrainingInstructor): Future[ResultSet] = {
    TrainingInstructorRepository.save(trainingInstructor)
  }

  override def getTrainingInstructorById(organisationId: String, trainingInstructorId: String): Future[Option[TrainingInstructor]] = {
    TrainingInstructorRepository.findById(organisationId, trainingInstructorId)
  }

  override def getTrainingInstructorsByOrganisationId(organisationId: String): Future[Seq[TrainingInstructor]] = {
    TrainingInstructorRepository.getTrainingInstructor(organisationId)
  }

  override def getAllTrainingInstructors(): Future[Seq[TrainingInstructor]] = {
    TrainingInstructorRepository.findAll
  }
}
