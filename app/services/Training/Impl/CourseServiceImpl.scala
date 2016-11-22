package services.Training.Impl

import domain.training.competencies.Evaluation
import domain.training.courses.Course
import repositories.Training.competencies.EvaluationRepository
import repositories.Training.courses.CourseRepository
import services.Service
import services.Training.{CourseRepositoryService, EvaluationService}
import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-13.
 */
class CourseServiceImpl extends CourseRepositoryService with Service{
  def createOrUpdate(course: Course): Future[ResultSet] = {
    CourseRepository.save(course)
  }

  def getCourseById( id: String): Future[Option[Course]] = {
    CourseRepository.getCourseTypeById( id)
  }

  def getCourse(): Future[Seq[Course]] = {
    CourseRepository.getAllCourseType
  }


}
