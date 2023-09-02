package com.trust.gestion.services.resources;

import com.trust.gestion.enums.ActionTitle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActionFinder {
    private String action;

    public String getAction(ActionTitle actionTitle) {
        switch (actionTitle){
            case OWNER_CREATE -> this.action = ActionTitle.OWNER_CREATE.getValue();
            case OWNER_UPDATE -> this.action = ActionTitle.OWNER_UPDATE.getValue();
            case OWNER_DELETE -> this.action = ActionTitle.OWNER_DELETE.getValue();
            case BUILDING_CREATE -> this.action = ActionTitle.BUILDING_CREATE.getValue();
            case BUILDING_UPDATE -> this.action = ActionTitle.BUILDING_UPDATE.getValue();
            case BUILDING_DELETE -> this.action = ActionTitle.BUILDING_DELETE.getValue();
            case TENANT_CREATE -> this.action = ActionTitle.TENANT_CREATE.getValue();
            case TENANT_UPDATE -> this.action = ActionTitle.TENANT_UPDATE.getValue();
            case TENANT_DELETE -> this.action = ActionTitle.TENANT_DELETE.getValue();
        }
        return this.action;
    }
}
