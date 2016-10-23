package factories.training.courses
import org.scalatest.FunSuite
/**
 * Created by gavin.ackerman on 2016-10-23.
 */
import domain.training.courses.Criteria

import factories.training.courses.CriteriaFactory
class CriteriaFactoryTest extends FunSuite {
  test("testCreateCriteria")
  {
    val values = Map("criteriaId"->"1000", "name"->"Accounting")
    val criteria = CriteriaFactory.createCriteria(values)
    assert(criteria == Criteria(criteriaId="1000", name="Accounting"))
  }
}
