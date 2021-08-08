package core.hello.order;

import core.hello.discount.DiscountPolicy;
import core.hello.discount.FixDiscountPolicy;
import core.hello.member.Member;
import core.hello.member.MemberRepository;
import core.hello.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice); //단일책임원칙을 잘 수행함

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
