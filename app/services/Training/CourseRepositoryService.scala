package services.Training

import services.Training.Impl.CourseServiceImpl

import scala.concurrent.Future
import com.websudos.phantom.dsl._
import domain.training.courses.{Course, CourseCompetencies}

/**
 * Created by gavin.ackerman on 2016-11-09.
 */
trait CourseRepositoryService {
  def createOrUpdate(course: Course): Future[ResultSet]

  def getCourseById( id: String): Future[Option[Course]]

  def getCourses(): Future[Seq[Course]]

}

object CourseRepositoryService{
  def apply: CourseRepositoryService = new CourseServiceImpl()
}