package services.Training.Impl

import domain.training.schedules.ScheduledCourse
import repositories.Training.courses.SubjectRepository
import repositories.Training.schedules.ScheduledCourseRepository
import services.Service
import services.Training.{ScheduledCourseService, SubjectService}
import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-15.
 */
class ScheduledCourseServiceImpl extends ScheduledCourseService with Service{
  def createOrUpdate(scheduledCourse: ScheduledCourse): Future[ResultSet] = {
    ScheduledCourseRepository.save(scheduledCourse)
  }

  def getScheduledCourseById( id: String): Future[Option[ScheduledCourse]] = {
    ScheduledCourseRepository.getScheduledCourseById( id)
  }

  def getScheduledCourse(): Future[Seq[ScheduledCourse]] = {
    ScheduledCourseRepository.getAllScheduledCourse
  }

}
