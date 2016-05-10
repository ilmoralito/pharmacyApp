package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class CompanyController {
    static defaultAction = "list"
    static allowedMethods = [
        list: ["GET", "POST"],
        show: "GET",
        update: "POST",
        employees: ["GET", "POST"],
        showEmployee: "GET",
        updateEmployee: "POST",
        addEmployee: "POST",
        removeEmployee: "GET"
    ]

    def list(Boolean enabled) {
        Closure getCompanies = {
            if (enabled == null) {
                enabled = true
            }

            Company.findAllByEnabled(enabled)
        }

        if (request.post) {
            Company company = new Company(params)

            if (!company.save()) {
                company.errors.allErrors.each { error ->
                    log.error "[$error.field: $error.defaultMessage]"
                }

                flash.message = "A ocurrido un error."
                return [companies: getCompanies(), company: company]
            }
        }

        [companies: getCompanies()]
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

            flash.message = "A ocurrido un error."
        }

        redirect action: "show", id: id
    }

    def employees(Long id) {
        Company company = Company.get(id)

        if (!company) {
            response.sendError 404
        }

        [employees: company.employees, companyId: company.id, companyName: company.name]
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
                log.error "[$error.field: $error.defaultMessage]"
            }

            flash.message = "A ocurrido un error."
            chain action: "showEmployee", params: [id: id], model: [employee: employee]
            return
        }

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
                log.error "[$error.field: $error.defaultMessage]"
            }

            flash.message = "A ocurrido un error."
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
