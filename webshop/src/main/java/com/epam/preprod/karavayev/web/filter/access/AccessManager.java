package com.epam.preprod.karavayev.web.filter.access;

import com.epam.preprod.karavayev.dto.Constraint;
import org.apache.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class AccessManager {

    private static final Logger LOGGER = Logger.getLogger(AccessManager.class);

    private Map<Pattern, List<String>> patternConstrains;

    public AccessManager(List<Constraint> constraints) {
        LOGGER.debug(constraints);
        constraints.sort((s1, s2) -> s2.getPattern().length() - s1.getPattern().length());
        LOGGER.debug(constraints);
        patternConstrains = new LinkedHashMap<>();
        for (Constraint constraint : constraints) {
            patternConstrains.put(Pattern.compile(constraint.getPattern()), constraint.getRoles());
        }
    }

    public boolean isExistPermissionForURI(String uri) {
        return patternConstrains.keySet().stream()
                .anyMatch(pattern -> pattern.matcher(uri).matches());
    }

    public boolean hasUserRolePermissionForURI(String role, String uri) {
        return patternConstrains.entrySet().stream()
                .anyMatch(entry -> entry.getKey().matcher(uri).matches() && entry.getValue().contains(role));
    }

}
