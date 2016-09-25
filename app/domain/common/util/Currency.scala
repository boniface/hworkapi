package domain.common.util

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/10/30.
 */
case class Currency(currencyId: String,
                     code: String,
                     name: String,
                     symbol: String
                     )

object Currency {
  implicit val currencymt = Json.format[Currency]

}
