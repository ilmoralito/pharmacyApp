package ni.sb

import org.grails.databinding.BindUsing
import groovy.transform.ToString

@ToString
class Company implements Serializable {
    @BindUsing({ obj, source ->
        source["name"]?.toLowerCase()?.tokenize(" ")*.capitalize().join(" ")
    })
    String name
    String city
    String address
    String telephoneNumber
    BigDecimal creditLimit
    CompanyContact companyContact
    Boolean enabled = true

    Date dateCreated
    Date lastUpdated

    List<Employee> employees

    static constraints = {
        name blank: false, unique: true
        city inList: ["Leon", "Chinandega"], maxSize: 255
        address unique: true
        telephoneNumber unique: true, size: 8..8
        creditLimit min: 1.0
        companyContact nullable: false
    }

    static hasMany = [employees: Employee]

    static mapping = {
        sort "name"
        employees cascade: "all-delete-orphan"
    }
}
