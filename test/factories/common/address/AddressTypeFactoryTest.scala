package factories.common.address

import org.scalatest.FunSuite
import factories.common.address.AddressTypeFactory

class AddressTypeFactoryTest extends FunSuite {

  test("testCreateAddressType") {
    
    val createdAddressType = new AddressTypeFactory.createdAddressType();
  
    assert(1==1)

  }

}