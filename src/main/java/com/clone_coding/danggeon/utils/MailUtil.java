package com.clone_coding.danggeon.utils;

import com.clone_coding.danggeon.models.User;
import com.google.common.html.HtmlEscapers;
import org.apache.commons.mail.HtmlEmail;

//메일을 보낼 클래스
public class MailUtil {
    public void sendMail(User user) throws Exception {
        //Mail Server 설정
        String charSet = "utf-8";
        String hostSMTP = "smtp.naver.com"; //SMTP 서버명
        String hostSMTPid = "epfvkdlxj"; //naver id
        String hostSMTPpw = "Rhcqkfka11!";

        //보내는 사람
        String fromEmail = "epfvkdlxj@naver.com";
        String fromName = "Danggeon";

        String subject = ""; //mail title
        String msg = ""; //mail contents

        subject = "[DANGGEON] 임시 비밀번호 발급 안내";
        msg += "<div align = 'left'>";
        msg += "<h3>";
        msg += user.getUsername() + "님의 임시 비밀번호입니다. <br> 비밀번호를 변경하여 사용하세요 </h3>";
        msg += "<p>임시 비밀번호 : ";
        msg += user.getPassword() + "</p></div>";

        //email 전송
        String mailRecipient = user.getEmail(); //받는 사람 이메일 주소
        try {
            //객체 선언
            HtmlEmail mail = new HtmlEmail();
            mail.setDebug(true);
            mail.setCharset(charSet);
            mail.setSSLOnConnect(true); //SSL 사용
            mail.setHostName(hostSMTP);
            mail.setSmtpPort(587); //SMTP 포트 번호
            mail.setAuthentication(hostSMTPid, hostSMTPpw);
            mail.setStartTLSEnabled(true); //TLS 사용
            mail.addTo(mailRecipient, charSet);
            mail.setFrom(fromEmail, fromName, charSet);
            mail.setSubject(subject);
            mail.setHtmlMsg(msg);
            mail.send();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
