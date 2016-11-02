package repositories.common.demographics

/**
 * Created by hashcode on 2015/11/07.
 */
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.demographics.Gender

import scala.concurrent.Future

sealed class GenderRepository extends CassandraTable[GenderRepository,Gender]{
  object genderId extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)

  override def fromRow(r: Row): Gender = {
    Gender(genderId(r),name(r))
  }
}

object GenderRepository extends GenderRepository with RootConnector {
  override lazy val tableName = "gender"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(gender: Gender): Future[ResultSet] = {
    insert
      .value(_.genderId, gender.genderId)
      .value(_.name, gender.name)
      .future()
  }

  def getGenderById(genderId: String):Future[Option[Gender]] = {
    select.where(_.genderId eqs genderId).one()
  }
  def getGenders(genderId:String): Future[Seq[Gender]] = {
    select.where(_.genderId eqs genderId ).fetchEnumerator() run Iteratee.collect()
  }

  def deleteGenderById(genderId:String): Future[ResultSet] = {
    delete.where(_.genderId eqs genderId).future()
  }
}
