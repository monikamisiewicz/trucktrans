package pl.coderslab.trucktrans.order;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.web.format.WebConversionService;
import pl.coderslab.trucktrans.model.Order;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

class OrderControllerTest {

    private OrderRepository orderRepository;

//    @Test
//    void shouldSaveOrder() {
//        //when
//        Order order = orderRepository.save(new Order());
//        //then
//        assertThat(new WebConversionService("dd-MM-yyyy").convert("01-03-2019",
//                java.time.LocalDate.class)).isEqualTo(java.time.LocalDate.of(2019, 3, 1));
//
//    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void shouldThrowException_whenSaveNull() {
        // when
        orderRepository.save(null);
    }

    @org.junit.Test
    public void shouldUpdateUser() {
        // given
        Order order = new Order();
        Order savedOrder =  orderRepository.save(order);
        // when
//        savedOrder.setDate("13-3-2019");
//        Order modifiedOrder = orderRepository.save(savedOrder);
        // then
//        Assert.assertThat(modifiedOrder.getDate(), is("name"));
    }

    @Test
    void getByDate() {
    }

    @Test
    void getByDateRange() {
    }
}