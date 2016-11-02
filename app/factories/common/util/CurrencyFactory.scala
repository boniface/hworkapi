package factories.common.util

import domain.common.util.Currency

/**
  * Created by SONY on 2016-09-28.
  */
class CurrencyFactory {
  def createCurrency(values:Map[String,String]):Currency={
    Currency(currencyId = values("currencyId"),code = values("code"),
      name = values("name"),symbol = values("symbol"))

  }

}
