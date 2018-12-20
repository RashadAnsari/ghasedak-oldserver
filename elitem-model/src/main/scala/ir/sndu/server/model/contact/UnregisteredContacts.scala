package ir.sndu.server.model.contact

@SerialVersionUID(1L)
case class UnregisteredContact(ownerUserId: Int, name: Option[String])

@SerialVersionUID(1L)
case class UnregisteredPhoneContact(phoneNumber: Long, ownerUserId: Int, name: Option[String])

@SerialVersionUID(1L)
case class UnregisteredEmailContact(email: String, ownerUserId: Int, name: Option[String])
