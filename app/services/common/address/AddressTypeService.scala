package services.common.address

import com.websudos.phantom.dsl._
import domain.common.address.AddressType
import services.common.address.Impl.AddressTypeServiceImpl

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/11/02.
  */
trait AddressTypeService {
  def createOrUpdate(addressType:AddressType):Future[ResultSet]

  def getAddressTypeById(addressType: String): Future[Option[AddressType]]

  def getAddressTypes(addressType: String): Future[Seq[AddressType]]
}

object AddressTypeService{
  def apply: AddressTypeService = new AddressTypeServiceImpl()
}