package services.common.location

import com.websudos.phantom.dsl._
import domain.job.JobEvent

import scala.concurrent.Future
import domain.common.location.Location
import services.common.location.Impl.LocationServiceImpl



trait LocationService {

  def createOrUpdate(location: Location): Future[ResultSet]
  def getLocation(locationId: String): Future[Seq[Location]]
}

object LocationService{
  def apply: LocationService = new LocationServiceImpl()
}
