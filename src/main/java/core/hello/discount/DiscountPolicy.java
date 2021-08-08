package core.hello.discount;

import core.hello.member.Member;

public interface DiscountPolicy {

    /***
     * @return 은 할인 금액
     */
    int discount(Member member, int price);

}
