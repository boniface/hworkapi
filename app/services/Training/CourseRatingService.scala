package services.Training

import domain.training.schedules.CourseRating
import services.Training.Impl.CourseRatingServiceImpl

import scala.concurrent.Future
import com.websudos.phantom.dsl._
/**
 * Created by gavin.ackerman on 2016-11-10.
 */
trait CourseRatingService {
  def createOrUpdate(course: CourseRating): Future[ResultSet]

  def getCourseRatingById( id: String, scheduledCourseId: String): Future[Option[CourseRating]]

  def getAllCourseRating(): Future[Seq[CourseRating]]

  def getCourseRating(id: String): Future[Seq[CourseRating]]

}

object CourseRatingService{
  def apply:CourseRatingService = new CourseRatingServiceImpl()
}
