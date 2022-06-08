package com.namoonhee.plogging.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
  @Override
  protected PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication(
      "kangmb90@gmail.com", "vcfsbnfxjoejjzff");
  }
}