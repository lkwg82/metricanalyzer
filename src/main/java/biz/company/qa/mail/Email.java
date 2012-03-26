/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * value object for the entity email
 * 
 
 
 */
public class Email {

    private String subject;
    private final List<String> recipient = new ArrayList<String>();
    private String plaintext;
    private String html;
    private Integer messageNumber;

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(final String plaintext) {
        this.plaintext = plaintext;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(final String html) {
        this.html = html;
    }

    public Integer getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(final Integer messageNumber) {
        this.messageNumber = messageNumber;
    }

    public void setSubject(final String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    /**
     * @return the recipient
     */
    public List<String> getRecipient() {
        return recipient;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((html == null) ? 0 : html.hashCode());
        result = prime * result + ((messageNumber == null) ? 0 : messageNumber.hashCode());
        result = prime * result + ((plaintext == null) ? 0 : plaintext.hashCode());
        result = prime * result + ((recipient == null) ? 0 : recipient.hashCode());
        result = prime * result + ((subject == null) ? 0 : subject.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Email other = (Email) obj;
        if (html == null) {
            if (other.html != null) {
                return false;
            }
        } else if (!html.equals(other.html)) {
            return false;
        }
        if (messageNumber == null) {
            if (other.messageNumber != null) {
                return false;
            }
        } else if (!messageNumber.equals(other.messageNumber)) {
            return false;
        }
        if (plaintext == null) {
            if (other.plaintext != null) {
                return false;
            }
        } else if (!plaintext.equals(other.plaintext)) {
            return false;
        }
        if (recipient == null) {
            if (other.recipient != null) {
                return false;
            }
        } else if (!recipient.equals(other.recipient)) {
            return false;
        }
        if (subject == null) {
            if (other.subject != null) {
                return false;
            }
        } else if (!subject.equals(other.subject)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Email [subject=" + subject + ", recipient=" + recipient + ", plaintext=" + plaintext + ", html=" + html
                + ", messageNumber=" + messageNumber + "]";
    }
}
