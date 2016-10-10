package factories.position

import domain.position.DepartureReason

/**
  * Created by theo on 2016/10/06.
  */
class DepartureReasonFactory {
  def createDepartureReason(values:Map[String, String]):DepartureReason={
    DepartureReason(organisationId = values("organisationId"),
      departureReasonId = values("jobEventId"),
      reason = values("reason"),
      description = values("description"),
      state = values("state"))
  }

}
