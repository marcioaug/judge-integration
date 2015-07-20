package br.ufal.ic.judge.integration

import br.ufal.ic.judge.integration.domain.Delivery
import br.ufal.ic.judge.integration.domain.Drink
import org.apache.commons.logging.LogFactory
import org.springframework.integration.annotation.Aggregator
import org.springframework.integration.annotation.CorrelationStrategy
import org.springframework.integration.annotation.MessageEndpoint

@MessageEndpoint
class Waiter {

    static def logger = LogFactory.getLog(Waiter)

    @Aggregator(inputChannel = "preparedDrinks", outputChannel = "deliveries")
    static Delivery prepareDelivery(List<Drink> drinks) {
        logger.debug("recebeu os drinks do pedido #${drinks.first().orderNumber}")
        new Delivery(deliveredDrinks: drinks, orderNumber: drinks.first().orderNumber)
    }

    @CorrelationStrategy
    static def correlateByOrderNumber(Drink drink) {
        drink.orderNumber
    }
}
