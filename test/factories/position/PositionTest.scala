package factories.position

import domain.position.Position
import org.joda.time.DateTime
import org.scalatest.FunSuite

/**
  * Created by Yusiry on 10/17/2016.
  */
class PositionTest extends FunSuite{

  test("testCreatePosition")P
  val des = new PositionFactory;

  val date = new DateTime(2016, 10, 11, 5, 20, 0, 0);

  val values = Map("positionId" -> "1",
  "organisationId" -> "1",
  "code" -> "123",
  "title" -> "testPosition",
  "jobId" -> "1",
  "positionTypeId" -> "1",
  "description" -> "test Position",
  "supervisorId" -> "2",
  "state" -> "testState");

  val posdes = des.createPosition(values, date);

  assert(posdes == Position(positionId = "1",
    positionTypeId = "1",
    organisationId = "1",
    code = "123",
    title = "testPosition",
    jobId = "1",
    description = "test Position",
    supervisorId = "2",
    state = "testState",
    date = new DateTime(2016, 10, 11, 5, 20, 0, 0)));


}
