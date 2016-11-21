package services.Training

import domain.training.schedules.CourseParticipants
import services.Training.Impl.CourseParticipantsServiceImpl

import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-10.
 */
trait CourseParticipantsService {
  def createOrUpdate(course: CourseParticipants): Future[ResultSet]

  def getCourseParticipantsById( id: String): Future[Option[CourseParticipants]]

  def getCourseParticipants(): Future[Seq[CourseParticipants]]

}

object CourseParticipantsService{
  def apply:CourseParticipantsService = new CourseParticipantsServiceImpl()
}
