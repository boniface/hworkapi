package services.common.util

import com.websudos.phantom.dsl._
import domain.common.util.Currency
import services.common.util.Impl.CurrencyServiceImpl

import scala.concurrent.Future


trait CurrencyService {

  def createOrUpdate(currency: Currency): Future[ResultSet]
  def getCurrency(currencyId: String): Future[Seq[Currency]]

}

object CurrencyService{
  def apply: CurrencyService = new CurrencyServiceImpl()
}
