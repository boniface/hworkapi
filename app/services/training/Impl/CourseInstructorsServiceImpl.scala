package services.Training.Impl

import domain.training.schedules.CourseInstructors
import services.Service
import services.Training.CourseInstructorsService

import scala.concurrent.Future
import com.websudos.phantom.dsl._
import repositories.training.schedules.CourseInstructorsRepository
/**
 * Created by gavin.ackerman on 2016-11-15.
 */
class CourseInstructorsServiceImpl extends CourseInstructorsService with Service {
  def createOrUpdate(courseInstructors: CourseInstructors): Future[ResultSet] = {
    CourseInstructorsRepository.save(courseInstructors)
  }

  def getCourseInstructorsById(id: String, TrainingInstructorId: String): Future[Option[CourseInstructors]] = {
    CourseInstructorsRepository.getCourseInstructorsById(id, TrainingInstructorId)
  }

  def getAllCourseInstructors(): Future[Seq[CourseInstructors]] = {
    CourseInstructorsRepository.getAllCourseInstructors
  }
  def getCourseInstructors(id: String): Future[Seq[CourseInstructors]] = {
    CourseInstructorsRepository.getCourseInstructors(id)
  }

}