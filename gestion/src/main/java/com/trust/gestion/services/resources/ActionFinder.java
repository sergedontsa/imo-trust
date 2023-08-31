package com.trust.gestion.services.resources;

import com.trust.gestion.enums.ActionType;
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

    public String getAction(ActionType actionType) {
        switch (actionType){
            case OWNER_CREATE -> this.action = ActionType.OWNER_CREATE.getValue();
            case OWNER_UPDATE -> this.action = ActionType.OWNER_UPDATE.getValue();
            case OWNER_DELETE -> this.action = ActionType.OWNER_DELETE.getValue();
            case BUILDING_CREATE -> this.action = ActionType.BUILDING_CREATE.getValue();
            case BUILDING_UPDATE -> this.action = ActionType.BUILDING_UPDATE.getValue();
            case BUILDING_DELETE -> this.action = ActionType.BUILDING_DELETE.getValue();
            case TENANT_CREATE -> this.action = ActionType.TENANT_CREATE.getValue();
            case TENANT_UPDATE -> this.action = ActionType.TENANT_UPDATE.getValue();
            case TENANT_DELETE -> this.action = ActionType.TENANT_DELETE.getValue();
        }
        return this.action;
    }
}
