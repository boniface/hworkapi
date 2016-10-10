package factories.position

import domain.position.DepartureReason
import org.scalatest.FunSuite

/**
  * Created by theo on 2016/10/10.
  */
class DepartureReasonFactoryTest extends FunSuite {

  test("testCreateDepartureReason") {
    val dep = new DepartureReasonFactory;

    val values = Map("organisationId" -> "1",
      "departureReasonId" -> "2",
      "reason"-> "thirsty",
      "description"-> "please grab a sprite",
      "state"->"wisconsin");

    val depres = dep.createDepartureReason(values);

    assert(depres == DepartureReason(organisationId = "1",
      departureReasonId = "2",
      reason = "thirsty",
      description = "please grab a sprite",
      state = "wisconsin"));

  }

}
