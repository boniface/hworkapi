package factories.users

import java.util.Date

import domain.users.UserPosition
import org.joda.time.DateTime

/**
  * Created by SONY on 2016-09-27.
  */
class UserPositionFactory
{
  def createPersonPosition(values: Map[String, String], date:DateTime):UserPosition=
  {
    UserPosition(organisationId = values("organisationId"), userId = values("userId"), personPositionId = values("personPositionId"),
      statusDate = date, positionId = values("positionId"), statusId = values("statusId"), reason = values("reason"))
  }

}
