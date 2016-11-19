package services.Training.Impl

import domain.training.courses.TargetGroup
import domain.training.schedules.CourseInstructors
import repositories.Training.courses.TargetGroupRepository
import repositories.Training.schedules.CourseInstructorsRepository
import services.Service
import services.Training.{CourseInstructorsService, TargetGroupService}
import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-15.
 */
class CourseInstructorsServiceImpl extends CourseInstructorsService with Service {
  def createOrUpdate(courseInstructors: CourseInstructors): Future[ResultSet] = {
    CourseInstructorsRepository.save(courseInstructors)
  }

  def getCourseInstructorsById(id: String): Future[Option[CourseInstructors]] = {
    CourseInstructorsRepository.getCourseInstructorsById(id)
  }

  def getCourseInstructors(): Future[Seq[CourseInstructors]] = {
    CourseInstructorsRepository.getAllCourseInstructors
  }

}