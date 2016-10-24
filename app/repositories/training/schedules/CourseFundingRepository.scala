package repositories.training.schedules
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.training.schedules.CourseFunding

import scala.concurrent.Future
/**
  * Created by SONY on 2016-10-19.
  */
class CourseFundingRepository extends CassandraTable[CourseFundingRepository,CourseFunding]{
  object scheduledCourseId extends StringColumn(this) with PartitionKey[String]
  object fundingSourcesId extends StringColumn(this)  with PrimaryKey[String]
  object amount extends BigDecimalColumn(this)
  object currencyId extends StringColumn(this)

  override def fromRow(r: Row): CourseFunding = {
    CourseFunding(scheduledCourseId(r),fundingSourcesId(r),amount(r), currencyId(r))
  }
}

object CourseFundingRepository extends CourseFundingRepository with RootConnector {
  override lazy val tableName = "coursefunding"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(courseFunding: CourseFunding): Future[ResultSet] = {
    insert
      .value(_.scheduledCourseId, courseFunding.scheduledCourseId)
      .value(_.fundingSourcesId, courseFunding.fundingSourcesId)
      .value(_.amount, courseFunding.amount)
      .value(_.currencyId, courseFunding.currencyId)
      .future()
  }

  def findById(scheduledCourseId: String, fundingSourcesId: String):Future[Option[CourseFunding]] = {
    select.where(_.scheduledCourseId eqs scheduledCourseId). and(_.fundingSourcesId eqs fundingSourcesId).one()
  }
  def findAll: Future[Seq[CourseFunding]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getCourseFunding(scheduledCourseId: String): Future[Seq[CourseFunding]] = {
    select.where(_.scheduledCourseId eqs scheduledCourseId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(scheduledCourseId:String,fundingSourcesId: String): Future[ResultSet] = {
    delete.where(_.scheduledCourseId eqs scheduledCourseId). and(_.fundingSourcesId eqs fundingSourcesId).future()
  }
}
