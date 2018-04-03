package com.rider.goldenreign.imagetransitionactivity.event_bus;

/**
 * Created by goldenreign on 4/3/2018.
 */

public class ChangeImageThumbnailVisibility {
    private final boolean visible;

    public ChangeImageThumbnailVisibility(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }
}
