package services.Training.Impl

import domain.training.schedules.CourseRating
import services.Service
import services.Training.CourseRatingService

import scala.concurrent.Future
import com.websudos.phantom.dsl._
import repositories.training.schedules.CourseRatingRepository
/**
 * Created by gavin.ackerman on 2016-11-15.
 */
class CourseRatingServiceImpl extends CourseRatingService with Service{
  def createOrUpdate(courseStatus: CourseRating): Future[ResultSet] = {
    CourseRatingRepository.save(courseStatus)
  }

  def getCourseRatingById( id: String, scheduledCourseId: String): Future[Option[CourseRating]] = {
    CourseRatingRepository.getCourseRatingById( id, scheduledCourseId)
  }

  def getAllCourseRating(): Future[Seq[CourseRating]] = {
    CourseRatingRepository.getAllCourseRating
  }
  def getCourseRating(id: String): Future[Seq[CourseRating]] = {
    CourseRatingRepository.getCourseRating(id)
  }

}
