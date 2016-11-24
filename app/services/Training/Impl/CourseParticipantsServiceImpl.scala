package services.Training.Impl

import domain.training.schedules.CourseParticipants
import services.Service
import services.Training.CourseParticipantsService

import scala.concurrent.Future
import com.websudos.phantom.dsl._
import repositories.training.schedules.CourseParticipantsRepository
/**
 * Created by gavin.ackerman on 2016-11-15.
 */
class CourseParticipantsServiceImpl extends CourseParticipantsService with Service{
  def createOrUpdate(courseParticipants: CourseParticipants): Future[ResultSet] = {
    CourseParticipantsRepository.save(courseParticipants)
  }

  def getCourseParticipantsById( id: String, userId: String): Future[Option[CourseParticipants]] = {
    CourseParticipantsRepository.getCourseParticipantsById( id, userId)
  }

  def getAllCourseParticipants(): Future[Seq[CourseParticipants]] = {
    CourseParticipantsRepository.getAllCourseParticipants
  }
  def getCourseParticipants(id: String): Future[Seq[CourseParticipants]] = {
    CourseParticipantsRepository.getCourseParticipants(id)
  }
}
