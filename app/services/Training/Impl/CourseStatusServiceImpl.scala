package services.Training.Impl

import domain.training.competencies.Evaluation
import domain.training.courses.CourseStatus
import repositories.Training.competencies.EvaluationRepository
import repositories.Training.courses.CourseStatusRepository
import services.Service
import services.Training.{CourseStatusService, EvaluationService}
import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-13.
 */
class CourseStatusServiceImpl extends CourseStatusService with Service{
  def createOrUpdate(courseStatus: CourseStatus): Future[ResultSet] = {
    CourseStatusRepository.save(courseStatus)
  }

  def getCourseStatusById( id: String): Future[Option[CourseStatus]] = {
    CourseStatusRepository.getCourseStatussById( id)
  }

  def getCourseStatuss(): Future[Seq[CourseStatus]] = {
    CourseStatusRepository.getAllCourseCourseStatus
  }


}
