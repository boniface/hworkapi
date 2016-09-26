package domain.payroll.common

import org.joda.time.DateTime

/**
  * Created by hashcode on 2016/09/25.
  */
case class PensionFundPlan(pensionFundId:String,date:DateTime,details:Map[String, String] )
object PensionFundPlan{

}
