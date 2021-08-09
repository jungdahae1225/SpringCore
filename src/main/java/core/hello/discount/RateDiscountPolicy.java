package core.hello.discount;

import core.hello.member.Grade;
import core.hello.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100; //vip회원일 경우 10% 할인율 적용
        } else {
            return 0;
        }
    }
}
