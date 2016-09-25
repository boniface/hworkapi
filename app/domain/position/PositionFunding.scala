package domain.position

import org.joda.time.DateTime

/**
  * Created by hashcode on 2016/01/08.
  */
case class PositionFunding(organisationId: String, positionId: String, fundingSourcesId: String, date: DateTime)

object PositionFunding {

}
