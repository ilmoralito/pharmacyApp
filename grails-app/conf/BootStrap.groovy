import ni.sb.*
import grails.util.Environment
import grails.util.Holders

class BootStrap {
  def init = { servletContext ->
  	switch(Environment.current) {
  		case Environment.DEVELOPMENT:
  			
  		break
  	}
  }
  def destroy = {
  }
}
