package services.Training.Impl

import domain.training.courses.CourseStatus
import domain.training.schedules.CourseRating
import repositories.Training.courses.CourseStatusRepository
import repositories.Training.schedules.CourseRatingRepository
import services.Service
import services.Training.{CourseRatingService, CourseStatusService}
import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-15.
 */
class CourseRatingServiceImpl extends CourseRatingService with Service{
  def createOrUpdate(courseStatus: CourseRating): Future[ResultSet] = {
    CourseRatingRepository.save(courseStatus)
  }

  def getCourseRatingById( id: String): Future[Option[CourseRating]] = {
    CourseRatingRepository.getCourseRatingById( id)
  }

  def getCourseRating(): Future[Seq[CourseRating]] = {
    CourseRatingRepository.getAllCourseRating
  }

}
