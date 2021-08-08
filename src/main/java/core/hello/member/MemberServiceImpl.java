package core.hello.member;

public class MemberServiceImpl implements MemberService{

    //회원가입과 회원조회를 하기 위해서는 MemberRepository 저장소를 끌어와야 한다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
