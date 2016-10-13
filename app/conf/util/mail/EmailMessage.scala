package conf.util.mail

/**
  * Created by hashcode on 2016/10/05.
  */
case class EmailMessage(subject: String,
                        recipient: String,
                        from: String,
                        text: String,
                        html: String,
                        smtpConfig: SmtpConfig)
