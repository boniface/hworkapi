package factories.position

import domain.position.PositionOccupants
import org.joda.time.DateTime
import org.scalatest.FunSuite

/**
  * Created by theo on 2016/10/10.
  */
class PositionOccupantsFactoryTest extends FunSuite{

  test("testCreatePositionOccupants") {
    val des = new PositionOccupantsFactory;

    val date = new DateTime(2016, 9, 11, 4, 20, 0, 0);

    val values = Map("positionId" -> "1",
      "positionOccupantId" -> "2",
      "userId" -> "3",
      "state" -> "test");

    val posdes = des.createPositionOccupants(values, date);

    assert(posdes == PositionOccupants(positionId = "1",
      positionOccupantId = "2",
      date = new DateTime(2016, 9, 11, 4, 20, 0, 0),
      userId = "3",
      state = "test"));

  }

}
