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

  def getCourseSubjectById( id: String, courseId: String, subjectId: String): Future[Option[CourseSubjects]]

  def getAllCourseCourseSubjects(): Future[Seq[CourseSubjects]]

  def getCourseSubjects(id: String): Future[Seq[CourseSubjects]]

}

object CourseSubjectService{
  def apply: CourseSubjectService = new CourseSubjectsServiceImpl()
}
