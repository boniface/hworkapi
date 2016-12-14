package factories.users

import domain.users.UserRole
import org.joda.time.DateTime
import org.scalatest.FunSuite

/**
  * Created by SONY on 2016-09-28.
  */
class UserRoleFactoryTest extends FunSuite {

  test("testCreateUserRole") {
    val userRoleFactory = new UserRoleFactory;

    val date = new DateTime(2016, 4, 2, 1, 20, 2, 0);

    val values = Map("organisationId" -> "1",
      "userId" -> "1",
      "roleId" -> "1",
      "state" -> "1"
    );

    val userRoleF = userRoleFactory.createPersonRole(values, date);

    assert(userRoleF == UserRole(organisationId = "1",
      userId = "1",
      roleId = "1",
      state = "1",
      date = new DateTime(2016, 4, 2, 1, 20, 2, 0)));
  }
}