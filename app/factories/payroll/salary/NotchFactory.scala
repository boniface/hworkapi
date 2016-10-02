package factories.payroll.salary
import domain.payroll.salary.Notch

/**
  * Created by Malu.Mukendi on 2016-09-30.
  */
object NotchFactory {
  def createNotch(values:Map[String, String], values2:BigDecimal):Notch={
    Notch(gradeId = values("gradeId"),id= values("Id"), name = values("name"), amount = values2)
  }
}
