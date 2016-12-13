package repositories.common.util.education

/**
  * Created by Aphiwe on 2016/12/13.
  */

import conf.connection.DataConnection
import domain.common.address.OfficeType
import domain.common.education.{DegreeType, EducationType}
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.common.address.OfficeTypeRepository
import repositories.common.education.{DegreeTypeRepository, EducationTypeRepository}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class EducationTypeRepositoryTest extends FunSuite with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    EducationTypeRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val educationType = EducationType(
      "EducationTypeID",
      "Higher Education")

    val result = Await.result(EducationTypeRepository.save(educationType), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetEducationType") {
    val result = Await.result(EducationTypeRepository.getEducationTypeById("EducationTypeID"), 2.minutes)
    assert( result.head.name === "Higher Education")
  }

  override protected def afterEach(): Unit = {
    //Delete All records
    EducationTypeRepository.truncate().future()
  }
}
