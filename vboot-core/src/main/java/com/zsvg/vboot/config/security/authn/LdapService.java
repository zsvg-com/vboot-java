package com.zsvg.vboot.config.security.authn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

import javax.naming.directory.DirContext;

@Service
public class LdapService {

    @Autowired
    private LdapTemplate ldapTemplate;

    @Value("${ldap.domainName}")
    private String ldapDomainName;

    @Value("${ldap.base}")
    private String ldapBaseDn;

    public boolean authenticate(String userName, String password) {
        //String userDomainName = getDnForUser(userName);
        String userDomainName = String.format(ldapDomainName, userName);
        DirContext ctx = null;
        try {
            ctx = ldapTemplate.getContextSource().getContext(userDomainName, password);
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            LdapUtils.closeContext(ctx);
        }
        return false;
    }

}