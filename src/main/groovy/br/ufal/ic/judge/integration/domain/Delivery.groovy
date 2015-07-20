package br.ufal.ic.judge.integration.domain

class Delivery implements Serializable {

    List<Drink> deliveredDrinks
    Integer orderNumber

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("-----------------------" + "\n")

        buffer.append("Order #$orderNumber \n")

        deliveredDrinks.each {
            buffer.append("${it.drinkType.toString()} x $it.shots \n")
        }

        buffer.append("----------------------- \n")

        buffer.toString()
    }

}
