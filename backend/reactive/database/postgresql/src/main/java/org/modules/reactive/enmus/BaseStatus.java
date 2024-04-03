package org.modules.reactive.enmus;

import lombok.Getter;

/**
 * The type ResourceStatus.
 *
 * @author Jx-zou
 */
@Getter
public enum BaseStatus {

    NORMAL(0), SUSPEND(1), DELETED(-1);

    private final int status;

    BaseStatus(int status) {
        this.status = status;
    }

}
