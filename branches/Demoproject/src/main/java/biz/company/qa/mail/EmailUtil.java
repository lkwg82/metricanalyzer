/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * util class for dealing with emails
 * 
 
 */
public class EmailUtil {

    private final static Logger log = LoggerFactory.getLogger(EmailUtil.class);

    /**
     * converts Message objects to our {@link Email} entity
     * <p/>
     * there are some tricky issues with mime types during conversion
     */
    public List<Email> convertMessagesToEmails(final Message[] messages) throws MessagingException, IOException {
        List<Email> emails = new ArrayList<Email>();

        // workaround for ClassNotFoundException, run maven with -Djavax.activation.debug=true to see the problem
        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());

        for (Message message : messages) {
            Email mail = new Email();

            mail.setSubject(message.getSubject());

            // we could have more than one recipient
            for (javax.mail.Address address : message.getRecipients(RecipientType.TO)) {
                mail.getRecipient().add(address.toString());
            }

            if (message.isMimeType("text/*")) {
                mail.setPlaintext(message.getContent().toString());

                mail.setMessageNumber(message.getMessageNumber());
                emails.add(mail);
            } else if (message.isMimeType("multipart/*")) {
                Multipart content = (Multipart) message.getContent();

                for (int i = 0; i < content.getCount(); i++) {
                    BodyPart part = content.getBodyPart(i);
                    if (part.isMimeType("text/plain")) {
                        mail.setPlaintext(part.getContent().toString());
                    } else if (part.isMimeType("text/html")) {
                        mail.setHtml(part.getContent().toString());
                    }
                }

                mail.setMessageNumber(message.getMessageNumber());
                emails.add(mail);
            } else {
                log.error("problems in detecting the mime type : " + message.getHeader("Content-Type"));
            }
        }

        return emails;
    }
}
