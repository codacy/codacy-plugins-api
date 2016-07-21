package codacy.docker.api

object Parameter {

  sealed trait Value extends Any{
    def value:Any
    override def toString: String = value.toString
  }

  object Value{

    def unapply(arg: Value): Option[Any] = Some(arg.value)

    def apply(value:String):Value = Str(value)
    def apply(value:BigDecimal):Value = Num(value)
    def apply(value:Boolean):Value = Bool(value)
    def apply(value:List[Value]):Value = Arr(value)

    case class Arr(value:List[Parameter.Value]) extends AnyVal with Parameter.Value
    case class Str(value:String) extends Parameter.Value
    case class Bool(value:Boolean) extends Parameter.Value
    case class Num(value:BigDecimal) extends Parameter.Value
  }

  case class Name(value: String) extends AnyVal{
    override def toString: String = value
  }

  case class Definition(name: Parameter.Name, value: Parameter.Value)

  case class Specification(name: Parameter.Name, default: Parameter.Value)

}
