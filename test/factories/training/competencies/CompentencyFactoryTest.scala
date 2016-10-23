package factories.training.competencies
import org.scalatest.FunSuite
import domain.training.competencies.Competency
import factories.training.competency.CompetencyFactory

/**
 * Created by gavin.ackerman on 2016-10-23.
 */
class CompetencyFactoryTest extends FunSuite {
  test("testCreateCompetency")
  {
    val values = Map("compencyId"->"1000", "name"->"Medium", "competencyTypeId"->"2000")

    val competency = CompetencyFactory.createCompetency(values)
    assert(competency == Competency(compencyId="1000", name="Medium", "competencyTypeId"->"2000"))
  }
}
