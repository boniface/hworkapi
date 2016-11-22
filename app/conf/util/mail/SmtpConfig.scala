package conf.util.mail

/**
  * Created by hashcode on 2016/10/05.
  */
case class SmtpConfig(port : Int = 587,
                      host : String="smtp.gmail.com",
                      user : String,
                      password: String)
