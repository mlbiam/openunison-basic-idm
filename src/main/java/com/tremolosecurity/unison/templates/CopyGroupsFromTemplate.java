package com.tremolosecurity.unison.templates;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.tremolosecurity.provisioning.core.ProvisioningException;
import com.tremolosecurity.provisioning.core.User;
import com.tremolosecurity.provisioning.core.WorkflowTask;
import com.tremolosecurity.provisioning.util.CustomTask;
import com.tremolosecurity.saml.Attribute;
import com.tremolosecurity.server.GlobalEntries;

/**
 * CopyGroupsFromTemplate
 */
public class CopyGroupsFromTemplate implements CustomTask {

    String target;

    @Override
    public boolean doTask(User user, Map<String, Object> request) throws ProvisioningException {
        
        String uidOfTemplate = (String) request.get("uid");
        System.out.println("UID of tempalte - " + uidOfTemplate);

        User userFromTemplate = GlobalEntries.getGlobalEntries().getConfigManager().getProvisioningEngine().getTarget(this.target).getProvider().findUser(uidOfTemplate, new HashSet<String>(), new HashMap<String,Object>());

        user.getGroups().addAll(userFromTemplate.getGroups());

        return true;
    }

    @Override
    public void init(WorkflowTask wf, Map<String, Attribute> cfg) throws ProvisioningException {
        target = cfg.get("target").getValues().get(0);

    }

    @Override
    public void reInit(WorkflowTask task) throws ProvisioningException {
        // TODO Auto-generated method stub

    }

    
}