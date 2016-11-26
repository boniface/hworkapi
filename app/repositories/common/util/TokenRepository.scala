package repositories.common.util

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.common.util.Token

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/06/09.
 */
class TokenRepository extends CassandraTable[TokenRepository, Token] {

  object id extends StringColumn(this) with PartitionKey[String]

  object tokenValue extends StringColumn(this)

  override def fromRow(row: Row): Token = {
    Token(
      id(row),
      tokenValue(row)
    )
  }
}

object TokenRepository extends TokenRepository with RootConnector {
  override lazy val tableName = "tokens"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session


  def save(token: Token): Future[ResultSet] = {
    insert
      .value(_.id, token.id)
      .value(_.tokenValue, token.tokenValue)
      .ttl(12000)
      .future()
  }

  def getTokenById(id:String):Future[Option[Token]] = {
    select.where(_.id eqs id).one()

  }
  def deleteToken(id:String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }

}
