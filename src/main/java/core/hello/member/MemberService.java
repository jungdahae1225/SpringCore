package core.hello.member;

/**
 * 회원 서비스
 * 1. 회원가입
 * 2. 회원조회
 * **/

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
