import ni.sb.*
import grails.util.Environment
import grails.util.Holders

class BootStrap {
  def init = { servletContext ->
  	switch(Environment.current) {
  		case Environment.DEVELOPMENT:
  			def provider1 = new Provider(
  				name:"provider1",
  				address:"address1",
  				products:["product1", "product2", "product3"]
  			)

  			provider1.save()

  			def provider2 = new Provider(
  				name:"provider2",
  				address:"address2",
  				products:["product4", "product5", "product6", "product7"]
  			)
 			
  			provider2.save()

  			assert Provider.count() == 2
  			assert provider1.products.size() == 3
  			assert provider2.products.size() == 4
  		break
  	}
  }
  def destroy = {
  }
}
