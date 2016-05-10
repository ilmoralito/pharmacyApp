package ni.sb

import org.grails.databinding.BindUsing
import groovy.transform.ToString

@ToString
class Company {
    @BindUsing({ obj, source ->
        source["name"]?.capitalize()
    })
    String name
    String city
    String address
    String telephoneNumber
    BigDecimal creditLimit
    @BindUsing({ obj, source ->
        source["contactFullName"]?.toLowerCase()?.tokenize(" ")*.capitalize().join(" ")
    })
    String contactFullName
    String contactTelephoneNumber
    String contactEmail
    Boolean enabled = true
    List<Employee> employees

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false, unique: true
        city inList: ["Leon", "Chinandega"], maxSize: 255
        address unique: true
        telephoneNumber unique: true, size: 8..8
        creditLimit min: 1.0
        contactFullName blank: false
        contactTelephoneNumber unique: true, size: 8..8
        contactEmail email: true, unique: true
    }

    static hasMany = [employees: Employee]

    static mapping = {
        sort "name"
        employees cascade: "all-delete-orphan"
    }
}
