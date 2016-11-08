package services.common.address.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.common.address.LocationType
import repositories.common.address.LocationTypeRepository
import services.Service
import services.common.address.LocationTypeService

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/11/02.
  */
class LocationTypeServiceImpl extends LocationTypeService with Service{
  override def createOrUpdate(locationType: LocationType): Future[ResultSet] = {
    LocationTypeRepository.save(locationType)
  }

  override def getLocationTypeById(locationType: String): Future[Option[LocationType]] = {
    LocationTypeRepository.getLocationTypeById(locationType)
  }

  override def getLocationTypes(locationType: String): Future[Seq[LocationType]] = {
    LocationTypeRepository.getLocationTypes(locationType)
  }
}
