package lab8;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailTest {

  static String pwd = "Lv200014";

  public static void main(String[] args)
    throws MessagingException, IOException {
    // read properties
    Properties props = new Properties();
    try (InputStream in = Files.newInputStream(Paths.get("mail.properties"))) {
      props.load(in);
    }

    // read message info
    List<String> lines = Files.readAllLines(
      Paths.get("message.txt"),
      StandardCharsets.UTF_8
    );

    String from = lines.get(0);
    String to = lines.get(1);
    String subject = lines.get(2);

    StringBuilder builder = new StringBuilder();
    for (int i = 3; i < lines.size(); i++) {
      builder.append(lines.get(i));
      builder.append("\n");
    }

    // read password for your email account
    // System.out.println("Password: ");
    // Scanner scanner = new Scanner(System.in);
    // String password = scanner.next();

    // Session mailSession = Session.getDefaultInstance(
    //   props,
    //   new Authenticator() {
    //     public PasswordAuthentication getPasswordAuthentication() {
    //       return new PasswordAuthentication(
    //         "11710420@mail.sustech.edu.cn",
    //         "Lv200014"
    //       );
    //     }
    //   }
    // );
    Session mailSession = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(mailSession);
    // TODO 1: check the MimeMessage API to figure out how to set the sender, receiver, subject, and email body
    // message.setFrom(new InternetAddress(from, "Lvyue", "UTF-8"));
    message.setFrom(new InternetAddress(from));
    message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
    message.setSubject(subject, "UTF-8");
    message.setText(builder.toString());
    // TODO 2: check the Session API to figure out how to connect to the mail server and send the message
    Transport trans = mailSession.getTransport();
    trans.connect(null, pwd);
    trans.sendMessage(message, message.getAllRecipients());
    System.out.println("Sent message successfully....");
  }
}
