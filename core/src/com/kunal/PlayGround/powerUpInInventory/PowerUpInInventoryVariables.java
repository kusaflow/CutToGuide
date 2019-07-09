package com.kunal.PlayGround.powerUpInInventory;

import com.badlogic.gdx.graphics.Texture;

/*
>>>>>>>>>>>>>Type Of Power
    >1 ->> fast Move
    >2 ->> stopper
    >3 ->> Jumper
    >4 ->> directionChange
 */

public class PowerUpInInventoryVariables {
    public Texture texture;
    public short TypeOfPower;
    public Boolean active = true;
    public short x = -550;
    public short y = -550;
}
