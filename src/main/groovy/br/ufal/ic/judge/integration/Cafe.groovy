package br.ufal.ic.judge.integration

import br.ufal.ic.judge.integration.domain.Order
import org.springframework.integration.annotation.Gateway

interface Cafe {

    @Gateway(requestChannel = "orders")
    void placeOrder(Order order)

}