package repositories.common.util.training.competencies

import conf.connection.DataConnection
import domain.training.competencies.CompetencyType
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.training.competencies.CompetencyTypeRepository
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by gavin.ackerman on 2016-12-14.
 */
class CompetencyTypeRepositoryTest extends FunSuite   with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    CompetencyTypeRepository.create.ifNotExists().future()
  }
 // CompetencyType(competencyTypeId: String, name: String)
  test("testSaveOrUpdate") {
    val competencyType = CompetencyType(
      "comp1",
      "good"
)

    val result = Await.result(CompetencyTypeRepository.save(competencyType), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCompetencyType") {
    val result = Await.result(CompetencyTypeRepository.getCompetencyTypeById("comp1"), 2.minutes)
    assert( result.head.name === "good")
  }
  override protected def afterEach(): Unit = {
    //Delete All records
    CompetencyTypeRepository.truncate().future()
  }

}
