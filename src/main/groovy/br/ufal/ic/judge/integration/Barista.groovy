package br.ufal.ic.judge.integration

import br.ufal.ic.judge.integration.domain.Drink
import br.ufal.ic.judge.integration.domain.OrderItem
import org.apache.commons.logging.LogFactory
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.stereotype.Component

import java.util.concurrent.atomic.AtomicInteger

@Component
class Barista {

    def static logger = LogFactory.getLog(Application)

    Long hotDrinkDelay = 5000
    Long coldDrinkDelay = 1000

    AtomicInteger hotDrinkCounter = new AtomicInteger()
    AtomicInteger coldDrinkCounter = new AtomicInteger()


    @ServiceActivator(inputChannel="hotDrinkBarista", outputChannel="preparedDrinks")
    def prepareHotDrink(OrderItem orderItem) {
        try {
            Thread.sleep(5000)

            logger.info "${Thread.currentThread().getName()}" +
                    " prepared hot drink #${hotDrinkCounter.incrementAndGet()} for order #" +
                    "$orderItem.orderNumber : $orderItem"

            return new Drink(
                    orderNumber: orderItem.orderNumber,
                    drinkType: orderItem.drinkType,
                    iced: orderItem.iced,
                    shots: orderItem.shots
            );

        } catch (InterruptedException e) {
            e.printStackTrace()
            Thread.currentThread().interrupt();
            return null;
        }
    }

    @ServiceActivator(inputChannel="coldDrinkBarista", outputChannel="preparedDrinks")
    public Drink prepareColdDrink(OrderItem orderItem) {
        try {
            Thread.sleep(1000)

            logger.info "${Thread.currentThread().getName()}" +
                    " prepared cold drink #${coldDrinkCounter.incrementAndGet()} for order #" +
                    "$orderItem.orderNumber : $orderItem"

            return new Drink(
                    orderNumber: orderItem.orderNumber,
                    drinkType: orderItem.drinkType,
                    iced: orderItem.iced,
                    shots: orderItem.shots
            );

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }


}
