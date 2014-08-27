import ni.sb.*
import grails.util.Environment
import grails.util.Holders

class BootStrap {
  def init = { servletContext ->
  	switch(Environment.current) {
  		case Environment.DEVELOPMENT:
  			def provider1 = Provider.build()
  			def provider2 = Provider.build()

  			assert Provider.count() == 2
  		break
  	}
  }
  def destroy = {
  }
}
