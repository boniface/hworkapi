package factories.training.schedules

import domain.training.schedules.CourseRating

/**
 * Created by gavin.ackerman on 2016-10-23.
 */
class CourseRatingFactory {
  def createCourseRating(values:Map[String, String],rating:Int):CourseRating={
    CourseRating(organisationId = values("organisationId"), scheduledCourseId = values("scheduledCourseId"), rating = rating, comment = values("comment"))
  }
}
