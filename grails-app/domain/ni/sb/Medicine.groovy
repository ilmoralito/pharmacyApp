package ni.sb

class Medicine extends Product {
    String code
    String genericName

    static constraints = {
        code blank: false
        genericName blank: false
        presentations nullable: false, minSize: 1
    }

    static mapping = {
        sort "name"
    }

    List<Presentation> presentations
    static hasMany = [presentations: Presentation]

    String toString() { name }
}
