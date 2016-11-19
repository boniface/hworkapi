package services.Training

import domain.training.courses.CourseStatus
import services.Training.Impl.CourseStatusServiceImpl

import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-09.
 */
trait CourseStatusService {
  def createOrUpdate(course: CourseStatus): Future[ResultSet]

  def getCourseStatusById( id: String): Future[Option[CourseStatus]]

  def getCourseStatuss(id: String): Future[Seq[CourseStatus]]

}

object CourseStatusService{
  def apply: CourseStatusService = new CourseStatusServiceImpl()
}
