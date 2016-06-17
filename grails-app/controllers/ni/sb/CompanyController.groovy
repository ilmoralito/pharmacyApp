package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class CompanyController {
    static defaultAction = "list"
    static allowedMethods = [
        list: "GET",
        create: ["GET", "POST"],
        show: "GET",
        update: "POST",
        employees: ["GET", "POST"],
        showEmployee: "GET",
        updateEmployee: "POST",
        addEmployee: "POST",
        removeEmployee: "GET"
    ]

    def list() {
        Boolean enabled = params.boolean("enabled") == null ? true : params.boolean("enabled")
        List<Company> companies = Company.where {
            enabled == enabled
        }.list(params)

        [companies: companies]
    }

    def create() {
        Company company = new Company(params)

        if (request.post) {
            if (!company.save()) {
                company.errors.allErrors.each { error ->
                    log.error "[field: $error.field: defaultMessage: $error.defaultMessage]"
                }

                flash.bag = company
            }

            flash.message = company.hasErrors() ? "A ocurrido un error" : "Accion concluida"
            return
        }

        [company: company]
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
                log.error "[field: $error.field: defaultMessage: $error.defaultMessage]"
            }

            flash.bag = company
        }

        flash.message = company.hasErrors() ? "A ocurrido un error" : "Accion concluida"
        redirect action: "show", id: id
    }

    def employees(Long id) {
        Company company = Company.get(id)

        if (!company) {
            response.sendError 404
        }

        [employees: company.employees, company: company]
    }

    def showEmployee(Long id) {
        Employee employee = Employee.get(id)

        if (!employee) {
            response.sendError 404
        }

        [employee: employee]
    }

    def updateEmployee(Long id) {
        Employee employee = Employee.get(id)

        if (!employee) {
            response.sendError 404
        }

        employee.properties = params

        if (!employee.save()) {
            employee.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }

            flash.bag = employee
        }

        flash.message = employee.hasErrors() ? "A ocurrido un error" : "Acction concluida"
        redirect action: "showEmployee", id: id
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
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }

            flash.bag = company
        }

        flash.message = company.hasErrors() ? "A ocurrido un error" : "Acction concluida"
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
