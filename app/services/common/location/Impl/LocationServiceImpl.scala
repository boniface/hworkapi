package services.common.location.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.common.location.Location
import services.Service
import services.common.location.LocationService
import repositories.common.location.LocationRepository
import scala.concurrent.Future


class LocationServiceImpl extends LocationService with Service{

  override def createOrUpdate(location: Location): Future[ResultSet] = {
    LocationRepository.save(location)
  }

  override def getLocation(locationId: String): Future[Seq[Location]] = {
    LocationRepository.getLocation(locationId)
  }
}
