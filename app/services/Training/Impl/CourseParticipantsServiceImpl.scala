package services.Training.Impl

import domain.training.courses.CourseStatus
import domain.training.schedules.CourseParticipants
import repositories.Training.courses.CourseStatusRepository
import repositories.Training.schedules.CourseParticipantsRepository
import services.Service
import services.Training.{CourseParticipantsService, CourseStatusService}
import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-15.
 */
class CourseParticipantsServiceImpl extends CourseParticipantsService with Service{
  def createOrUpdate(courseParticipants: CourseParticipants): Future[ResultSet] = {
    CourseParticipantsRepository.save(courseParticipants)
  }

  def getCourseParticipantsById( id: String): Future[Option[CourseParticipants]] = {
    CourseParticipantsRepository.getCourseParticipantsById( id)
  }

  def getCourseParticipants(): Future[Seq[CourseParticipants]] = {
    CourseParticipantsRepository.getAllCourseParticipants
  }
}
