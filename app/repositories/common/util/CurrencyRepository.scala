package repositories.common.util

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.common.util.Currency

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/10/31.
 */
//id: String,
//code: String,
//name: String,
//symbol: String
sealed class CurrencyRepository extends CassandraTable[CurrencyRepository,Currency]{
  object currencyId extends StringColumn(this) with PartitionKey[String]
  object code extends StringColumn(this)
  object name extends StringColumn(this)
  object symbol extends StringColumn(this)
  override def fromRow(r: Row): Currency = {
    Currency(currencyId(r),code(r),name(r),symbol(r))
  }
}

object CurrencyRepository extends CurrencyRepository with RootConnector {
  override lazy val tableName = "currencies"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(currency: Currency): Future[ResultSet] = {
    insert
      .value(_.currencyId, currency.currencyId)
      .value(_.name, currency.name)
      .value(_.code, currency.code)
      .value(_.symbol, currency.symbol)
      .future()
  }

  def findById(currencyId: String):Future[Option[Currency]] = {
    select.where(_.currencyId eqs currencyId).one()
  }
  def findAll: Future[Seq[Currency]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(currencyId:String): Future[ResultSet] = {
    delete.where(_.currencyId eqs currencyId).future()
  }


}
