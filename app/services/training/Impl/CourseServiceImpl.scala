package services.Training.Impl


import domain.training.courses.Course
import services.Service
import services.Training.CourseRepositoryService

import scala.concurrent.Future
import com.websudos.phantom.dsl._
import repositories.training.courses.CourseRepository
/**
 * Created by gavin.ackerman on 2016-11-13.
 */
class CourseServiceImpl extends CourseRepositoryService with Service{
  def createOrUpdate(course: Course): Future[ResultSet] = {
    CourseRepository.save(course)
  }

  def getCourseById( id: String, courseId: String, courseCode: String): Future[Option[Course]] = {
    CourseRepository.getCourseTypeById( id, courseId, courseCode)
  }

  def getAllCourseType(): Future[Seq[Course]] = {
    CourseRepository.getAllCourseType
  }
  def getCourse(id: String): Future[Seq[Course]] = {
    CourseRepository.getCourse(id)
  }

}
