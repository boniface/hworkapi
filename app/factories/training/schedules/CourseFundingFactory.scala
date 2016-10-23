package factories.training.schedules

import domain.training.schedules.CourseFunding

import scala.math.BigDecimal
import scala.math.BigDecimal

/**
 * Created by gavin.ackerman on 2016-10-23.
 */
object CourseFundingFactory {
  def createCourseFunding(values:Map[String, String],amount:BigDecimal):CourseFunding={
    CourseFunding(scheduledCourseId = values("scheduledCourseId"), fundingSourcesId = values("fundingSourcesId"),amount = amount,
      currencyId = values("currencyId"))
  }
}
