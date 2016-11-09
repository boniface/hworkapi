package repositories.storage


import domain.storage.FileResults
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import scala.concurrent.Future

/**
  * Created by SONY on 2016-10-17.
  */
class FileResultsRepository extends CassandraTable[FileResultsRepository, FileResults]
{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object fileResultsId extends StringColumn(this)  with PrimaryKey[String]
  object url extends StringColumn(this)
  object size extends OptionalStringColumn(this)

  override def fromRow(r: Row): FileResults=
  {
    FileResults(
      fileResultsId(r),
      fileResultsId(r),
      url(r),
      size(r)
    )
  }
}
object FileResultsRepository extends FileResultsRepository with RootConnector
{
  override lazy val tableName = "fileresults"
  override implicit def space: KeySpace = DataConnection.keySpace
  override implicit def session: Session = DataConnection.session

  def save(fileResults: FileResults): Future[ResultSet] =
  {
    insert
      .value(_.organisationId, fileResults.organisationId)
      .value(_.fileResultsId, fileResults.fileResultsId)
      .value(_.url, fileResults.url)
      .value(_.size, fileResults.size)
      .future()
  }

  def getFileResultById(organisationId: String, fileResultsId: String): Future[Option[FileResults]] = {
    select.where(_.organisationId eqs organisationId).and (_.fileResultsId eqs fileResultsId).one()
  }

  def findAll: Future[Seq[FileResults]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getFileResults(organisationId: String): Future[Seq[FileResults]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId: String, fileResultsId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).and (_.fileResultsId eqs fileResultsId).future()
  }
}
