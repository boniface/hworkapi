package factories.training.competencies
import org.scalatest.FunSuite
import domain.training.competencies.CompetencyType
import factories.training.competency.CompetencyTypeFactory

/**
 * Created by gavin.ackerman on 2016-10-23.
 */
class CompetencyTypeFactoryTest extends FunSuite{
  test("testCreateCompetencyType")
  {
    val values = Map("competencyTypeId"->"1000", "name"->"Medium")

    val competencyType = CompetencyTypeFactory.createCompetencyType(values)
    assert(competencyType == CompetencyType(competencyTypeId="1000", name="Medium"))
  }
}
