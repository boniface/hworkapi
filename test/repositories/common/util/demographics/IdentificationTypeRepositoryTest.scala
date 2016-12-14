package repositories.common.util.demographics

/**
  * Created by Aphiwe on 2016/12/13.
  */

import conf.connection.DataConnection
import domain.common.demographics.{Gender, IdentificationType}
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.common.demographics.{GenderRepository, IdentificationTypeRepository}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class IdentificationTypeRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    IdentificationTypeRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val identificationType = IdentificationType(
      "identificationTypeID",
      "Permit","working permit")

    val result = Await.result(IdentificationTypeRepository.save(identificationType), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetIdentificationType") {
    val result = Await.result(IdentificationTypeRepository.getIdentificationTypeById("identificationTypeID"), 2.minutes)
    assert( result.head.name === "Permit")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    IdentificationTypeRepository.truncate().future()
  }

}
