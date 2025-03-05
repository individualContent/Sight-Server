package com.temp.sight.jwt.dto;

import com.temp.sight.jwt.Authority;
import com.temp.sight.jwt.Member;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
@AllArgsConstructor
@Builder
public class MemberRequestDto {
    private String email;
    private String password;

    //requestdto를 member entity로 변환 (패스워드는 인코더를 사용해서 암호화 한 후에 저장)
    public Member toMember(org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_ADMIN)
                .build();
    }

    //email을 사용자 이름으로, password를 인증에 사용할 자격 증명으로 설정한 인증 객체 생성
    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
