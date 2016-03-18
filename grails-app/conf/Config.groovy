// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = "ni.sb" // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ["Gecko", "WebKit", "Presto", "Trident"]
grails.mime.types = [ // the first one is the default format
  all:           "*/*", // "all" maps to "*" or the first available format in withFormat
  atom:          "application/atom+xml",
  css:           "text/css",
  csv:           "text/csv",
  form:          "application/x-www-form-urlencoded",
  html:          ["text/html","application/xhtml+xml"],
  js:            "text/javascript",
  json:          ["application/json", "text/json"],
  multipartForm: "multipart/form-data",
  rss:           "application/rss+xml",
  text:          "text/plain",
  hal:           ["application/hal+json","application/hal+xml"],
  xml:           ["text/xml", "application/xml"]
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ["/images/*", "/css/*", "/js/*", "/plugins/*"]
grails.resources.adhoc.includes = ["/images/**", "/css/**", "/js/**", "/plugins/**"]

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = "singleton"

// GSP settings
grails {
  views {
    gsp {
      encoding = "UTF-8"
      htmlcodec = "xml" // use xml escaping instead of HTML4 escaping
      codecs {
          expression = "html" // escapes values inside ${}
          scriptlet = "html" // escapes output from scriptlets in GSPs
          taglib = "none" // escapes output from taglibs
          staticparts = "none" // escapes output from static template parts
      }
    }
    // escapes all not-encoded output at final stage of outputting
    // filteringCodecForContentType."text/html" = "html"
  }
}


grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = "Instance"

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ["password"]

// configure auto-caching of queries by default (if false you can cache individual queries with "cache: true")
grails.hibernate.cache.queries = false

// configure passing transaction"s read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

environments {
  development {
    grails.logging.jul.usebridge = true
  }
  production {
    grails.logging.jul.usebridge = false
    // TODO: grails.serverURL = "http://www.changeme.com"
  }
}

// log4j configuration
log4j = {
  // Example of changing the log pattern for the default console appender:
  //
  //appenders {
  //    console name:"stdout", layout:pattern(conversionPattern: "%c{2} %m%n")
  //}

  error  "org.codehaus.groovy.grails.web.servlet",        // controllers
         "org.codehaus.groovy.grails.web.pages",          // GSP
         "org.codehaus.groovy.grails.web.sitemesh",       // layouts
         "org.codehaus.groovy.grails.web.mapping.filter", // URL mapping
         "org.codehaus.groovy.grails.web.mapping",        // URL mapping
         "org.codehaus.groovy.grails.commons",            // core / classloading
         "org.codehaus.groovy.grails.plugins",            // plugins
         "org.codehaus.groovy.grails.orm.hibernate",      // hibernate integration
         "org.springframework",
         "org.hibernate",
         "net.sf.ehcache.hibernate"
}

grails.plugin.springsecurity.ui.forgotPassword.emailBody = '''
  <h1>Hola,</h1>
  $user.username
  <br><br>
  usted ha hecho una solicitud,
  para restaura su contaseña,
  si en realidad desea cambiar por cualquier razon su contraseña,
  de click en el enlace siguiente: <br>
  <h4><a href="$url">Restaurar Contraseña</a></h4>. <br> <h3>Esperamos esto soluciones sus dudas</h3>dudas
'''
grails.plugin.springsecurity.ui.forgotPassword.emailSubject = "Restauración de contraseña Aplicacion FSB"

// App configuration
grails.plugin.springsecurity.userLookup.usernamePropertyName = "email"

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = "ni.sb.User"
grails.plugin.springsecurity.userLookup.authorityJoinClassName = "ni.sb.UserRole"
grails.plugin.springsecurity.authority.className = "ni.sb.Role"
grails.plugin.springsecurity.logout.postOnly = false // lo que hace es evitar que solo por post se cierre sesion
grails.plugin.springsecurity.successHandler.defaultTargetUrl = "/notifications/quantity"
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	"/":                              ["permitAll"],
	"/index":                         ["permitAll"],
	"/index.gsp":                     ["permitAll"],
  "/dbconsole/**":                  ["permitAll"],
	"/assets/**":                     ["permitAll"],
	"/**/js/**":                      ["permitAll"],
	"/**/css/**":                     ["permitAll"],
	"/**/images/**":                  ["permitAll"],
	"/**/favicon.ico":                ["permitAll"],
  "/register/forgotPassword/**":    ["permitAll"],
  "/register/index/**":             ["permitAll"],
  "/register/resetPassword/**":     ["permitAll"],
  "/register/register/**":          ["permitAll"]
]

