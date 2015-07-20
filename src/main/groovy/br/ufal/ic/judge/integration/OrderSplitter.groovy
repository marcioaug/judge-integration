package br.ufal.ic.judge.integration

import br.ufal.ic.judge.integration.domain.Order
import org.springframework.integration.annotation.MessageEndpoint
import org.springframework.integration.annotation.Splitter

@MessageEndpoint
class OrderSplitter {

    @Splitter(inputChannel = "orders", outputChannel = "drinks")
    static def split(Order order) {
        order.orderItems
    }
}
