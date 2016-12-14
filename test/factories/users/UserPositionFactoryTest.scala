package factories.users

import domain.users.UserPosition
import org.joda.time.DateTime
import org.scalatest.FunSuite

/**
  * Created by SONY on 2016-09-28.
  */
class UserPositionFactoryTest extends FunSuite {

  test("testCreateUserPosition") {
    val userPositionFactory = new UserPositionFactory;

    val date = new DateTime(2016, 4, 2, 1, 20, 2, 0);

    val values = Map("organisationId" -> "1",
      "userId" -> "1",
      "personPositionId" -> "1",
      "positionId" -> "1",
      "statusId" -> "1",
      "reason" -> "nofile"
    );

    val userPositionF = userPositionFactory.createPersonPosition(values, date);

    assert(userPositionF == UserPosition(organisationId = "1",
      userId = "1",
      personPositionId = "1",
      statusDate = new DateTime(2016, 4, 2, 1, 20, 2, 0),
      positionId = "1",
      statusId = "1",
      reason = "nofile"));
  }
}
