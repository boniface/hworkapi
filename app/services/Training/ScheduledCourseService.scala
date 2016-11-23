package services.Training

import domain.training.schedules.ScheduledCourse
import services.Training.Impl.ScheduledCourseServiceImpl

import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-10.
 */
trait ScheduledCourseService {
  def createOrUpdate(course: ScheduledCourse): Future[ResultSet]

  def getScheduledCourseById( id: String, courseId: String , scheduledCourseId:String): Future[Option[ScheduledCourse]]

  def getScheduledCourse(id: String): Future[Seq[ScheduledCourse]]

}

object ScheduledCourseService{
  def apply:ScheduledCourseService = new ScheduledCourseServiceImpl()
}