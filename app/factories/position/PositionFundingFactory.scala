package factories.position

import domain.position.PositionFunding
import org.joda.time.DateTime

/**
  * Created by Yusiry on 10/17/2016.
  */
class PositionFundingFactory {
  def createPositionFunding(values:Map[String, String], date:DateTime):PositionFunding = {
    PositionFunding(organisationId = values("organisationId"),
      positionId = values("positionId"),
      fundingSourcesId = values("fundingSourcesId"),
      date = DateTime)
  }

}
