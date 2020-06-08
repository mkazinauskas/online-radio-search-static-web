package com.modzo.ors.web.components;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CommonComponents {

    private final List<CommonComponent> components;

    public CommonComponents(List<CommonComponent> components) {
        this.components = components;
    }

    public Map<String, Object> load() {
        return components.stream()
                .collect(Collectors.toMap(component -> component.type().getName(), CommonComponent::data));
    }
}
