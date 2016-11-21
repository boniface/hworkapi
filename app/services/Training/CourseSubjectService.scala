package services.Training

import domain.training.courses.CourseSubjects
import services.Training.Impl.CourseSubjectsServiceImpl

import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-09.
 */
trait CourseSubjectService {
  def createOrUpdate(course: CourseSubjects): Future[ResultSet]

  def getCourseSubjectById( id: String): Future[Option[CourseSubjects]]

  def getCourseSubjects(): Future[Seq[CourseSubjects]]

}

object CourseSubjectService{
  def apply: CourseSubjectService = new CourseSubjectsServiceImpl()
}