ni.sb.presentationsAndMeasures = [
  Talco:
  ["80gm"],
  Tabletas:
  [
    "0.5mg", "1mg", "2mg", "4mg", "5mg", "15mg", "20mg", "25mg", "20mg + 10mg", "40mg",
    "50mg", "50mg + 12mg + 3mg", "100mg", "60mg + 50mg + 5mg", "6mg + 5mg + 3mg + 39mg + 61mg + 10mg",
    "125mg + 1mg", "150mg", "120mg + 25mg + 10mg + 20mg + 1.5mg", "200mg", "200mg + 0.4mg", "200mg + 10mg + 6mg + 3mg",
    "200mg + 40mg", "250mg", "200mg + 25mg + 25mg", "50mg + 0.2mg + 2ui + 50mg + 10mg + 2mg + 2mg + 50mg + 100mg + 55mg",
    "200mg + 80mg", "150mg + 150mg + 40mg", "50mg + 25ui + 50mg + 10mg + 2mg + 2mg + 50mg + 100mg + 50mg + 100mg + 55mg",
    "400mg", "4mg + 45mg + 45mg + 45mg + 45mg +  45mg + 45mg + 45mg + 45mg + 45mg + 50mg",
    "100mg + 90mg + 50mg + 10mg + 50mg + 10mg + 40mg + 100mg",
    "500mg", "11.5mg + 507mg", "550mg", "600mg + 125ui", "160 + 800mg", "875 + 125mg",
    "5mg + 1000mg","50mg + 50mg + 1000mcg + 50mg + 400ui",
    "1667.67mg + 6.2mg + 85.7mg + 9.91mg + 4.05mg +5.48mg",
    "100mg + 100mg + 87.5mg + 15mg + 5mg + 5mg + 1000mcg + 400mcg + 70mcg + 50mcg + 5mcg",
    "3000ui + 10mcg + 60mg + 400 ui",
    "25000ui + 400 ui + 1.5mg + 1mg + 20mg + 10mg + 10mg + 8mg + 2.21mg + 400mg + 97.27mg", "50000ui"
  ],
  Capsulas:
  [
    "0.5mg", "1mg", "2mg", "4mg", "5mg", "15mg", "20mg", "25mg", "20mg + 10mg", "40mg",
    "50mg", "50mg + 12mg + 3mg", "100mg", "60mg + 50mg + 5mg", "6mg + 5mg + 3mg + 39mg + 61mg + 10mg",
    "125mg + 1mg", "150mg", "120mg + 25mg + 10mg + 20mg + 1.5mg", "200mg", "200mg + 0.4mg", "200mg + 10mg + 6mg + 3mg",
    "200mg + 40mg", "250mg", "200mg + 25mg + 25mg", "50mg + 0.2mg + 2ui + 50mg + 10mg + 2mg + 2mg + 50mg + 100mg + 55mg",
    "200mg + 80mg", "150mg + 150mg + 40mg", "50mg + 25ui + 50mg + 10mg + 2mg + 2mg + 50mg + 100mg + 50mg + 100mg + 55mg",
    "400mg", "4mg + 45mg + 45mg + 45mg + 45mg +  45mg + 45mg + 45mg + 45mg + 45mg + 50mg",
    "100mg + 90mg + 50mg + 10mg + 50mg + 10mg + 40mg + 100mg",
    "500mg", "11.5mg + 507mg", "550mg", "600mg + 125ui", "160 + 800mg", "875 + 125mg", "5mg + 1000mg",
    "50mg + 50mg + 1000mcg + 50mg + 400ui",
    "1667.67mg + 6.2mg + 85.7mg + 9.91mg + 4.05mg +5.48mg",
    "100mg + 100mg + 87.5mg + 15mg + 5mg + 5mg + 1000mcg + 400mcg + 70mcg + 50mcg + 5mcg",
    "3000ui + 10mcg + 60mg + 400 ui",
    "25000ui + 400 ui + 1.5mg + 1mg + 20mg + 10mg + 10mg + 8mg + 2.21mg + 400mg + 97.27mg", "50000ui"
  ],
  Gotas:
  [
    "15,000ui + 2mg + 0.125mg + 600ui + 12.5mg + 50mg + 10mg + 1.6mg + 4mg",
    "1mg + 2000ui + 35mg + 0.06mg + 1mg + 1mg + 400ui + 10mg + 25mg + 10mg",
    "0.5mg/100,000mg", "7.5mg/30ml", "8.55mg/ml", "15mg/ml", "15mg/0.6 ml",
    "40mg/ml", "400mg/10ml", "50mg/ml", "100mg/ml", "100m/ml"
  ],
  Aerosol:
  [
    "1500ui + 600ui + 10mg + 2mg + 12.5mg + 1.6mg + 0.125mg + 50mg + 4mg",
    "11.22g (0.37mg + 1g)", "0.025 %", "0.8g", "7.5mg/ml", "5mcg + 7.5mg + 0.2mg",
    "40mg/ml", "0.5mg + 50mcg + 5mg", "80mg + 2mg + 1mg", "261 mcg", "400mg"
  ],
  Ovulos:
  [
   "25mg", "36mg", "0.338mg + 40mg", "75mg + 0.325mg", "90mg", "100mg", "125mg", "300mg",
   "400mg", "500mg", "100,000ui", "0.25mg + 100,000ui", "0.325mg + 100,000ui", "0.25mg + 100,000ui + 500mg",
   "500mg + 100,000ui + 5mg + 20mg + 10mg"
  ],
  "Ampollas Bebibles":
    [
      "3mg/10ml", "5g/10ml", "5g + 5g/10ml", "5g + 13mg + 1.3g", "4.5mg + 1.7mg + 20mg + 3mg + 18mg",
      "4.5mg + 6mg + 18mcg + 60mg","5mg + 5mcg + 50mg + 5mg + 30mg + 50mg + 50mg",
      "50mg + 120mg + 25mg + 25mg + 12mg + 25mg + 20mg",
      "50mg + 50mg + 50mg + 30mcg + 50mg + 50mg + 30mg", "500mcg + 1000mcg + 150mg + 2.7mg/12ml"
    ],
  "Ampollas Inyectables":
  [
    "1g/2ml", "4mg/2ml", "4mg/ml", "3mg + 3mg/1ml", "5mg + 0.1mg + 0.5mg + 2mcg/2ml", "10mg/ml", "20mg/2ml",
    "30mg/2ml", "75mg/3ml", "50mg/2ml", "60mg/2ml", "80mg/2ml", "100mg/2ml", "160mg/2ml", "100mg + 15mg/2ml",
    "250mg/2ml", "100mg + 100mg/3ml", "500mg/2ml", "500mg", 
    "250mg + 250mg + 2500mcg/5ml", "1000mcg + 200 mcg + 50mg/3ml",
    "100mg + 100mg + 5,000mcg", "100mg + 100mg + 5000mcg + 10mg",
    "75mg + 125mg + 50mg + 5,000mcg", "10,000mcg + 100mg + 100mg/3ml",
    "25,000mg", "100mg + 4mg + 100mg + 5000mcg + 10mg + 25,000", "800,000 ui", "1,200,000ui", "4000,000 ui"
  ],
  Jarabes:
  [
    "7mg + 3.5mg + 8.5mg + 0.0035mg + 17.5mg + 26.5mg + 5.36mg + 7mg + 142.8mg + 1400ui + 2ui",
    "2mg", "12mg/5ml", "50mg/20mg", "2.5mg", "15mg/5ml", "3g + 0.18g", "20mg/5ml", "27mg/5ml", "30mg/5ml",
    "1mg + 5ml", "30mg/5ml", "7.5mg + 0.005mg/5ml", "4mg + 5ml", "5mg + 0.25mg + 5ml", "5mg + 0.25 + 5ml", "3mg + 10mg",
    "10mg + 5ml", "12.5mg + 5ml", "12.5mg + 5ml",
    "15mg + 5ml", "125mg/5ml", "27mg + 5ml", "0.5g + 35mg + 2.5ml", "200mg/5ml",
    "30mg + 15ml", "250mg/5ml", "50mg + 5ml", "50mg + 2mg + 5ml",
    "50mg + 2.5mg + 1mg + 5mg", "10mg + 1.5mg + 1.8mg + 20mg + 2mg + 6mg + 10mg + 15ml",
    "50mg + 20mg", "1mg + 2.5mg + 7.5mg + 60mg", "0.88ml + 100ml",
    "0.88ml + 100ml", "120mg + 5ml","31mg + 3mg + 0.03% + 100ml",
    "100mg + 200mg/5ml", "250mg", "150mg + 150mcg + 10mg + 15mcg", "30mg + 300mcg",
    "30mg + 300mcg", "600mg + 42.9mg + 5ml"
  ],
  Cremas:
  [
    "0.25% + 2%", "0.02%", "0.05%", "0.1%", "0.75%", "1%", "1% + 0.1%",
    "1% + 0.5% + 0.04%", "2%", "2% + 4% + 0.025% + 4% + 2% + 0.025%",
    "0.010g + 0.100g + 0.020g", "0.1gr + 0.1gr", "0.5g", "15% + 17.75% + 20%", "30% + 24%", "1g", "16% + 1g",
    "1.16% + 1g", "1gr + 0.1gr", "0.606g + 0.065gr + 0.5g", "0.5mg + 1mg", "2g",
    "5g", "5gr", "10gr + 0.6ugr", "0.64gr + 10gr",
    "0.5mg + 1g + 10mg", "1mg + 0.5mg + 10mg", "15g", "20g", "30gr", "90gr", "0.01g + 0.01g + 100ml", "5% + 100g",
    "6g + 3g + 10g + ug + 2g + 0.3g + 6g + 10g + 100g", "1000ui", "1000ui",
    "5gr + 10000ui + 1g", "10mg + 20mg + 25mg + 10000ui + 10mg",
    "8% + 10000ui + 2000ui", "100000g + 4g", "100000ui + 40000ui + 15gr"
  ],
  "Goteros Oftalmicos":
  [
    "0.025%", "0.05%", "0.1%", "0.25%", "0.5%", "0.5% + 0.1%", "10%", "100%", "0.15g + 1.5g + 0.01g",
    "2.5mg", "5mg + 2%", "3mg + 3mg", "5mg + 10mg + 10000ui + 1ml"
  ]
]

