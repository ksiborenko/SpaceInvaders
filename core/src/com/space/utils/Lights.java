package com.space.utils;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.space.App;

public class Lights {

    public static final int RAYS = 200;
    public static final int DISTANCE = 350;
    private final Light leftFirst;
    private final Light leftSecond;
    private final Light right;

    public Lights(RayHandler rayHandler) {
        rayHandler.setShadows(true);
        rayHandler.setCulling(true);
        rayHandler.setBlur(true);
        this.right = new PointLight(rayHandler, RAYS, Color.WHITE, DISTANCE, App.WIDTH, App.HEIGHT);
        this.leftFirst = new PointLight(rayHandler, RAYS, Color.WHITE, DISTANCE, 0, App.HEIGHT);
        this.leftSecond = new PointLight(rayHandler, RAYS, Color.WHITE, DISTANCE, 0, App.HEIGHT);
    }

    public Light getLeftFirst() {
        return leftFirst;
    }

    public Light getLeftSecond() {
        return leftSecond;
    }

    public Light getRight() {
        return right;
    }
}
