package com.ssafy.api.service;

import com.ssafy.api.request.UserIdEmailReq;
import com.ssafy.api.response.MailSenderRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import com.ssafy.db.repository.UserRepositorySupport;

import javax.mail.internet.MimeMessage;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRepositorySupport userRepositorySupport;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();
		user.setId(userRegisterInfo.getId());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
		user.setName(userRegisterInfo.getName());
		user.setEmail(userRegisterInfo.getEmail());
		user.setPhone(userRegisterInfo.getPhone());
		user.setNickname(userRegisterInfo.getNickname());
		user.setBirthDt(userRegisterInfo.getBirthDt());
		user.setRole(userRegisterInfo.getRole());

		return userRepository.save(user);
	}

	@Override
	public User getUserByUserId(Long userId) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		User user = userRepositorySupport.findUserByUserId(userId).get();
		return user;
	}

	@Override
	public User getUserById(String id) {
		// 디비에 유저 정보 조회 (Id 를 통한 조회).
		User user = userRepositorySupport.findUserById(id).get();
		return user;
	}

	@Autowired
	JavaMailSender mailSender;
	@Value("${spring.mail.username}")
	String sendFrom;

	@Autowired
	Environment env;
	@Override
	public boolean sendMail(UserIdEmailReq userIE) {
		String sendTo = userIE.getMail();
		String tempPwd = getTempPassword();
		String mailTitle = "[Coding With Me] 임시 비밀번호 안내 메일 입니다.";
		String mailContent = "안녕하세요. Coding With Me 입니다.\n회원님의 변경된 임시 비밀번호는 "
				+ tempPwd
				+ " 입니다.\n보안을 위해 로그인 후 비밀번호를 변경해주세요.";



		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				final MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true,"UTF-8");

				message.setTo(sendTo);
				message.setFrom(sendFrom);	//env.getProperty("spring.mail.username")
				message.setSubject(mailTitle);
				message.setText(mailContent, true); //ture : html 형식 사용

//				//Mail에 img 삽입
//				ClassPathResource resource = new ClassPathResource("img 주소/img 이름.png");
//				message.addInline("img", resource.getFile());
			}
		};

		try{
			mailSender.send(preparator);
		} catch (MailException e){
			return false;
		}
		return true;
	}

	public String getTempPassword() {

		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
				'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

		String str = "";

		// 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
		int idx = 0;
		for (int i = 0; i < 10; i++) {
			idx = (int) (charSet.length * Math.random());
			str += charSet[idx];
		}

		System.out.println("user 변경된 비밀번호 : " + str);
		return str;
	}

}
