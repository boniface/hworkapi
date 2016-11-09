package services.common.address

import com.websudos.phantom.dsl._
import domain.common.address.OfficeType
import services.common.address.Impl.OfficeTypeServiceImpl

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/11/02.
  */
trait OfficeTypeService {

  def createOrUpdate(officeType:OfficeType):Future[ResultSet]

  def getOfficeTypeById(locationType: String): Future[Option[OfficeType]]

  def getOfficeTypes(locationType: String): Future[Seq[OfficeType]]
}

object OfficeTypeService{
  def apply: OfficeTypeService = new OfficeTypeServiceImpl()

}
