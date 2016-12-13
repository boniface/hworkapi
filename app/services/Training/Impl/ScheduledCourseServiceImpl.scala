package services.Training.Impl

import domain.training.schedules.ScheduledCourse
import services.Service
import services.Training.{ScheduledCourseService, SubjectService}

import scala.concurrent.Future
import com.websudos.phantom.dsl._
import repositories.training.schedules.ScheduledCourseRepository
/**
 * Created by gavin.ackerman on 2016-11-15.
 */
class ScheduledCourseServiceImpl extends ScheduledCourseService with Service{
  def createOrUpdate(scheduledCourse: ScheduledCourse): Future[ResultSet] = {
    ScheduledCourseRepository.save(scheduledCourse)
  }

  def getScheduledCourseById( id: String, courseId: String, scheduledCourseId: String): Future[Option[ScheduledCourse]] = {
    ScheduledCourseRepository.getScheduledCourseById( id, courseId, scheduledCourseId)
  }

  def getScheduledCourse(id: String): Future[Seq[ScheduledCourse]] = {
    ScheduledCourseRepository.getScheduledCourse(id)
  }

}
