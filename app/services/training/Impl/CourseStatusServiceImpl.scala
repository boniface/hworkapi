package services.Training.Impl

import domain.training.courses.CourseStatus
import services.Service
import services.Training.CourseStatusService

import scala.concurrent.Future
import com.websudos.phantom.dsl._
import repositories.training.courses.CourseStatusRepository
/**
 * Created by gavin.ackerman on 2016-11-13.
 */
class CourseStatusServiceImpl extends CourseStatusService with Service{
  def createOrUpdate(courseStatus: CourseStatus): Future[ResultSet] = {
    CourseStatusRepository.save(courseStatus)
  }

  def getCourseStatusById( id: String): Future[Option[CourseStatus]] = {
    CourseStatusRepository.getCourseStatusById( id)
  }

  def getAllCourseCourseStatus(): Future[Seq[CourseStatus]] = {
    CourseStatusRepository.getAllCourseCourseStatus
  }
  def getCourseStatus(id: String): Future[Seq[CourseStatus]] = {
    CourseStatusRepository.getCourseStatus(id)
  }


}
