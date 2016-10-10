package factories.position

import domain.position.PositionEvent
import org.joda.time.DateTime
import org.scalatest.FunSuite

/**
  * Created by theo on 2016/10/10.
  */
class PositionEventFactoryTest extends FunSuite{

  test("testCreatePositionEvent") {
    val des = new PositionEventFactory;

    val date = new DateTime(2016, 9, 11, 4, 20, 0, 0);

    val values = Map("positionId" -> "1",
      "positionEventId" -> "2",
      "event" -> "test");

    val posdes = des.createPositionEvent(values, date);

    assert(posdes == PositionEvent(positionId = "1",
      positionEventId = "2",
      date = new DateTime(2016, 9, 11, 4, 20, 0, 0),
      event = "test"));
  }

}
