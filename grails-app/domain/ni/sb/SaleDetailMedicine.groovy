package ni.sb

class SaleDetailMedicine extends SaleDetail {
  Presentation presentation
  String measure

  static constraints = {
    presentation nullable:false
    measure blank:false
  }
}
