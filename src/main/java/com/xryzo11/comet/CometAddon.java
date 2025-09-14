package com.xryzo11.comet;

import com.xryzo11.comet.modules.HoldAutoClick;
import com.xryzo11.comet.modules.OnVisualRange;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

public class CometAddon extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();

    public static final Category COMET_CATEGORY = new Category("Comet");

    @Override
    public void onInitialize() {
        LOG.info("Initializing Comet Addon");

        Modules.get().add(new OnVisualRange());
        Modules.get().add(new HoldAutoClick());
    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(COMET_CATEGORY);
    }

    @Override
    public String getPackage() {
        return "com.xryzo11.comet";
    }
}
