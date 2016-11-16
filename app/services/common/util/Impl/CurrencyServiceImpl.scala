package services.common.util.Impl

import repositories.common.util.CurrencyRepository
import com.websudos.phantom.dsl.ResultSet
import domain.common.util.Currency
import services.Service
import services.common.util.CurrencyService

import scala.concurrent.Future

class CurrencyServiceImpl extends CurrencyService with Service {
  override def createOrUpdate(currency: Currency): Future[ResultSet] = {
    CurrencyRepository.save(currency)
  }

  override def getCurrency(currencyId: String): Future[Seq[Currency]] = {
    CurrencyRepository.getCurrency(currencyId)
  }
}
