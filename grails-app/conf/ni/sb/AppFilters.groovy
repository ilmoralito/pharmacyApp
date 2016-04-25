package ni.sb

class AppFilters {
    def itemService
    def springSecurityService
    def passwordEncoder

    def filters = {
        all(controller: "*", action: "*") {
            before = {
                List<String> allowedControllers = ["login", "logout", "register", "checkPassword"]

                if (!(controllerName in allowedControllers)) {
                    User currentUser = springSecurityService.currentUser
                    String currentUserPassword = currentUser.password

                    if (passwordEncoder.isPasswordValid(currentUserPassword, "temporal", null)) {
                        redirect controller: "checkPassword", action: "index"

                        return false
                    }
                }
            }
        }

        notifications(controller: "*", action: "*") {
            before = {
                Item[] itemsWithLowStocks = itemService.getItemsWithlowStocks()
                MedicineOrder[] medicineOrdersAboutToExpire = itemService.getMedicineOrdersAboutToExpire()

                session?.lowStocks = itemsWithLowStocks.size()
                session?.aboutToExpire = medicineOrdersAboutToExpire.size()
            }
        }
    }
}
