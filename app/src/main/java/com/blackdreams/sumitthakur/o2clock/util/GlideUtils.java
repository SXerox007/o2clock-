package com.blackdreams.sumitthakur.o2clock.util;

import com.bumptech.glide.request.RequestOptions;

/**
 * The type Glide utils.
 */
public class GlideUtils {

    /**
     * Sets glide placeholder.
     *
     * @param placeholderResource the placeholder resource
     * @return the glide placeholder
     */
    public static RequestOptions setGlidePlaceholder(final int placeholderResource) {
        return new RequestOptions().placeholder(placeholderResource);
    }

    /**
     * Sets circular image.
     *
     * @return the circular image
     */
    public static RequestOptions setCircularImage() {
        return new RequestOptions().circleCrop();
    }
}
