package ni.sb

class Measure {
    String name

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false, unique: true
    }

    static mapping = {
        version false
    }

    static belongsTo = Presentation
    static hasMany = [presentations: Presentation]

    String toString() { name }
}
