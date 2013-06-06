package org.motechproject.care.reporting.processors;

import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.FlwGroup;
import org.motechproject.care.reporting.mapper.GenericMapper;
import org.motechproject.care.reporting.parser.GroupParser;
import org.motechproject.care.reporting.parser.ProviderParser;
import org.motechproject.care.reporting.service.CareService;
import org.motechproject.commcare.provider.sync.response.Group;
import org.motechproject.commcare.provider.sync.response.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProviderSyncProcessor {
    private GroupParser groupParser;
    private CareService careService;
    private GenericMapper genericMapper;
    private final ProviderParser providerParser;

    @Autowired
    public ProviderSyncProcessor(GroupParser groupParser, ProviderParser providerParser, CareService careService) {
        this.groupParser = groupParser;
        this.careService = careService;
        this.providerParser = providerParser;
        this.genericMapper = new GenericMapper();
    }

    public void processGroupSync(List<Group> groups) {
        List<FlwGroup> flwGroups = new ArrayList<>();
        for (Group group : groups) {
            Map<String, Object> parsedGroups = groupParser.parse(group);
            flwGroups.add(genericMapper.map(parsedGroups, FlwGroup.class));
        }
        careService.saveOrUpdateAll(flwGroups);
    }

    public void processProviderSync(List<Provider> providers) {
        List<Flw> flws = new ArrayList<>();
        Map<String, FlwGroup> flwGroups = new HashMap<>();
        for (Provider provider : providers) {
            Map<String, Object> parsedProvider = providerParser.parse(provider);
            Flw flw = genericMapper.map(parsedProvider, Flw.class);
            flw.setFlwGroups(new HashSet<>(getAssociatedFlwGroups(provider.getGroups(), flwGroups)));
            flws.add(flw);
        }
        careService.saveOrUpdateAll(flws);
    }

    private List<FlwGroup> getAssociatedFlwGroups(List<String> groups, Map<String, FlwGroup> existingFlwGroups) {
        ArrayList<FlwGroup> flwGroups = new ArrayList<>();
        if (groups == null)
            return flwGroups;
        for (String groupId : groups) {
            FlwGroup group;
            if (existingFlwGroups.containsKey(groupId)) {
                group = existingFlwGroups.get(groupId);
            } else {
                group = careService.getGroup(groupId);
                existingFlwGroups.put(groupId, group);
            }
            flwGroups.add(group);
        }
        return flwGroups;
    }
}
