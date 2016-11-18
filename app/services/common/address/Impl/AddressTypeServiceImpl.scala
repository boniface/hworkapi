package services.common.address.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.common.address.AddressType
import repositories.common.address.AddressTypeRepository
import services.Service
import services.common.address.AddressTypeService

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/11/02.
  */
class AddressTypeServiceImpl extends AddressTypeService with Service{
  override def createOrUpdate(addressType: AddressType): Future[ResultSet] = {
    AddressTypeRepository.save(addressType)
  }

  override def getAddressTypeById(addressType: String): Future[Option[AddressType]] = {
    AddressTypeRepository.getAddressTypeById(addressType)
  }

  override def getAddressTypes(addressType: String): Future[Seq[AddressType]] = {
    AddressTypeRepository.getAddressTypes(addressType)
  }
}
