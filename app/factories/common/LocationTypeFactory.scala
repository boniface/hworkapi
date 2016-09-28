package factories.common

import domain.common.address.LocationType

class LocationTypeFactory {
  def createLocationType(values:Map[String, String]):LocationType={
  LocationType(locationTypeId = values("locationTypeId"),
                name = values("name"),
                code = values("code"),
                state = values("state"))
  }

}