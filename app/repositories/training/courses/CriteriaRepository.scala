package repositories.training.courses
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.training.courses.Criteria

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-19.
  */
class CriteriaRepository extends CassandraTable[CriteriaRepository,Criteria]{
  object criteriaId extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)

  override def fromRow(r: Row): Criteria = {
    Criteria(criteriaId(r),name(r))
  }
}

object CriteriaRepository extends CriteriaRepository with RootConnector {
  override lazy val tableName = "criteria"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(criteria: Criteria): Future[ResultSet] = {
    insert
      .value(_.criteriaId, criteria.criteriaId)
      .value(_.name, criteria.name)
      .future()
  }

  def getCriteriaById(criteriaId: String):Future[Option[Criteria]] = {
    select.where(_.criteriaId eqs criteriaId).one()
  }
  def findAll: Future[Seq[Criteria]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getCriteria(criteriaId: String): Future[Seq[Criteria]] = {
    select.where(_.criteriaId eqs criteriaId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(criteriaId:String): Future[ResultSet] = {
    delete.where(_.criteriaId eqs criteriaId).future()
  }
}
