package br.ufal.ic.judge.integration

import br.ufal.ic.judge.integration.domain.OrderItem
import org.springframework.integration.annotation.MessageEndpoint
import org.springframework.integration.annotation.Router

@MessageEndpoint
public class DrinkRouter {

    @Router(inputChannel = "drinks")
    static def resolveOrderItemChannel(OrderItem orderItem) {
        return orderItem.iced ? "coldDrinks" : "hotDrinks"
    }

}