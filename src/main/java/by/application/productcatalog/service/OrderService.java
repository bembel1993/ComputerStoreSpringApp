//package by.application.productcatalog.service.old;
//
//import by.application.productcatalog.model.old.*;
//import by.application.productcatalog.repository.old.*;
//import by.store.model.*;
//import by.application.productcatalog.model.old.dto.BasketDto;
//import by.application.productcatalog.model.old.dto.OrderDto;
//import by.application.productcatalog.model.old.dto.OrderListDto;
//import by.application.productcatalog.model.old.enums.OrderStatus;
//import by.store.repository.*;
//import org.springframework.stereotype.Service;
//import lombok.RequiredArgsConstructor;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.Date;
//
//@Service
//@RequiredArgsConstructor
//public class OrderService {
//
//    private final OrderRepository orderRepository;
//    private final OrderListRepository orderListRepository;
//    private final BasketRepository basketRepository;
//    private final ProductRepository productRepository;
//    private final UserRepository userRepository;
//
//    //список всех заказов для админа
//    public List<OrderDto> getAllOrders(){
//        Iterable<Order> iter = orderRepository.findAll();
//        List<OrderDto> orderList1 = new ArrayList<>();
//        for(Order order: iter){
//            String status = OrderStatus.getById(order.getStatus());
//            OrderDto orderDto = new OrderDto(order.getId(), order.getSumm(), order.getData(), status);
//            orderList1.add(orderDto);
//        }
//        return orderList1;
//    }
//
//    //список заказов пользователя
//    public List<OrderDto> getOrdersListFromUser(Integer userId){
//        Iterable<Order> iter = orderRepository.findAllByUserId(userId);
//        List<OrderDto> orderList = new ArrayList<>();
//        for(Order order: iter){
//            String status = OrderStatus.getById(order.getStatus());
//            OrderDto orderDto = new OrderDto(order.getId(), order.getSumm(), order.getData(), status);
//            orderList.add(orderDto);
//        }
//        return orderList;
//    }
//
//    //список товаров в заказе
//    public List<OrderListDto> getListFromOrder(Integer orderId){
//        Iterable<OrderList> iter = orderListRepository.findAllByOrderId(orderId);
//        List<OrderListDto> orderList = new ArrayList<>();
//        for(OrderList ol: iter){
//            Product pr = productRepository.findById(ol.getProductId()).get();
//            OrderListDto dto = new OrderListDto(pr.getName(), ol.getAmount(), ol.getPrice());
//            orderList.add(dto);
//        }
//        return orderList;
//    }
//
//    //список товаров в корзине заказа
//    public List<BasketDto> getBasketListFromUserId(Integer userId){
//        Iterable<Basket> iter = basketRepository.findAllByUserId(userId);
//        List<BasketDto> basketList = new ArrayList<>();
//        for(Basket b: iter){
//            Product pr = productRepository.findById(b.getProductId()).get();
//            BasketDto basketDto = new BasketDto(pr.getName(), b.getAmount(), b.getSumm(), b.getId());
//            basketList.add(basketDto);
//        }
//        return basketList;
//    }
//
//    //общая сумма заказа
//    public Double getBasketSumm(List<BasketDto> list){
//        Double resultSum = 0.0;
//        for(BasketDto bDto: list){
//            resultSum += bDto.getSumm();
//        }
//        return round(resultSum);
//    }
//
//    //удалить товар из корзины - увеличить количество на складе
//    public void delProductFromBasket(Integer id){
//        Optional<Basket> b = basketRepository.findById(id);
//        if(b.isPresent()){
//            Basket bb = b.get();
//            int count = bb.getAmount();
//            int productId = bb.getProductId();
//            Product pr = productRepository.findById(productId).get();
//            count+=pr.getRemainder();
//            pr.setRemainder(count);
//            productRepository.save(pr);
//        }
//        basketRepository.deleteById(id);
//    }
//
//    //сформировать заказ
//    public void createOrder(User user){
//        List<BasketDto> basketList = getBasketListFromUserId(user.getId());
//
//        String data = (new Date()).toString();
//        int orderId1 = (orderRepository.findMaxId() == null) ? 1: orderRepository.findMaxId()+1;
//        Order order = new Order(orderId1, user.getId(), getOrderSum(basketList), data, OrderStatus.CREATE.getId());
//        Order xOrder = orderRepository.save(order);
//        int x = xOrder.getId();
//
//        for(BasketDto b: basketList){
//            int nextId = (orderListRepository.findMaxId() == null) ? 1: orderListRepository.findMaxId()+1;
//            OrderList list = new OrderList(nextId, x, b.getId(), b.getAmount(), b.getSumm());
//            orderListRepository.save(list);
//        }
//    }
//
//    //очистить корзину
//    public void deleteFromBasket(Integer userId){
//        List<BasketDto> basketList = getBasketListFromUserId(userId);
//        for(BasketDto bDto: basketList){
//            delProductFromBasket(bDto.getId());
//        }
//    }
//
//    public void deleteFromBasketFromOrder(Integer userId){
//        List<BasketDto> basketList = getBasketListFromUserId(userId);
//        for(BasketDto bDto: basketList){
//            basketRepository.deleteById(bDto.getId());
//        }
//    }
//
//    public Double round(Double value){
//        BigDecimal result = new BigDecimal(value);
//        result = result.setScale(2, RoundingMode.UP);
//        return result.doubleValue();
//    }
//
//    public boolean saveUserInfo(Integer userId, String name, String login, String pass, String mail, String info){
//        Optional<User> userOptional = userRepository.findById(userId);
//        if(userOptional.isPresent()){
//            User user = userOptional.get();
//            if(name != null && !name.equals("")){
//                user.setName(name);
//            }
//            if(login != null && !login.equals("")){
//                user.setLogin(login);
//            }
//            if(pass != null && !pass.equals("")){
//                user.setPassword(pass);
//            }
//            if(mail != null && !info.equals("")){
//                user.setInfo(info);
//            }
//            userRepository.save(user);
//            return true;
//        }
//        return false;
//    }
//
//    public void changeStatus(Integer orderId, Integer statusId){
//        Optional<Order> optionalOrder = orderRepository.findById(orderId);
//        if(optionalOrder.isPresent()){
//            Order order = optionalOrder.get();
//            order.setStatus(statusId);
//            orderRepository.save(order);
//        }
//    }
//
//    private Double getOrderSum(List<BasketDto> list){
//        Double resultSumm = 0.0;
//        for(BasketDto b: list){
//            resultSumm += b.getSumm();
//        }
//        BigDecimal result = new BigDecimal(resultSumm);
//        result = result.setScale(2, RoundingMode.UP);
//        return result.doubleValue();
//    }
//
//}
