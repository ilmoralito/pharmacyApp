package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class CompanyController {
    static defaultAction = "list"
    static allowedMethods = [
        list: "GET",
        show: "GET",
        update: "POST",
        updateEnabledState: "GET",
        employees: ["GET", "POST"],
        addEmployee: "POST",
        removeEmployee: "GET"
    ]

    def list() {
        if (request.method == "POST") {
            Company company = new Company(params)

            if (!company.save()) {
                company.errors.allErrors.each { error ->
                    log.error "[$error.field: $error.defaultMessage]"
                }

                flash.message = "A ocurrido un error. Intentalo otravez"

                return [companies: Company.list(), company: company]
            }
        }

        List<Company> companies = Company.list()

        [companies: companies]
    }

    def show(Long id) {
        Company company = Company.get(id)

        if (!company) {
            response.sendError 404
        }

        [company: company]
    }

    def update(Long id) {
        Company company = Company.get(id)

        if (!company) {
            response.sendError 404
        }

        company.properties = params
        
        if (!company.save()) {
            company.errors.allErrors.each { error ->
                log.error "[$error.field: $error.defaultMessage]"
            }

            flash.message = "A ocurrido un error. Intentalo otravez"
        }

        redirect action: "show", id: id
    }

    def updateEnabledState(Long id) {
        Company company = Company.get(id)

        if (!company) {
            response.sendError 404
        }

        company.properties["enabled"] = !company.enabled
        company.save()

        redirect action: "list"
    }

    def employees(Long id) {
        Company company = Company.get(id)

        if (!company) {
            response.sendError 404
        }

        [employees: company.employees, company: company]
    }

    def addEmployee(Long id) {
        Company company = Company.get(id)

        if (!company) {
            response.sendError 404
        }

        Employee employee = new Employee(params)

        company.addToEmployees(employee)

        if (!company.save()) {
            company.errors.allErrors.each { error ->
                log.error "[$error.field: $error.defaultMessage]"
            }

            flash.message = "A ocurrido un error. Intentalo otravez"

            chain action: "employees", model: [employee: employee], params: [id: id]
            return
        }

        redirect action: "employees", id: id
    }

    def removeEmployee(Long id, Long employeeId) {
        Company company = Company.get(id)
        Employee employee = Employee.get(employeeId)

        if (!company || !employee) {
            response.sendError 404
        }

        company.removeFromEmployees(employee)
        company.save()

        redirect action: "employees", id: id
    }
}
