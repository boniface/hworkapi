package services.Training.Impl

import domain.training.competencies.Evaluation
import domain.training.courses.CourseSubjects
import repositories.Training.competencies.EvaluationRepository
import repositories.Training.courses.CourseSubjectsRepository
import services.Service
import services.Training.{CourseSubjectService, EvaluationService}
import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-13.
 */
class CourseSubjectsServiceImpl extends CourseSubjectService with Service{
  def createOrUpdate(courseSubjects: CourseSubjects): Future[ResultSet] = {
    CourseSubjectsRepository.save(courseSubjects)
  }

  def getCourseSubjectsById( id: String): Future[Option[CourseSubjects]] = {
    CourseSubjectsRepository.getCourseSubjectsById( id)
  }

  def getCourseSubjects(): Future[Seq[CourseSubjects]] = {
    CourseSubjectsRepository.getAllCourseCourseSubjects
  }


}