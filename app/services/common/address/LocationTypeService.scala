package services.common.address

import com.websudos.phantom.dsl._
import domain.common.address.LocationType
import services.common.address.Impl.LocationTypeServiceImpl

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/11/02.
  */
trait LocationTypeService {

  def createOrUpdate(locationType:LocationType):Future[ResultSet]

  def getLocationTypeById(locationType: String): Future[Option[LocationType]]

  def getLocationTypes(locationType: String): Future[Seq[LocationType]]
}

object LocationTypeService{
  def apply: LocationTypeService = new LocationTypeServiceImpl()

}
