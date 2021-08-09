package core.hello.order;

import core.hello.discount.DiscountPolicy;
import core.hello.member.Member;
import core.hello.member.MemberRepository;

public class OrderServiceImpl implements OrderService{
    /**
     * 1단계 온전히 java로 코딩하기 (할인 적용 정책에 따라 구현체 수동으로 바꿔끼우기)
     * =>구체 클래스도 의존하여 -> DIP위반 -> OCP위반
     *
     *  1. fix일 때 : private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
     *  2. Rate일 때 : private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
     *
     **/


    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice); //단일책임원칙을 잘 수행함

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
