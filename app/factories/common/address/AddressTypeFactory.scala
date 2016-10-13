package factories.common.address

import domain.common.address.AddressType

class AddressTypeFactory {
  def createAddressType(values:Map[String, String]):AddressType={
  AddressType(addressTypeId = values("addressTypeId"),name = values("name"))
  }

}