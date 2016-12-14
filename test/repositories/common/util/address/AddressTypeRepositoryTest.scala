package repositories.common.util.address

import conf.connection.DataConnection
import domain.common.address.AddressType
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.common.address.AddressTypeRepository

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Aphiwe on 2016/12/12.
  */
class AddressTypeRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keySpace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    AddressTypeRepository.create.ifNotExists().future()
  }
    test("testSaveOrUpdate"){
      val addresType = AddressType("h","Home")

      val results = Await.result(AddressTypeRepository.getAddressType("h"), 2.minutes)
      assert(results.head.addressTypeId === "h")
    }

  override protected def afterEach(): Unit = {
    AddressTypeRepository.truncate().future()
  }
}
