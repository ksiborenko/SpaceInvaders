package com.space.utils;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.space.App;

public class Lights {

    private final Light leftFirst;
    private final Light leftSecond;
    private final Light right;

    public Lights(RayHandler rayHandler) {
        rayHandler.setShadows(true);
        rayHandler.setCulling(true);
        rayHandler.setBlur(true);
        int rays = 200;
        int distance = 350;
        this.right = new PointLight(rayHandler, rays, Color.WHITE, distance, App.WIDTH, App.HEIGHT);
        this.leftFirst = new PointLight(rayHandler, rays, Color.WHITE, distance, 0, App.HEIGHT);
        this.leftSecond = new PointLight(rayHandler, rays, Color.WHITE, distance, 0, App.HEIGHT);
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
