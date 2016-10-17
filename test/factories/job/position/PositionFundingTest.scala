package factories.job.position

import domain.position.PositionFunding
import factories.position.PositionFundingFactory
import org.joda.time.DateTime
import org.scalatest.FunSuite

/**
  * Created by Yusiry on 10/17/2016.
  */
class PositionFundingTest extends FunSuite{

  test("testCreatePositionFunding"){
    val des = new PositionFundingFactory;

    val date = new DateTime(2016, 4, 2, 1, 20, 2, 0);

    val values = Map("organisationId" -> "1",
    "positionId" -> "1",
    "fundingSourcesId" -> "1"
    );

    val posdes = des.createPositionFunding(values, date);

    assert(posdes == PositionFunding(organisationId = "1",
      positionId = "1",
      fundingSourcesId = "1",
      date = new DateTime(2016, 4, 2, 1, 20, 2, 0)));
  }

}
