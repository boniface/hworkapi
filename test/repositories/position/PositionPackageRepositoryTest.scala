package repositories.position

import conf.connection.DataConnection
import domain.position.PositionPackage
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * Created by Yusiry on 12/14/2016.
  */
class PositionPackageRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    PositionPackageRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate"){
    val positionPackage = PositionPackage("POS100", "POSPK01", "01", "NOT01",
    new DateTime(2016,11,9,12,0,0,0), "State")

    val result = Await.result(PositionPackageRepository.save(positionPackage), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetPositionPackage"){
    val result = Await.result(PositionPackageRepository.getPositionPackage("POS100", "POSPK01"), 2.minutes)
    assert(result.head.state == "State")
  }

  test("testGetAllPositionPackages"){
    val result = Await.result(PositionPackageRepository.getPositionPackages("POS100"), 2.minutes)
    assert(result.size > 0)
  }

  override protected def afterEach(): Unit = {
    PositionPackageRepository.truncate().future()
  }




}
