package repositories.storage


import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.storage.FileMeta

import scala.concurrent.Future

/**
  * Created by SONY on 2016-10-17.
  */
class FileMetaRepository extends CassandraTable[FileMetaRepository, FileMeta]
{
  object organisationId extends StringColumn(this) with PartitionKey[String]
  object fileName extends StringColumn(this)
  object contentType extends StringColumn(this)

  override def fromRow(r: Row): FileMeta=
  {
    FileMeta(
      organisationId(r),
      fileName(r),
      contentType(r)
    )
  }
}
object FileMetaRepository extends FileMetaRepository with RootConnector
{
  override lazy val tableName = "filemeta"
  override implicit def space: KeySpace = DataConnection.keySpace
  override implicit def session: Session = DataConnection.session

  def save(fileResults: FileMeta): Future[ResultSet] =
  {
    insert
      .value(_.organisationId, fileResults.organisationId)
      .value(_.fileName, fileResults.fileName)
      .value(_.contentType, fileResults.contentType)
      .future()
  }

  def getFileResultById(organisationId: String): Future[Option[FileMeta]] = {
    select.where(_.organisationId eqs organisationId).one()
  }

  def findAll: Future[Seq[FileMeta]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
  def getFileMeta(organisationId: String): Future[Seq[FileMeta]] = {
    select.where(_.organisationId eqs organisationId).fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(organisationId: String): Future[ResultSet] = {
    delete.where(_.organisationId eqs organisationId).future()
  }
}
