package org.team555.animation2.api;

import edu.wpi.first.wpilibj.AddressableLEDBuffer;

/**
 * A class which implements a simple animation that contains its own buffer.
 */
public abstract class AnimationBase extends SimpleAnimationBase
{
    private final AddressableLEDBuffer buffer = new AddressableLEDBuffer(97); //TODO add constant

    @Override
    public AddressableLEDBuffer getBuffer() {return buffer;}
}
