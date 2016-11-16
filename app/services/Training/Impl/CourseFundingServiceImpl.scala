package services.Training.Impl

import domain.training.competencies.Evaluation
import domain.training.schedules.CourseFunding
import repositories.Training.competencies.EvaluationRepository
import repositories.Training.schedules.CourseFundingRepository
import services.Service
import services.Training.{CourseFundingService, EvaluationService}
import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-13.
 */
class CourseFundingServiceImpl extends CourseFundingService with Service{
  def createOrUpdate(courseFunding: CourseFunding): Future[ResultSet] = {
    CourseFundingRepository.save(courseFunding)
  }

  def getCourseFundingById( id: String): Future[Option[CourseFunding]] = {
    CourseFundingRepository.getCourseFundingById( id)
  }

  def getCourseFunding(): Future[Seq[CourseFunding]] = {
    CourseFundingRepository.getAllCourseFunding
  }


}
