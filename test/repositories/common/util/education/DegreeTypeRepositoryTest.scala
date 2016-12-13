package repositories.common.util.education

/**
  * Created by Aphiwe on 2016/12/13.
  */

import conf.connection.DataConnection
import domain.common.address.OfficeType
import domain.common.education.DegreeType
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.common.address.OfficeTypeRepository
import repositories.common.education.DegreeTypeRepository

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class DegreeTypeRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    DegreeTypeRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val officeType = DegreeType(
      "DegreeTypeID",
      "Diploma")

    val result = Await.result(DegreeTypeRepository.save(officeType), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetDegreeType") {
    val result = Await.result(DegreeTypeRepository.getDegreeTypeById("DegreeTypeID"), 2.minutes)
    assert( result.head.name === "Diploma")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    DegreeTypeRepository.truncate().future()
  }
}
