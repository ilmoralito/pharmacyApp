package ni.sb

import org.grails.databinding.BindUsing

class Company {
    @BindUsing({ obj, source ->
        source["name"]?.capitalize()
    })
    String name
    String city
    String address
    String telephoneNumber
    @BindUsing({ obj, source ->
        source["contactFullName"]?.toLowerCase()?.tokenize(" ")*.capitalize().join(" ")
    })
    String contactFullName
    String contactTelephoneNumber
    String contactEmail
    Boolean enabled = true

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false, unique: true
        city blank: false, inList: ["Leon", "Chinandega"], maxSize: 255
        address blank: false, unique: true
        telephoneNumber blank: false, unique: true
        contactFullName blank: false
        contactTelephoneNumber blank: false, unique: true // TODO: validate cell numbers
        contactEmail email: true, blank: false, unique: true // TODO: get domain name if exist in each company
    }

    List<Employee> employees
    static hasMany = [employees: Employee]

    static mapping = {
        sort "name"
        employees cascade: "all-delete-orphan"
    }

    String toString() { name }
}
