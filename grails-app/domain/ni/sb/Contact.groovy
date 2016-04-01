package ni.sb

class Contact implements Serializable {
    String fullName
    String email
    String telephoneNumber

    Date dateCreated
    Date lastUpdated

    static constraints = {
      fullName blank: false
      email email: true, blank: false, unique: true // TODO: check if there exist distribuitor email
      telephoneNumber blank: false, unique: true
    }

    static belongsTo = [distribuitor: Distributor]

    static mapping = {
      version false
    }

    String toString() { fullName }
}
