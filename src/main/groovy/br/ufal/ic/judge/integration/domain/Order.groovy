package br.ufal.ic.judge.integration.domain

class Order {

    List<OrderItem> items = []
    Integer number

    def addItem(DrinkType drinkType, shots = 1, iced = false) {
        items.add(new OrderItem(orderNumber: number, drinkType: drinkType, shots: shots, iced: iced))
        this
    }

}
