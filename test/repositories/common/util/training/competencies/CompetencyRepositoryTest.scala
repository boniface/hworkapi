package repositories.common.util.training.competencies

import conf.connection.DataConnection
import domain.training.competencies.Competency
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import repositories.training.competencies.CompetencyRepository
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by gavin.ackerman on 2016-12-14.
 */
class CompetencyRepositoryTest extends FunSuite   with BeforeAndAfterEach{

  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session

  override protected def beforeEach(): Unit = {
    //Create Table
    CompetencyRepository.create.ifNotExists().future()
  }

  test("testSaveOrUpdate") {
    val competency = Competency(
      "comp2",
      "goodComp",
      "comp1"
    )

    val result = Await.result(CompetencyRepository.save(competency), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetCompetency") {
    val result = Await.result(CompetencyRepository.getcompById("comp2"), 2.minutes)
    assert( result.head.name === "goodComp")
  }
  override protected def afterEach(): Unit = {
    //Delete All records
    CompetencyRepository.truncate().future()
  }
}
