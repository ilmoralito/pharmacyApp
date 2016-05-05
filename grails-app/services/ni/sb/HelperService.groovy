package ni.sb

import grails.transaction.Transactional
import static java.util.Calendar.*

@Transactional
class HelperService {
    def getDates(String field) {
        Calendar calendar = Calendar.instance
        Calendar from = calendar.clone()
        Calendar to = calendar.clone()

        log.info "Quering by $field"

        if (field == "month") {
            from.set(Calendar.DAY_OF_MONTH, from.getActualMinimum(Calendar.DAY_OF_MONTH))
            to.set(Calendar.DAY_OF_MONTH, to.getActualMaximum(Calendar.DAY_OF_MONTH))
        }

        if (field == "week" || !field) {
            from.set(from.DAY_OF_WEEK, calendar.SUNDAY)
            to.set(to.DAY_OF_WEEK, 7)
        }

        if (field == "year") {
            Integer year = calendar[YEAR]

            from.set(Calendar.YEAR, year)
            from.set(Calendar.MONTH, 0)
            from.set(Calendar.DAY_OF_MONTH, 1)

            to.set(Calendar.YEAR, year)
            to.set(Calendar.MONTH, 11)
            to.set(Calendar.DAY_OF_MONTH, 31)
        }

        [from.getTime(), to.getTime()]
    }
}
