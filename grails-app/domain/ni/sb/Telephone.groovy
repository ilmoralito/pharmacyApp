package ni.sb

class Telephone implements Serializable {
    String telephoneNumber    

    Date dateCreated
    Date lastUpdated

    static constraints = {
        telephoneNumber blank: false, unique: true
    }

    static mapping = {
        version false
    }

    static belongsTo = [client: Client]

    String toString() { telephoneNumber }
}
