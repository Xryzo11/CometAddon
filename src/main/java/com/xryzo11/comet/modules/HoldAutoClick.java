package com.xryzo11.comet.modules;

import com.xryzo11.comet.CometAddon;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.option.KeyBinding;

public class HoldAutoClick extends Module {
    public HoldAutoClick() {
        super(CometAddon.COMET_CATEGORY, "Hold Auto Click", "Automatically clicks mouse buttons when held down.");
    }

    private final KeyBinding attackKey = mc.options.attackKey;
    private final KeyBinding useKey = mc.options.useKey;

    private double lmbTimer = 0;
    private double rmbTimer = 0;

    private final SettingGroup sg = settings.getDefaultGroup();

    private final Setting<Boolean> lmbEnabled = sg.add(new BoolSetting.Builder()
        .name("LMB Enabled")
        .description("Automatically clicks the left mouse button when held down.")
        .defaultValue(true)
        .build()
    );

    private final Setting<Double> lmbCps = sg.add(new DoubleSetting.Builder()
        .name("LMB CPS")
        .description("Click Per Second for the Left Mouse Button.")
        .defaultValue(5.0)
        .min(1.0)
        .max(20.0)
        .sliderRange(1.0, 20.0)
        .decimalPlaces(1)
        .visible(lmbEnabled::get)
        .build()
    );

    private final Setting<Boolean> rmbEnabled = sg.add(new BoolSetting.Builder()
        .name("RMB Enabled")
        .description("Automatically clicks the right mouse button when held down.")
        .defaultValue(true)
        .build()
    );

    private final Setting<Double> rmbCps = sg.add(new DoubleSetting.Builder()
        .name("RMB CPS")
        .description("Click Per Second for the Right Mouse Button.")
        .defaultValue(5.0)
        .min(1.0)
        .max(20.0)
        .sliderRange(1.0, 20.0)
        .decimalPlaces(1)
        .visible(rmbEnabled::get)
        .build()
    );

    public boolean isLmbDown() {
        return attackKey.isPressed();
    }
    public boolean isRmbDown() {
        return useKey.isPressed();
    }

    @EventHandler
    private void onTick(TickEvent.Post event) {
        if (lmbEnabled.get() && attackKey.isPressed()) {
            lmbTimer -= 1.0;
            double ticksPerClick = 20.0 / lmbCps.get();
            if (lmbTimer <= 0) {
                KeyBinding.onKeyPressed(attackKey.getDefaultKey());
                lmbTimer += ticksPerClick;
            }
        } else {
            lmbTimer = 0;
        }

        if (rmbEnabled.get() && useKey.isPressed()) {
            rmbTimer -= 1.0;
            double ticksPerClick = 20.0 / rmbCps.get();
            if (rmbTimer <= 0) {
                KeyBinding.onKeyPressed(useKey.getDefaultKey());
                rmbTimer += ticksPerClick;
            }
        } else {
            rmbTimer = 0;
        }
    }
}
