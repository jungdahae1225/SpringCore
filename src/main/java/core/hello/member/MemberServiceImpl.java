package core.hello.member;

public class MemberServiceImpl implements MemberService{

    /** 추상화객체에 의존하지 않기 위해
     * 이전코드:
     * private final MemberRepository memberRepository = new MemoryMemberRepository();
     * => 저장소를 이어주는 역할도 AppConfig에 위임한다.
     * => 객체를 생성하고 연결하는 역할을 온전히 AppConfig가 담당하게 함으로써 해당 객체는 실행만을 담당한다.
     */

    //회원가입과 회원조회를 하기 위해서는 MemberRepository 저장소를 끌어와야 한다.
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
