package de.djreedoo.ultron.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Email {

    private static List<Email> emails = Lists.newArrayList();

    public static List<Email> getEmails() {
        return emails;
    }

    public static void addEmail(Email email) {
        emails.add(email);
    }

    public static Email getEmail(UUID uuid) {
        for(Email email : emails) {
            if(email.uuid.toString().equalsIgnoreCase(uuid.toString())) return email;
        }
        return null;
    }

    public static Email getEmail(String mail) {
        for(Email email : emails) {
            if(email.domain.equalsIgnoreCase(mail)) return email;
        }
        return null;
    }

    private UUID uuid;

    private String domain;

    private HashMap<String, String> inbox;

    public Email(UUID uuid, String domain) {
        this.uuid = uuid;
        this.domain = domain;
        this.inbox = Maps.newHashMap();
    }

    public boolean removeInboxMessage(String from) {
        for(String msg : this.inbox.keySet()) {
            if(this.inbox.get(msg).equalsIgnoreCase(from)) {
                inbox.remove(msg);
                return true;
            }
        }
        return false;
    }

    public UUID getUuid() {
        return uuid;
    }

    public HashMap<String, String> getInbox() {
        return inbox;
    }

    public void setInbox(HashMap<String, String> inbox) {
        this.inbox = inbox;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
