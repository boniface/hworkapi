package factories.training.courses
import org.scalatest.FunSuite
/**
 * Created by gavin.ackerman on 2016-10-23.
 */
import domain.training.courses.TargetGroup
import factories.training.courses.CourseCategoryFactory
class TargetGroupFactoryTest extends FunSuite {
  test("testCreateTargetGroup")
  {
    val values = Map("targetGroupId"->"1000", "name"->"Accounting")
    val targetGroup = TargetGroupFactory.createTargetGroup(values)
    assert(targetGroup == TargetGroup(targetGroupId="1000", name="Accounting"))
  }
}
