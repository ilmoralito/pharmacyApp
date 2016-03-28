package ni.sb

class Medicine extends Product {
    String code
    String genericName

    static constraints = {
        code blank: false
        genericName blank: false
        presentations minSize: 1
    }

    static mapping = {
        presentations cascade: "all-delete-orphan"
    }

    List<Presentation> presentations
    static hasMany = [presentations: Presentation]

    String toString() { name }
}
