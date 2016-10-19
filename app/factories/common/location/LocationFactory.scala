package factories.common.location
import domain.common.location.Location
/**
  * Created by SONY on 2016-10-19.
  */
class LocationFactory
{
  def createLocation(values:Map[String,String]):Location={
    Location(locationId = values("locationId"),name = values("name"), latititude= values("latititude"), longitude = values("longitude"),
      code= values("code"), locationTypeId = values("locationTypeId"), locationParentId = Option("locationParentId"))
  }
}