ni.sb.locations = [
  "E1-1", "E1-2", "E1-3", "E1-4", "E1-5", "E1-6", "E1-A1",
  "E2-1", "E2-2", "E2-3", "E2-4", "E2-5", "E2-6",
  "E3-1", "E3-2", "E3-3", "E3-4", "E3-5", "E3-6", "E3-A1",
  "E4-1", "E4-2", "E4-3",
  "E5-1", "E5-2", "E5-3", "E5-4",
  "V1-1", "V1-2", "V1-3",
  "V2-1", "V2-2", "V2-3",
  "V3-1", "V3-2", "V3-3",
  "V4-1", "V4-2", "V4-3",
  "V5-1", "V5-2", "V5-3", "V5-4",
  "V6-1", "V6-2", "V6-3", "V6-4", "V6-5", "V6-6",
  "V7-1", "V7-2"
]

grails.databinding.dateFormats = ["yyyy-MM-dd", "dd-MM-yyyy"]
ni.sb.minDateToSale = 60

grails.mime.types = [ html: ["text/html","application/xhtml+xml"],
                      xml: ["text/xml", "application/xml"],
                      text: "text-plain",
                      js: "text/javascript",
                      rss: "application/rss+xml",
                      atom: "application/atom+xml",
                      css: "text/css",
                      csv: "text/csv",
                      pdf: "application/pdf",
                      rtf: "application/rtf",
                      excel: "application/vnd.ms-excel",
                      ods: "application/vnd.oasis.opendocument.spreadsheet",
                      all: "*/*",
                      json: ["application/json","text/json"],
                      form: "application/x-www-form-urlencoded",
                      multipartForm: "multipart/form-data"
                    ]

grails {
   mail {
     host = "smtp.gmail.com"
     port = 465
     username = "b2goldapps@gmail.com"
     password = "tritonB2gold0"
     props = ["mail.smtp.auth":"true",
              "mail.smtp.socketFactory.port":"465",
              "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
              "mail.smtp.socketFactory.fallback":"false"]

} }