package br.ufal.ic.judge.integration

import br.ufal.ic.judge.integration.domain.DrinkType
import br.ufal.ic.judge.integration.domain.Order
import br.ufal.ic.judge.integration.domain.OrderItem
import org.apache.commons.logging.LogFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.AbstractApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.integration.annotation.IntegrationComponentScan

@Configuration
@SpringBootApplication
@IntegrationComponentScan
class Application {

    def static log = LogFactory.getLog(Application)

    def static main(args) {
        SpringApplication.run(Application.class, args)
        log.debug("Iniciando a aplicação.")

        AbstractApplicationContext context = new ClassPathXmlApplicationContext(
                "/META-INF/spring/integration/cafedemo.xml",
                Application.class
        )

        Cafe cafe = context.getBean("cafe", Cafe)

        (1..100).each {
            println "cliente realizando o pedido #$it"
            def order = new Order(number: it)
            order.addItem(DrinkType.LATTE, 2, false)
            order.addItem(DrinkType.MOCHA, 3, true)
            cafe.placeOrder(order)
        }

        context.close()
    }

}
