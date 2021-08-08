package core.hello.order;
/**
 * 주문 생성 로직
 * 1. 회원 id
 * 2. 상품명
 * 3. 상품 가격
 * 
 * return 주문 결과 반환
 * */

public interface OrderService {
    Order createOrder(Long memberId,String itemName,int itemPrice);
}
