package services.Training

import domain.training.schedules.CourseInstructors
import services.Training.Impl.CourseInstructorsServiceImpl

import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-10.
 */
trait CourseInstructorsService {
  def createOrUpdate(course: CourseInstructors): Future[ResultSet]

  def getCourseInstructorsById( id: String): Future[Option[CourseInstructors]]

  def getCourseInstructors(): Future[Seq[CourseInstructors]]

}

object CourseInstructorsService{
  def apply:CourseInstructorsService = new CourseInstructorsServiceImpl()
}
