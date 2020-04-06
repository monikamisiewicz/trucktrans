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


    @org.junit.Test(expected = IllegalArgumentException.class)
    public void shouldThrowException_whenSaveNull() {
        // when
        orderRepository.save(null);
    }
}