package factories.training.schedules

import domain.training.schedules.CourseFunding
import org.scalatest.FunSuite
import scala.math.BigDecimal
import scala.math.BigDecimal
import scala.math.BigDecimal

/**
 * Created by gavin.ackerman on 2016-10-23.
 */
class CourseFundingFactoryTest extends FunSuite {
  test("testCreateCourseFunding")
  {
    val values = Map("scheduledCourseId"->"1000", "fundingSourcesId"->"Accounting","currencyId"->"1000")
    val amt:BigDecimal = 20
    val courseFunding = CourseFundingFactory.createCourseFunding(values,amt)

    assert(courseFunding == CourseFunding(scheduledCourseId="1000", fundingSourcesId="Accounting", amount = 20,currencyId="1000"))
  }
}
