package factories.training.schedules

import domain.training.schedules.CourseFunding

/**
  * Created by SONY on 2016-10-19.
  */
class CourseFundingFactory
{
  def createCourseFunding(values: Map[String, String], valDec: Map[String, BigDecimal]): CourseFunding=
  {
    CourseFunding(scheduledCourseId = values("scheduledCourseId"), fundingSourcesId = values("fundingSourcesId"), amount= valDec("amount"),
      currencyId= values("currencyId"))
  }

}
