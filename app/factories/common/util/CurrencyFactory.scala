package factories.common.util

import domain.common.util.Currency

class CurrencyFactory {
  def createCurrency(values:Map[String, String]):Currency={
 Currency(currencyId = values("currencyId"),
          code = values("code"),
          name = values("name"),
          symbol = values("symbol"))
  }

}