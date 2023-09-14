package namkyu.and.mz.homeshopping.service;

import namkyu.and.mz.homeshopping.config.PrincipalDetails;
import namkyu.and.mz.homeshopping.constant.Role;
import namkyu.and.mz.homeshopping.dto.GoogleUserInfo;
import namkyu.and.mz.homeshopping.dto.OAuth2UserInfo;
import namkyu.and.mz.homeshopping.entity.Member;
import namkyu.and.mz.homeshopping.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getClientId();
        String providerId = oAuth2User.getAttribute("sub");
        String name = oAuth2User.getAttribute("email");
        String password = UUID.randomUUID().toString();
        String email = oAuth2User.getAttribute("email");
        String role = String.valueOf(Role.ROLE_USER);

        Member memberEntity = memberRepository.findByName(name);

        if(memberEntity == null){
            memberEntity = Member.builder()
                    .name(name)
                    .email(email)
                    .password(password)
                    .role(Role.ROLE_USER)
                    .provider(provider)
                    .providerId(providerId)
                    .build();

            memberRepository.save(memberEntity);

        }

        return new PrincipalDetails(memberEntity, oAuth2User.getAttributes());
    }
    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest,
                                         OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = null;

        userRequest.getClientRegistration().getRegistrationId().equals("google");
        oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());

        Optional<Member> userOptional = memberRepository.findByProviderAndProviderId(
                oAuth2UserInfo.getProvider(), oAuth2UserInfo.getProviderId());

        Member user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            user.setEmail(oAuth2UserInfo.getEmail());
            memberRepository.save(user);
        } else {
            user = Member.builder()
                    .name(oAuth2UserInfo.getProvider() + "_" +
                            oAuth2UserInfo.getProviderId())
                    .email(oAuth2UserInfo.getEmail())
                    .password(UUID.randomUUID().toString())
                    .role(Role.ROLE_USER)
                    .provider(oAuth2UserInfo.getProvider())
                    .providerId(oAuth2UserInfo.getProviderId())
                    .build();
            memberRepository.save(user);
        }
        return new PrincipalDetails(user, oAuth2User.getAttributes());
    }
}